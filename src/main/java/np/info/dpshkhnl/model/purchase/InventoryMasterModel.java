package np.info.dpshkhnl.model.purchase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import np.info.dpshkhnl.model.admin.UnitModel;
import np.info.dpshkhnl.model.product.BrandModel;
import np.info.dpshkhnl.model.product.ItemCategoryModel;
import np.info.dpshkhnl.model.product.ItemProductModel;
import np.info.dpshkhnl.model.product.ItemTypeModel;

@Entity
@Table(name = "inv_stock_master")

public class InventoryMasterModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inv_master_id", unique = true)
	private int invMasterId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inv_id", referencedColumnName = "invoice_id")
	private InventoryInvoiceModel invInvoiceStk;

	
	@OneToOne
	@JoinColumn(referencedColumnName = "product_id", name = "product_id")
	private ItemProductModel productId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
	private UnitModel unitId;

        

	@Column(name = "in_qty", nullable = false)
	private int inQty;

	@Column(name = "out_qty", nullable = false)
	private int outQty;

	@Column(name = "total_cost", nullable = false)
	private double totalCost;

	@Column(name = "unit_sale_price", nullable = false)
	private double unitSalePrice;

	@Column(name = "exp_dt_np", nullable = true)
	private String expDtNp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "exp_dt_en")
	private Date expDtEn;

	@Column(name = "created_by")
	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private java.util.Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@Version
	@Column(name = "update_count")
	private int updateCount;

	@Column(name = "is_remove")
	private boolean isRemove;

	/**
	 * @return the isRemove
	 */
	public boolean isRemove() {
		return isRemove;
	}

	/**
	 * @param isRemove
	 *            the isRemove to set
	 */
	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}

	public int getInvMasterId() {
		return invMasterId;
	}

	public void setInvMasterId(int invMasterId) {
		this.invMasterId = invMasterId;
	}

	public InventoryInvoiceModel getInvInvoiceStk() {
		return invInvoiceStk;
	}

	public void setInvInvoiceStk(InventoryInvoiceModel invInvoiceStk) {
		this.invInvoiceStk = invInvoiceStk;
	}


	public UnitModel getUnitId() {
		return unitId;
	}

	public void setUnitId(UnitModel unitId) {
		this.unitId = unitId;
	}

	

	public int getInQty() {
		return inQty;
	}

	public void setInQty(int inQty) {
		this.inQty = inQty;
	}

	public int getOutQty() {
		return outQty;
	}

	public void setOutQty(int outQty) {
		this.outQty = outQty;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getUnitSalePrice() {
		return unitSalePrice;
	}

	public void setUnitSalePrice(double unitSalePrice) {
		this.unitSalePrice = unitSalePrice;
	}

	public String getExpDtNp() {
		return expDtNp;
	}

	public void setExpDtNp(String expDtNp) {
		this.expDtNp = expDtNp;
	}

	public Date getExpDtEn() {
		return expDtEn;
	}

	public void setExpDtEn(Date expDtEn) {
		this.expDtEn = expDtEn;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
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

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

	

   

    public ItemProductModel getProductId() {
        return productId;
    }

    public void setProductId(ItemProductModel productId) {
        this.productId = productId;
    }

    public boolean isIsRemove() {
        return isRemove;
    }

    public void setIsRemove(boolean isRemove) {
        this.isRemove = isRemove;
    }

	

}
