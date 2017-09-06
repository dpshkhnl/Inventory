/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.model;

import java.util.List;

/**
 *
 * @author Dpshkhnl
 */
public class DashboardModel {
    
    private int id;
    private int customers;
    private int suppliers;
    private int product;
    private double totPurchase;
    private double totSales;
    private double expenses;
    private double profit;
    private double purchasePer;
    private double salesPer;
    private double expensePer;
    private double profitPer;
    private List<Monthly> lstMonthlyData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

    public int getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(int suppliers) {
        this.suppliers = suppliers;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public double getTotPurchase() {
        return totPurchase;
    }

    public void setTotPurchase(double totPurchase) {
        this.totPurchase = totPurchase;
    }

    public double getTotSales() {
        return totSales;
    }

    public void setTotSales(double totSales) {
        this.totSales = totSales;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getPurchasePer() {
        return purchasePer;
    }

    public void setPurchasePer(double purchasePer) {
        this.purchasePer = purchasePer;
    }

    public double getSalesPer() {
        return salesPer;
    }

    public void setSalesPer(double salesPer) {
        this.salesPer = salesPer;
    }

    public double getExpensePer() {
        return expensePer;
    }

    public void setExpensePer(double expensePer) {
        this.expensePer = expensePer;
    }

    public double getProfitPer() {
        return profitPer;
    }

    public void setProfitPer(double profitPer) {
        this.profitPer = profitPer;
    }

    public List<Monthly> getLstMonthlyData() {
        return lstMonthlyData;
    }

    public void setLstMonthlyData(List<Monthly> lstMonthlyData) {
        this.lstMonthlyData = lstMonthlyData;
    }
    
    
     public class Monthly{
         private int sno;
         private String month;
         private String value;

        public int getSno() {
            return sno;
        }

        public void setSno(int sno) {
            this.sno = sno;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
        
    }
}
