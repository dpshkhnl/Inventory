/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.model.product;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import np.info.dpshkhnl.model.account.CodeValue;


@Entity
@Table(name = "inv_brand_name_mcg")
//@NamedQueries({
//	@NamedQuery(name = "BrandNameModel.findBrandName",query = "SELECT b FROM BrandNameModel b ORDER BY b.brandNameFor, b.brandName"),
//	@NamedQuery(name = "BrandNameModel.findBrandNameByType",query = "SELECT b FROM BrandNameModel b INNER JOIN b.brandNameFor br WHERE br.cvId = :cvIdPassed ORDER BY b.brandName"),
//	@NamedQuery(name = "BrandNameModel.findByBrandName",query = "SELECT b FROM BrandNameModel b where b.brandName like :brandName")	,
//	@NamedQuery(name = "BrandNameModel.findByALL",query = "SELECT b FROM BrandNameModel b where b.brandName  like :brandName and b.brandNameFor.cvId=:cvid")
//
//
//})
public class BrandModel implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String GET_BRAND_NAME = "BrandNameModel.findBrandName";
	public final static String GET_BY_BRANDNAME="BrandNameModel.findByBrandName";
	public final static String GET_BY_ALL="BrandNameModel.findByALL";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brand_id", unique = true)
	private int brandNameId;

	@Column(name = "brand_name", nullable = false)
	private String brandName;

	

	@Column(name = "brand_description", nullable = false)
	private String brandDescription;

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

	
	@Column(name = "updated_count")
	private int updateCount;

	public BrandModel() {
		super();
	}

	public int getBrandNameId() {
		return brandNameId;
	}

	public void setBrandNameId(int brandNameId) {
		this.brandNameId = brandNameId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
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

	

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
		updatedDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}
}
