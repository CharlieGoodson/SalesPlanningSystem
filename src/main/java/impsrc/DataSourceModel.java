package impsrc;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataSourceModel {

    private String filename;
    ArrayList<DataSource> list = new ArrayList<>();

    public DataSourceModel(String filename) {
        this.filename = filename;
    }

    public List<DataSource> getData() {

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

            DataSource listItem = new DataSource();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = null;

            cell = cellIterator.next();
            listItem.setRef(cell.getStringCellValue());

            cell = cellIterator.next();
            listItem.setTitle(cell.getStringCellValue());

            cell = cellIterator.next();
            listItem.setYear((int) (cell.getNumericCellValue()));

            cell = cellIterator.next();
            listItem.setMonth((int) (cell.getNumericCellValue()));

            cell = cellIterator.next();
            int s = (int) Math.round(cell.getNumericCellValue());
            listItem.setSum(s);

            list.add(listItem);
        }

        return list;
    }

    public int getSize() {
        return list.size();
    }

    public void sort() {
        Collections.sort(list);
    }

    public void printToConsole() {
        for (DataSource item : list) {
            System.out.println(item);
        }
    }
}
