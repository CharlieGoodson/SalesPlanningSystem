// Класс, который полностью отвечает за импорт исходных данных о фактических
// помесячных продажах с внешнего файла с данными *.xlsx.
// 1 - сначала метод load() считывает, загружает в память и сортирует данные в виде списка
// 2 - затем метод pasteId() подставляет в поле idCatalog id из соответсвующей позиции Catalog
// 3 - метод saveToDb() сохраняет считанные данные в базу данных в таблицу Sale.

package data;

import dao.DAO;
import model.Catalog;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.ConnectSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataModel {

    private String filename; // имя файла источника данных
    ArrayList<Data> list = new ArrayList<>(); // здесь будут лежать прочитанные данные

    ///////////////////////////////////////////////////////////////
    // Конструктор, считывает из файла-пропертиз имя файла данных
    public DataModel() {
        ConnectSettings settings = new ConnectSettings();
        filename = settings.getSrc();
    }

    //////////////////////////////////////////////////////////////
    // Считывает в память данные из файла данных *.xlsx
    // и сортирует по полю REF, YEAR, MONTH
    public void load() {
        XSSFWorkbook workbook = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(filename));
            workbook = new XSSFWorkbook(inputStream);
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                System.out.println(e);;
            }
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {

            Data listItem = new Data();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = null;
            // считываем ref
            cell = cellIterator.next();
            listItem.setRef(cell.getStringCellValue().toUpperCase());
            // считываем title
            cell = cellIterator.next();
            listItem.setTitle(cell.getStringCellValue());
            // считываем year
            cell = cellIterator.next();
            listItem.setYear((int) (cell.getNumericCellValue()));
            // считываем month
            cell = cellIterator.next();
            listItem.setMonth((int) (cell.getNumericCellValue()));
            // считываем sum
            cell = cellIterator.next();
            int s = (int) Math.round(cell.getNumericCellValue());
            listItem.setSum(s);

            list.add(listItem);
        }
        Collections.sort(list); // сортирует список по REF для последующей эффективной подстановки Id
    }

    /////////////////////////////////////////////////////////////////////////////////
    // Подставляет поле id_catalog в класс DATA для связи с таблицей CATALOG
    public void pasteId() {
        DAO dao = new DAO();
        String ref = list.get(0).getRef();
        int id = dao.getIdCatalogItem(ref);
        if (id == 0) {
            id = dao.insertCatalogItem(new Catalog(ref, list.get(0).getTitle()));
        }
        for (Data item : list) {
            if (item.getRef().equals(ref)) {
                item.setIdCatalog(id);
                continue;
            }
            ref = item.getRef();
            id = dao.getIdCatalogItem(ref);
            if (id == 0) {
                id = dao.insertCatalogItem(new Catalog(ref, item.getTitle())); ////!!!!!! Тут была ошибка!!!!!
            }
            item.setIdCatalog(id);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // сохраняет считанные, обработанные исходные данные в базу данных, в таблицу Sale.
    public void saveToDb() {
        DAO dao = new DAO();
        for (Data item : list) {
            dao.insertDataItemToSales(item);
        }
    }

    //////////////////////////////////////////////////////////
    // Возвращает размер списка,
    // т.е. количество прочитаных строк из *.xlsx файла данных
    public int size() {
        return list.size();
    }

    //////////////////////////////////////////////////////////
    // Возвращает сам список прочитаных данных
    public List<Data> getData() {
        return list;
    }

    //////////////////////////////////////////////////////////
    // Выводит считанные данные на консоль (метод для отладки)
    public void printToConsole() {
        for (Data item : list) {
            System.out.println(item);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    // Подставляет поле id_catalog в класс DATA для связи с таблицей CATALOG
    // Упрощенный альтернативный метод, не основной. Здесь не оптимизированы обращения к DB.
    public void pasteIdAlt() {
        DAO dao = new DAO();
        for (Data item : list) {
            String ref = item.getRef();
            int id = dao.getIdCatalogItem(ref);
            if (id == 0) {
                id = dao.insertCatalogItem(new Catalog(item.getRef(), item.getTitle()));
            }
            item.setIdCatalog(id);
        }
    }
}
