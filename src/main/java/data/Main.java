package data;

import dao.DAO;
import model.CatalogItem;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataModel model = new DataModel();
        model.load();

        List<Data> list = model.getData();

        DAO dao = new DAO();

        dao.recreateCatalog();


//        int countInsert = 0;
//        int countSelect = 0;
//
//        for (Data item : list) {
//            String ref = item.getRef();
//            int id = dao.getIdCatalogItem(ref);
//            countSelect++;
//            if (id == 0) {
//                id = dao.insertCatalogItem(new CatalogItem(item.getRef(), item.getTitle()));
//                countInsert++;
//            }
//            item.setIdCatalog(id);
//        }

        //////////////////////////////////////////////////////////////////////////////
        // Подставляет поле id_catalog в класс DATA для связи с таблицей CATALOG
//        int countInsert = 0;
//        int countSelect = 0;
//
//        String ref = list.get(0).getRef();
//        int id = dao.getIdCatalogItem(ref);
//        countSelect++;
//        if (id == 0) {
//            id = dao.insertCatalogItem(new CatalogItem(ref, list.get(0).getTitle()));
//            countInsert++;
//        }
//
//        for (Data item : list) {
//            if (item.getRef().equals(ref)) {
//                item.setIdCatalog(id);
//                continue;
//            }
//            ref = item.getRef();
//            id = dao.getIdCatalogItem(ref);
//            countSelect++;
//            if (id == 0) {
//                id = dao.insertCatalogItem(new CatalogItem(ref, list.get(0).getTitle()));
//                countInsert++;
//            }
//            item.setIdCatalog(id);
//        }
//        /////////////////////////////////////////////////////////////////////////////
//        System.out.println("После подстановки ID");
//        model.printToConsole();
//        System.out.println("countInsert - " + countInsert);
//        System.out.println("countSelect - " + countSelect);

    }
}
