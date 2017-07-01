/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.model.sales;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import np.info.dpshkhnl.model.admin.UnitModel;
import np.info.dpshkhnl.model.product.ItemProductModel;

/**
 *
 * @author Dpshkhnl
 */

@Entity
@Table(name = "inv_sale_detail")

public class SalesDetailModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inv_sale_id", unique = true)
	private int invsaleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inv_receipt_id")
	private InventoryReceiptModel inventoryReceiptModel;
        
        
        @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inv_product_id")
	private ItemProductModel itemProductModel;
        
        @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inv_unit_id")
	private UnitModel unitModel;

	@Column(name = "out_qty", nullable = false)
	private int outQty;

	@Column(name = "ret_qty", nullable = false)
	private int retQty;

	@Column(name = "per_cost", nullable = false)
	private double perCost;

	@Column(name = "total_cost")
	private double totalCost;
        
        @Column(name = "cost_price")
	private double costPrice;

	
	@Column(name = "created_by")
	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@Version
	@Column(name = "update_count")
	private int updateCount;

	

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

    public int getInvsaleId() {
        return invsaleId;
    }

    public void setInvsaleId(int invsaleId) {
        this.invsaleId = invsaleId;
    }

    

    public ItemProductModel getItemProductModel() {
        return itemProductModel;
    }

    public void setItemProductModel(ItemProductModel itemProductModel) {
        this.itemProductModel = itemProductModel;
    }

    public UnitModel getUnitModel() {
        return unitModel;
    }

    public void setUnitModel(UnitModel unitModel) {
        this.unitModel = unitModel;
    }

    

    public int getRetQty() {
        return retQty;
    }

    public void setRetQty(int retQty) {
        this.retQty = retQty;
    }

    public double getPerCost() {
        return perCost;
    }

    public void setPerCost(double perCost) {
        this.perCost = perCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public InventoryReceiptModel getInventoryReceiptModel() {
        return inventoryReceiptModel;
    }

    public void setInventoryReceiptModel(InventoryReceiptModel inventoryReceiptModel) {
        this.inventoryReceiptModel = inventoryReceiptModel;
    }

    public int getOutQty() {
        return outQty;
    }

    public void setOutQty(int outQty) {
        this.outQty = outQty;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

  
	

	
}

