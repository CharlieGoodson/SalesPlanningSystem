package model;

import java.util.List;

public class SalesRow {

    private Catalog catalog;

    private List<Transaction> factPeriod;

    private List<Transaction> planPeriod;

    private final static double COEF = 1.5;



    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<Transaction> getFactPeriod() {
        return factPeriod;
    }

    public void setFactPeriod(List<Transaction> factPeriod) {
        this.factPeriod = factPeriod;
    }

    public List<Transaction> getPlanPeriod() {
        return planPeriod;
    }

    public void setPlanPeriod(List<Transaction> planPeriod) {
        this.planPeriod = planPeriod;
    }

    public static double getCoef() {
        return COEF;
    }

    @Override
    public String toString() {
        String str = catalog + " | ";
        for (Transaction transaction : factPeriod) {
            str += transaction.getSum() + " | ";
        }
        return str;
    }
}
