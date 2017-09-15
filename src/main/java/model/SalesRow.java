package model;

import java.util.ArrayList;
import java.util.List;

public class SalesRow {

    private Catalog catalog;

    private List<Transaction> factPeriod;

    private List<Transaction> planPeriod;

    private List<Transaction> cloneAndIndexPeriod(List<Transaction> period, double coef) {
        List<Transaction> list = new ArrayList<>();
        Transaction temp;
        for (Transaction item : period) {
            temp = item.clone();
            temp.index(coef);
            list.add(temp);
        }
        return list;
    }

    public void makePlanAndIndexPeriod(double coef) {
        planPeriod = cloneAndIndexPeriod(factPeriod, coef);
    }


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

    @Override
    public String toString() {
        String str = catalog + "| ";
        for (Transaction transaction : factPeriod) {
            str += transaction.getSum() + " | ";
        }
        for (Transaction transaction : planPeriod) {
            str += transaction.getSum() + " | ";
        }

        return str;
    }
}
