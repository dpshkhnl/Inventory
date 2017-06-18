package np.info.dpshkhnl.model.admin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="branch_management_mcg")
public class RouteManagementModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="branch_id", unique = true)
	private int branchId;

	@Column(name="acc_head")
	private int accHead;

	@Column(name="branch_location")
	private String branchLocation;

	@Column(name="branch_name")
	private String branchName;

	@Column(name="branch_type")
	private int branchType;

	@Column(name="contact_number")
	private String contactNumber;

	@Column(name="contact_person")
	private String contactPerson;
        
        @Column(name="remarks")
	private String remarks;

	@Column(name="created_by")
	private int createdBy;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name="update_count")
	private int updateCount;


	@Column(name="updated_by")
	private int updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name = "is_outlet")
	private Boolean isOutlet;


	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getAccHead() {
		return accHead;
	}
	public void setAccHead(int accHead) {
		this.accHead = accHead;
	}
	public String getBranchLocation() {
		return branchLocation;
	}
	public void setBranchLocation(String branchLocation) {
		this.branchLocation = branchLocation;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public int getBranchType() {
		return branchType;
	}
	public void setBranchType(int branchType) {
		this.branchType = branchType;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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
	public int getUpdateCount() {
		return updateCount;
	}
	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
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
	public void setIsOutlet(Boolean isOutlet) {
		this.isOutlet = isOutlet;
	}
	public Boolean getIsOutlet() {
		return isOutlet;
	}

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}