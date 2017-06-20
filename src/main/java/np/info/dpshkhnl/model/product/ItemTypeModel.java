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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "item_type")
public class ItemTypeModel implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_type_id", unique = true)
	private int itemTypeId;

	/*
	 * @Column(name="alt_unit_id") private int altUnitId;
	 */

	@OneToOne
	@JoinColumn(referencedColumnName = "cat_id", name = "cat_id")
	private ItemCategoryModel catId;

	@Column(name = "created_by")
	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	private String description;

	
	@Column(name = "item_code")
	private String itemCode;

	@Column(name = "item_reorder_qty")
	private int itemReorderQty;

	@Column(name = "item_type_name")
	private String itemTypeName;

	

	@Column(name = "tolerance")
	private double tolerance;

	/*
	 * @Column(name="unit_id") private int unitId;
	 */

	@Column(name = "update_count")
	private int updateCount;

	@Column(name = "updated_by")
	private int updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;



	public String getItemCode() {
		return itemCode;
	}

	public int getItemReorderQty() {
		return itemReorderQty;
	}

	

	public double getTolerance() {
		return tolerance;
	}

	

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setItemReorderQty(int itemReorderQty) {
		this.itemReorderQty = itemReorderQty;
	}

	
	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	public int getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	

	

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
		updatedDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

    public ItemCategoryModel getCatId() {
        return catId;
    }

    public void setCatId(ItemCategoryModel catId) {
        this.catId = catId;
    }

}
