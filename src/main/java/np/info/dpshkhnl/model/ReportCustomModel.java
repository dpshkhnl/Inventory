/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.model;

/**
 *
 * @author Dpshkhnl
 */
public class ReportCustomModel {
 
    private int id;
    private String itemName;
    private double costPrice;
    private int outQty;
    private double perCost;
    private String unitName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

 
   

    public double getPerCost() {
        return perCost;
    }

    public void setPerCost(double perCost) {
        this.perCost = perCost;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getOutQty() {
        return outQty;
    }

    public void setOutQty(int outQty) {
        this.outQty = outQty;
    }
    
    
}
