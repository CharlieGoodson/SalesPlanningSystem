package model;

public class Transaction {
    private int year;
    private int month;
    private int sum;
    private int idCatalog;

    public Transaction(int year, int month, int sum, int idCatalog) {
        this.year = year;
        this.month = month;
        this.sum = sum;
        this.idCatalog = idCatalog;
    }

    public Transaction clone() {
        Transaction temp = new Transaction(year, month, sum, idCatalog);
        return temp;
    }

    public void index(double coef) {
        sum = (int) Math.round(sum * coef);
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
        return  "year=" + year +
                ", month=" + month +
                ", sum=" + sum +
                ", idCatalog=" + idCatalog;
    }
}