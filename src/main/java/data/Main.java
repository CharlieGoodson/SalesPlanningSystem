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

        CatalogItem item = new CatalogItem("012000", "jsngjsnfkjnsjfb");
        dao.insertCatalogItem(item);

//        for (Data item : list) {
//            String ref = item.getRef();
//            int id = dao.getIdCatalogItem(ref);
//            item.setIdCatalog(id);
//        }
//
//        System.out.println("После подстановки ID");
//        model.printToConsole();
    }
}
