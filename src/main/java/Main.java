import dao.DAO;
import data.DataModel;
import model.Catalog;
import model.SalesRow;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DAO dao = new DAO();
        dao.createTableCatalog();
        dao.createTableSales();

        DataModel model = new DataModel();
        model.load();
        System.out.println("... данные прочитаны!!!");

        model.pasteId();
        System.out.println("... столбцы подставлены!!!");

        model.saveToDb();
        System.out.println("... данные успешно сохранены в базу!!!");



        ///////////  Код который разворачивает столбцы в строки в таблице   /////////////

        List<Transaction> listSales = dao.getAllFromSales();
        List<SalesRow> listRow = new ArrayList<>();

        int id = listSales.get(0).getIdCatalog();
        Catalog catalogItem = dao.getCatalogItem(id);
        SalesRow row = new SalesRow();
        row.setCatalog(catalogItem);
        List<Transaction> list = new ArrayList<>();
        row.setFactPeriod(list);
        listRow.add(row);

        for (Transaction item : listSales) {

            if (id == item.getIdCatalog()) {
                list.add(item);

            } else {
                id = item.getIdCatalog();
                catalogItem = dao.getCatalogItem(id);
                row = new SalesRow();
                row.setCatalog(catalogItem);
                list = new ArrayList<>();
                row.setFactPeriod(list);
                list.add(item);
                listRow.add(row);
            }
        }
        ///////////////////////////////////////////////////////////////////


        for (SalesRow rowPr : listRow) {
            System.out.println(rowPr);
        }

    }
}
