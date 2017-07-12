package np.info.dpshkhnl.model.account;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;



@Entity
@Table(name="receipt",uniqueConstraints={@UniqueConstraint(name="unqReceiptConstraint",columnNames={"receipt_id","fy_id","receipt_no"})})
@NamedQueries({
	@NamedQuery(name = "ReceiptModel.findUnapprovedReceipts", query = "SELECT u from ReceiptModel u where u.status=:status"),
	@NamedQuery(name = "ReceiptModel.findByReceiptNo", query = "SELECT r FROM ReceiptModel r where r.receiptNo=:receiptNoPassed"),
	/*@NamedQuery(name = "ReceiptModel.findByStudentId", query = "SELECT r FROM ReceiptModel r where r.studentModel.studentId =:studentIdPassed")*/
})
public class ReceiptModel implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public static final String FIND_UNPOSTED_RECEIPTS = "ReceiptModel.findUnapprovedReceipts";
	public static final String FIND_BY_RECEIPT_NO = "ReceiptModel.findByReceiptNo";
	public static final String FIND_BY_STUDENT_ID = "ReceiptModel.findByStudentId";
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receipt_id")
	private long receiptId;

	@Column(name="receipt_no")
	//@MapsId("receiptNo")
	private int receiptNo;

	@ManyToOne
	//@MapsId("fiscalYearId")
	@JoinColumn(name="fy_id",referencedColumnName="fy_id")
	private FiscalYrModel fiscalYear;

	@ManyToOne
	@JoinColumn(name="jv_type",referencedColumnName="cv_id")
	private CodeValue jv_type;

	@OneToMany(mappedBy="receiptModel", cascade=CascadeType.ALL)
	private List<ReceiptDetailsModel> receiptDetailsList;


	@Column(name="trxn_from" ,length=20)
	private String trxnFrom;



	@Column(name="receipt_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date receiptDate;

	@Column(name="receipt_date_bs")
	private String receiptDateBs;

	@Column(name="receipt_amt",columnDefinition="Decimal(28,2) default '0.00'")
	private double receiptAmount;

	@Column(name="remarks")
	private String remarks;

	/*@ManyToOne
	@JoinColumn(name = "receipt_by", referencedColumnName="id")
	private User receiptBy;

	@ManyToOne
	@JoinColumn(name = "updated_by", referencedColumnName="id")
	private User updatedBy;*/

	@Version
	@Column(name="update_count")
	private int updateCount;

	@Column(name="updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name="status")
	private int status;

	@Column(name = "br_id")
	private int brId;

	@Column(name = "receipt_person",length=150)
	private String receiptPerson;

	//added on improvisation
	@Column(name="cash_amt",columnDefinition="Decimal(28,2) default '0.00'")
	private double cashAmount;
	@Column(name="bank_amt",columnDefinition="Decimal(28,2) default '0.00'")
	private double bankAmount;
	@Column(name="cheque_amt",columnDefinition="Decimal(28,2) default '0.00'")
	private double chequeAmount;	
	@Column(name="by_advance_amt",columnDefinition="Decimal(28,2) default '0.00'")
	private double advanceAdjustmentAmount;
	@Column(name="change_amt",columnDefinition="Decimal(28,2) default '0.00'")
	private double changeAmount;
	@Column(name="cheque_no")
	private String chequeNumber;
	@Column(name="keep_as_advance")
	private boolean keepAsAdvance;




	/**
	 * @return the receiptForName
	 */
	public String getReceiptPerson() {
		return receiptPerson;
	}

	/**
	 * @param receiptForPerson the receiptForName to set
	 */
	public void setReceiptPerson(String receiptForPerson) {
		this.receiptPerson = receiptForPerson;
	}

	/**
	 * @return the receiptPk
	 *//*
	public ReceiptPk getReceiptPk() {
		return receiptPk;
	}

	  *//**
	  * @param receiptPk the receiptPk to set
	  *//*
	public void setReceiptPk(ReceiptPk receiptPk) {
		this.receiptPk = receiptPk;
	}*/
	/**
	 * @param receiptId the receiptId to set
	 */
	/*public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	 */
	/**
	 * @param receiptId the receiptId to set
	 */
	public void setReceiptId(long receiptId) {
		this.receiptId = receiptId;
	}

	/**
	 * @return the receiptId
	 */
	public long getReceiptId() {
		return receiptId;
	}



	/**
	 * @return the receiptDetailsList
	 */
	public List<ReceiptDetailsModel> getReceiptDetailsList() {
		return receiptDetailsList;
	}

	/**
	 * @param receiptDetailsList the receiptDetailsList to set
	 */
	public void setReceiptDetailsList(List<ReceiptDetailsModel> receiptDetailsList) {
		this.receiptDetailsList = receiptDetailsList;
	}

	
	public int getReceiptNo() {
		return receiptNo;
	}

	/**
	 * @param receiptNo the receiptNo to set
	 */
	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}


	public String getTrxnFrom() {
		return trxnFrom;
	}

	/**
	 * @param trxnFrom the trxnFrom to set
	 */
	public void setTrxnFrom(String trxnFrom) {
		this.trxnFrom = trxnFrom;
	}

	/**
	 * @return the studentModel
	 *//*
	public StudentModel getStudentModel() {
		return studentModel;
	}

	  *//**
	  * @param studentModel the studentModel to set
	  *//*
	public void setStudentModel(StudentModel studentModel) {
		this.studentModel = studentModel;
	}

	   *//**
	   * @return the member
	   *//*
	public MemberInformationModel getMember() {
		return member;
	}

	    *//**
	    * @param member the member to set
	    *//*
	public void setMember(MemberInformationModel member) {
		this.member = member;
	}
	     */
	/**
	 * @return the receiptDate
	 */
	public Date getReceiptDate() {
		return receiptDate;
	}

	/**
	 * @param receiptDate the receiptDate to set
	 */
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	/**
	 * @return the receiptDateBs
	 */
	public String getReceiptDateBs() {
		return receiptDateBs;
	}

	/**
	 * @param receiptDateBs the receiptDateBs to set
	 */
	public void setReceiptDateBs(String receiptDateBs) {
		this.receiptDateBs = receiptDateBs;
	}

	/**
	 * @return the receiptAmount
	 */
	public double getReceiptAmount() {
		return receiptAmount;
	}

	/**
	 * @param receiptAmount the receiptAmount to set
	 */
	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the receiptBy
	 *//*
	public User getReceiptBy() {
		return receiptBy;
	}

	  *//**
	  * @param receiptBy the receiptBy to set
	  *//*
	public void setReceiptBy(User receiptBy) {
		this.receiptBy = receiptBy;
	}
	   */
	/**
	 * @return the updateCount
	 */
	public int getUpdateCount() {
		return updateCount;
	}

	/**
	 * @param updateCount the updateCount to set
	 */
	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the updatedBy
	 *//*
	public User getUpdatedBy() {
		return updatedBy;
	}

	  *//**
	  * @param updatedBy the updatedBy to set
	  *//*
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}*/

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the fiscalYear
	 */
	public FiscalYrModel getFiscalYear() {
		return fiscalYear;
	}

	/**
	 * @param fiscalYear the fiscalYear to set
	 */
	public void setFiscalYear(FiscalYrModel fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	/**
	 * @return the jv_type
	 */
	public CodeValue getJv_type() {
		return jv_type;
	}

	/**
	 * @param jv_type the jv_type to set
	 */
	public void setJv_type(CodeValue jv_type) {
		this.jv_type = jv_type;
	}

	/**
	 * @return the brId
	 */
	public int getBrId() {
		return brId;
	}

	/**
	 * @param brId the brId to set
	 */
	public void setBrId(int brId) {
		this.brId = brId;
	}

	/**
	 * @return the cashAmount
	 */
	public double getCashAmount() {
		return cashAmount;
	}

	/**
	 * @param cashAmount the cashAmount to set
	 */
	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}

	/**
	 * @return the bankAmount
	 */
	public double getBankAmount() {
		return bankAmount;
	}

	/**
	 * @param bankAmount the bankAmount to set
	 */
	public void setBankAmount(double bankAmount) {
		this.bankAmount = bankAmount;
	}

	/**
	 * @return the chequeAmount
	 */
	public double getChequeAmount() {
		return chequeAmount;
	}

	/**
	 * @param chequeAmount the chequeAmount to set
	 */
	public void setChequeAmount(double chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	/**
	 * @return the advanceAdjustmentAmount
	 */
	public double getAdvanceAdjustmentAmount() {
		return advanceAdjustmentAmount;
	}

	/**
	 * @param advanceAdjustmentAmount the advanceAdjustmentAmount to set
	 */
	public void setAdvanceAdjustmentAmount(double advanceAdjustmentAmount) {
		this.advanceAdjustmentAmount = advanceAdjustmentAmount;
	}

	/**
	 * @return the changeAmount
	 */
	public double getChangeAmount() {
		return changeAmount;
	}

	/**
	 * @param changeAmount the changeAmount to set
	 */
	public void setChangeAmount(double changeAmount) {
		this.changeAmount = changeAmount;
	}

	/**
	 * @return the keepAsAdvance
	 */
	public boolean isKeepAsAdvance() {
		return keepAsAdvance;
	}

	/**
	 * @param keepAsAdvance the keepAsAdvance to set
	 */
	public void setKeepAsAdvance(boolean keepAsAdvance) {
		this.keepAsAdvance = keepAsAdvance;
	}

	/**
	 * @return the chequeNumber
	 */
	public String getChequeNumber() {
		return chequeNumber;
	}

	/**
	 * @param chequeNumber the chequeNumber to set
	 */
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	/*	public FirmRegistrationModel getFirm() {
		return firm;
	}

	public void setFirm(FirmRegistrationModel firm) {
		this.firm = firm;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}*/

	/*	 (non-Javadoc)
	 * @see java.lang.Object#hashCode()

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(advanceAdjustmentAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(bankAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + brId;
		temp = Double.doubleToLongBits(cashAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(changeAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(chequeAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((chequeNumber == null) ? 0 : chequeNumber.hashCode());
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((firm == null) ? 0 : firm.hashCode());
		result = prime * result
				+ ((fiscalYear == null) ? 0 : fiscalYear.hashCode());
		result = prime * result
				+ ((journalVoucher == null) ? 0 : journalVoucher.hashCode());
		result = prime * result + ((jv_type == null) ? 0 : jv_type.hashCode());
		result = prime * result + (keepAsAdvance ? 1231 : 1237);
	//	result = prime * result + ((member == null) ? 0 : member.hashCode());
		temp = Double.doubleToLongBits(receiptAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((receiptBy == null) ? 0 : receiptBy.hashCode());
		result = prime * result
				+ ((receiptDate == null) ? 0 : receiptDate.hashCode());
		result = prime * result
				+ ((receiptDateBs == null) ? 0 : receiptDateBs.hashCode());
		result = prime
	 * result
				+ ((receiptDetailsList == null) ? 0 : receiptDetailsList
						.hashCode());
		result = prime * result + (int) (receiptId ^ (receiptId >>> 32));
		result = prime * result + receiptNo;
		result = prime * result
				+ ((receiptPerson == null) ? 0 : receiptPerson.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + status;
		result = prime * result
				+ ((studentModel == null) ? 0 : studentModel.hashCode());
		result = prime * result
				+ ((trxnFrom == null) ? 0 : trxnFrom.hashCode());
		result = prime * result + updateCount;
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedDate == null) ? 0 : updatedDate.hashCode());
		return result;
	}

	 (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReceiptModel other = (ReceiptModel) obj;
		if (Double.doubleToLongBits(advanceAdjustmentAmount) != Double
				.doubleToLongBits(other.advanceAdjustmentAmount))
			return false;
		if (Double.doubleToLongBits(bankAmount) != Double
				.doubleToLongBits(other.bankAmount))
			return false;
		if (brId != other.brId)
			return false;
		if (Double.doubleToLongBits(cashAmount) != Double
				.doubleToLongBits(other.cashAmount))
			return false;
		if (Double.doubleToLongBits(changeAmount) != Double
				.doubleToLongBits(other.changeAmount))
			return false;
		if (Double.doubleToLongBits(chequeAmount) != Double
				.doubleToLongBits(other.chequeAmount))
			return false;
		if (chequeNumber == null) {
			if (other.chequeNumber != null)
				return false;
		} else if (!chequeNumber.equals(other.chequeNumber))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (firm == null) {
			if (other.firm != null)
				return false;
		} else if (!firm.equals(other.firm))
			return false;
		if (fiscalYear == null) {
			if (other.fiscalYear != null)
				return false;
		} else if (!fiscalYear.equals(other.fiscalYear))
			return false;
		if (journalVoucher == null) {
			if (other.journalVoucher != null)
				return false;
		} else if (!journalVoucher.equals(other.journalVoucher))
			return false;
		if (jv_type == null) {
			if (other.jv_type != null)
				return false;
		} else if (!jv_type.equals(other.jv_type))
			return false;
		if (keepAsAdvance != other.keepAsAdvance)
			return false;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		if (Double.doubleToLongBits(receiptAmount) != Double
				.doubleToLongBits(other.receiptAmount))
			return false;
		if (receiptBy == null) {
			if (other.receiptBy != null)
				return false;
		} else if (!receiptBy.equals(other.receiptBy))
			return false;
		if (receiptDate == null) {
			if (other.receiptDate != null)
				return false;
		} else if (!receiptDate.equals(other.receiptDate))
			return false;
		if (receiptDateBs == null) {
			if (other.receiptDateBs != null)
				return false;
		} else if (!receiptDateBs.equals(other.receiptDateBs))
			return false;
		if (receiptDetailsList == null) {
			if (other.receiptDetailsList != null)
				return false;
		} else if (!receiptDetailsList.equals(other.receiptDetailsList))
			return false;
		if (receiptId != other.receiptId)
			return false;
		if (receiptNo != other.receiptNo)
			return false;
		if (receiptPerson == null) {
			if (other.receiptPerson != null)
				return false;
		} else if (!receiptPerson.equals(other.receiptPerson))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (status != other.status)
			return false;
		if (studentModel == null) {
			if (other.studentModel != null)
				return false;
		} else if (!studentModel.equals(other.studentModel))
			return false;
		if (trxnFrom == null) {
			if (other.trxnFrom != null)
				return false;
		} else if (!trxnFrom.equals(other.trxnFrom))
			return false;
		if (updateCount != other.updateCount)
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		return true;
	}*/

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (receiptId ^ (receiptId >>> 32));
		return result;
	}

	/*(non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)*/

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ReceiptModel)) {
			return false;
		}
		ReceiptModel other = (ReceiptModel) obj;
		if (receiptId != other.receiptId) {
			return false;
		}
		return true;
	}



}
