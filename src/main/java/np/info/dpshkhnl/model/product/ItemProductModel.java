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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import np.info.dpshkhnl.model.admin.UnitModel;


@Entity
@Table(name="item_product")
public class ItemProductModel  implements Serializable{
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true)
	private int productId;

	@Column(name="product_code", unique = true)
	private String productCode;

	@Column(name="recorder_level")
	private int recoderLevel;

	@ManyToOne
	@JoinColumn(referencedColumnName="item_type_id",name="item_id")
	private ItemTypeModel itemId;

	@ManyToOne
	@JoinColumn(referencedColumnName="brand_id",name="brand_id")
	private BrandModel brandId;
	
	@OneToOne
	@JoinColumn(referencedColumnName="unit_id",name="unit_id")
	private UnitModel unitId;

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
	@Column(name = "updated_count")
	private int updateCount;	
	

	public UnitModel getUnitId() {
		return unitId;
	}

	public void setUnitId(UnitModel unitId) {
		this.unitId = unitId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getRecoderLevel() {
		return recoderLevel;
	}

	public void setRecoderLevel(int recoderLevel) {
		this.recoderLevel = recoderLevel;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brandId == null) ? 0 : brandId.hashCode());
		result = prime * result + createdBy;
		result = prime * result
		+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result
		+ ((productCode == null) ? 0 : productCode.hashCode());
		result = prime * result + productId;
		result = prime * result + recoderLevel;
		result = prime * result + updateCount;
		result = prime * result + updatedBy;
		result = prime * result
		+ ((updatedDate == null) ? 0 : updatedDate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

    public ItemTypeModel getItemId() {
        return itemId;
    }

    public void setItemId(ItemTypeModel itemId) {
        this.itemId = itemId;
    }

    public BrandModel getBrandId() {
        return brandId;
    }

    public void setBrandId(BrandModel brandId) {
        this.brandId = brandId;
    }

}
    

