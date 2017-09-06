package dataimport;

public class Main {

    public static void main(String[] args) {

        DataModel model = new DataModel("src.xlsx");
        model.load();
        model.sort();
        model.printToConsole();
        System.out.println("Импорт завершен. Количество записей: " + model.size());

    }
}
