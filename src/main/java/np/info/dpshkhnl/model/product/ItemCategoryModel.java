/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.model.product;

/**
 *
 * @author Dpshkhnl
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "item_category_mcg")
public class ItemCategoryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String GET_ALL_CATEGORY = "InventoryStockCategoryModel.findAllInvStkCategory";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_id", unique = true)
	private int catId;

	@Column(name = "cat_name", nullable = false)
	private String catName;

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(referencedColumnName = "cv_id", name = "stock_type_id")
	 * private CodeValue stkTypeId;
	 */

	@Column(name = "description")
	private String description;

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(referencedColumnName = "cv_id", name = "out_method_id")
	 * private CodeValue outMethod;
	 */

	@Column(name = "out_method_id", nullable = false)
	private int outMethod;

	

	@Column(name = "parent", nullable = false)
	private int parent;

	@Transient
	// not to be saved in database
	private String parentName;

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

	@Column(name = "update_count")
	private int updateCount;

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	

	/*
	 * public CodeValue getStkTypeId() { return stkTypeId; }
	 * 
	 * public void setStkTypeId(CodeValue stkTypeId) { this.stkTypeId =
	 * stkTypeId; }
	 */
	/*
	 * public CodeValue getOutMethod() { return outMethod; }
	 * 
	 * public void setOutMethod(CodeValue outMethod) { this.outMethod =
	 * outMethod; }
	 */
	public int getOutMethod() {
		return outMethod;
	}

	public void setOutMethod(int outMethod) {
		this.outMethod = outMethod;
	}

	/*
	 * public AccHeadMcg getRegStock() { return regStock; }
	 * 
	 * public void setRegStock(AccHeadMcg regStock) { this.regStock = regStock;
	 * }
	 * 
	 * public AccHeadMcg getRsExpense() { return rsExpense; }
	 * 
	 * public void setRsExpense(AccHeadMcg rsExpense) { this.rsExpense =
	 * rsExpense; }
	 * 
	 * public AccHeadMcg getDirectExp() { return directExp; }
	 * 
	 * public void setDirectExp(AccHeadMcg directExp) { this.directExp =
	 * directExp; }
	 */

	

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

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
		updatedDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + catId;
		return result;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	

}

