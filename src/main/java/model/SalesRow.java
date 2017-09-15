package model;

import java.util.List;

public class SalesRow {

    private CatalogItem catalog;

    private List<Transaction> factPeriod;

    private List<Transaction> planPeriod;  // sales forecasdt with coef applied

    private final static double coef = 1.5;



    public CatalogItem getCatalog() {
        return catalog;
    }

    public void setCatalog(CatalogItem catalog) {
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
        return coef;
    }

    @Override
    public String toString() {
        String str = " | ";
        for (Transaction transaction : factPeriod) {
            str += transaction.getSum() + " | ";
        }
        return catalog + str;
    }
}
