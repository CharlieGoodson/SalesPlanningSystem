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


//        for (int i = 0; i < list.size(); i++) {
//
//            String ref = list.get(i).getRef();
//            int id = dao.getIdCatalogItem(ref);
//            if (id == 0) {
//                id = dao.insertCatalogItem(new CatalogItem(list.get(i).getRef(), list.get(i).getTitle()));
//            }
//            list.get(i).setIdCatalog(id);
//
//            int j;
//            for (j = i + 1; j < list.size() && list.get(j).getRef().equals(ref); j++) {
//                list.get(j).setIdCatalog(id);
//            }
//            i = j - 1;
//        }

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
