package data;

import dao.DAO;
import model.CatalogItem;

import java.util.List;

public class Main {

    public static void main(String[] args) {

//        DAO dao = new DAO();
//        dao.createTableCatalog();
//        dao.createTableSales();

        DataModel model = new DataModel();
        model.load();
        System.out.println("... данные прочитаны!!!");

        model.pasteId();
        System.out.println("... столбцы подставлены!!!");

        model.saveToDb();
        System.out.println("... данные успешно сохранены в базу!!!");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        model.printToConsole();
        System.out.println("Выгружено строк: " + model.size());
    }
}
