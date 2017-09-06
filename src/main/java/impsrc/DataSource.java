package impsrc;

public class DataSource implements Comparable<DataSource> {

    String ref;
    String title;
    int year;
    int month;
    int sum;
    int idCatalog; // внешний ключ к таблице CATALOG

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getIdCatalog() {
        return idCatalog;
    }

    public void setIdCatalog(int idCatalog) {
        this.idCatalog = idCatalog;
    }

    @Override
    public String toString() {
        return  "ref='" + ref + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", sum=" + sum +
                ", idCatalog=" + idCatalog;
    }

    @Override
    // для сортировки записей сначала по REF, потом по YEAR и потом по MONTH
    public int compareTo(DataSource o) {
        int x = this.ref.compareTo(o.ref);
        if (x == 0) {
            x = this.year - o.year;
            if (x == 0) {
                x = this.month - o.month;
            }
        }
        return x;
    }
}
