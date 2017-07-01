package np.info.dpshkhnl.model.purchase;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import np.info.dpshkhnl.model.admin.UnitModel;
import np.info.dpshkhnl.model.product.ItemProductModel;

@Entity
@Table(name = "inv_purchase_detail")

public class PurchaseDetailModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inv_purchase_id", unique = true)
	private int invPurchaseId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inv_ivoice_id")
	private InventoryInvoiceModel inventoryInvoiceModel;
        
        
        @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inv_product_id")
	private ItemProductModel itemProductModel;
        
        @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inv_unit_id")
	private UnitModel unitModel;

	@Column(name = "in_qty", nullable = false)
	private int inQty;

	@Column(name = "ret_qty", nullable = false)
	private int retQty;

	@Column(name = "per_cost", nullable = false)
	private double perCost;

	@Column(name = "total_cost")
	private double totalCost;
        
        @Column(name = "selling_price")
	private double sellingPrice;

	
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

    public int getInvPurchaseId() {
        return invPurchaseId;
    }

    public void setInvPurchaseId(int invPurchaseId) {
        this.invPurchaseId = invPurchaseId;
    }

    public InventoryInvoiceModel getInventoryInvoiceModel() {
        return inventoryInvoiceModel;
    }

    public void setInventoryInvoiceModel(InventoryInvoiceModel inventoryInvoiceModel) {
        this.inventoryInvoiceModel = inventoryInvoiceModel;
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

    public int getInQty() {
        return inQty;
    }

    public void setInQty(int inQty) {
        this.inQty = inQty;
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

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

	

	
}
