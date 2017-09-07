package data;

import dao.DAO;
import model.CatalogItem;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataModel model = new DataModel();
        model.load();
        model.sort();

        List<Data> list = model.getData();

        DAO dao = new DAO();


        for (Data item : list) {

            String ref = item.getRef();
            int id = dao.getIdCatalogItem(ref);
            if (id == 0) {
                id = dao.insertCatalogItem(new CatalogItem(item.getRef(), item.getTitle()));
            }
            item.setIdCatalog(id);
        }

        System.out.println("После подстановки ID");
        model.printToConsole();
    }
}
