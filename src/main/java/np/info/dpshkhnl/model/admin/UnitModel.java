package np.info.dpshkhnl.model.admin;

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
import javax.persistence.Transient;
import np.info.dpshkhnl.model.account.CodeValue;




@Entity
@Table(name = "inv_unit_mcg")
@NamedQueries({ @NamedQuery(name = "UnitModel.findUnit", query = "select u from UnitModel u where u.parentUnitId  = :parentId "),
	@NamedQuery(name = "UnitModel.findUnitById", query = "select u from UnitModel u where u.unitId  = :unitIdPassed "),
	@NamedQuery(name = "UnitModel.findChildByParentId", query = "select u from UnitModel u where u.parentUnitId  = :unitIdPassed "),
	@NamedQuery(name="UnitModel.getParentDetail", query = "select u from UnitModel u where u.unitId = :parentId ")
})
public class UnitModel implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String GET_UNIT = "UnitModel.findUnit";
	public final static String GET_UNIT_BY_ID = "UnitModel.findUnitById";
	public final static String GET_PARENT_DETAIL = 	"UnitModel.getParentDetail";
	public final static String GET_CHILD_BY_PARENT = "UnitModel.findChildByParentId";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_id", unique = true)
	private int unitId;

	@Column(name = "name", nullable = false)
	private String unitName;

	
	@Column(name = "punit_id")
	private int parentUnitId;

	@Transient
	private String parentName;

	/*
	 * // @Transient
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn(referencedColumnName = "unit_id", name = "punit_id", nullable
	 * = true, insertable = false, updatable = false) private UnitModel
	 * parentModel;
	 * 
	 * public UnitModel getParentModel() { return parentModel; }
	 * 
	 * public void setParentModel(UnitModel parentModel) { this.parentModel =
	 * parentModel; }
	 */
	@Column(name = "unit_count", nullable = false)
	private int unitCount;

	/*@OneToOne
	@JoinColumn(referencedColumnName = "cv_id", name = "status")
	private CodeValue status;*/

	@Column(name = "remarks", nullable = false)
	private String remarks;

	@Column(name = "crtd_by")
	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "crtd_dt")
	private java.util.Date createdDate;

	@Column(name = "updt_by")
	private int updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updt_dt")
	private Date updatedDate;

	@Column(name = "updt_cnt")
	private int updateCount;

	public UnitModel() {
		super();
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	

	public int getParentUnitId() {

		return parentUnitId;
	}

	public void setParentUnitId(int parentUnitId) {
		this.parentUnitId = parentUnitId;
	}

	/*
	 * public String getParentName() { if (parentUnitId <= 0) {
	 * this.setParentName("Root"); } else { UnitModel model = new UnitModel();
	 * UnitEJB unitEJB = null; model = unitEJB.find(parentUnitId);
	 * this.setParentName(model.unitName); } return parentName; }
	 * 
	 * public void setParentName(String parentNamePassed) { this.parentName =
	 * parentNamePassed; }
	 */
	public int getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(int unitCount) {
		this.unitCount = unitCount;
	}

	/*public CodeValue getStatus() {
		return status;
	}

	public void setStatus(CodeValue status) {
		this.status = status;
	}*/

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
		//	updatedDate = new Date();
	}

	

	

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

}
