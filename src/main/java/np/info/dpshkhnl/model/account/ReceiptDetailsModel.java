package np.info.dpshkhnl.model.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="receipt_details")

@NamedQueries({
/*@NamedQuery(name="ReceiptDetailsModel.findByReceiptNo",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "WHERE r.receiptNo=:receiptNoPassed AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByReceiptDt",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "WHERE r.receiptDate BETWEEN :frmDt AND :toD AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByParticularsId",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.feeSchemeDetailModel f "
+ "WHERE f.planId=:particularid AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByStudent",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "WHERE r.studentModel.studentId=:stuIdPassed AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByMember",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "WHERE r.member.memNo=:memNoPassed AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByFirm",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "WHERE r.firm.firmId=:firmIdPassed AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByStudentDateRptNoParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "JOIN rd.feeSchemeDetailModel f "
+ " WHERE r.studentModel.studentId=:stuIdPassed "
+ "AND r.receiptNo=:receiptNoPassed "
+ "AND (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND f.planId=:particularid AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByStudentDateReceiptNo",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ " WHERE r.studentModel.studentId=:stuIdPassed "
+ "AND r.receiptNo=:receiptNoPassed "
+ "AND (r.receiptDate BETWEEN :frmDt AND :toD) AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByStuDateParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "JOIN rd.feeSchemeDetailModel f "
+ " WHERE r.studentModel.studentId=:stuIdPassed "
+ "AND (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND f.planId=:particularid AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByStuReceiptNoParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "JOIN rd.feeSchemeDetailModel f "
+ " WHERE r.studentModel.studentId=:stuIdPassed "
+ "AND f.planId=:particularid "
+ "AND r.receiptNo=:receiptNoPassed AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByMemDateRptNoParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "JOIN rd.feeSchemeDetailModel f "
+ " WHERE r.member.memNo=:memNoPassed "
+ "AND r.receiptNo=:receiptNoPassed "
+ "AND (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND f.planId=:particularid AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByMemDateReceiptNo",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ " WHERE r.member.memNo=:memNoPassed "
+ "AND r.receiptNo=:receiptNoPassed "
+ "AND (r.receiptDate BETWEEN :frmDt AND :toD) AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByMemDateParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "JOIN rd.feeSchemeDetailModel f "
+ " WHERE r.member.memNo=:memNoPassed "
+ "AND (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND f.planId=:particularid AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByMemReceiptNoParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "JOIN rd.feeSchemeDetailModel f "
+ " WHERE r.member.memNo=:memNoPassed "
+ "AND f.planId=:particularid "
+ "AND r.receiptNo=:receiptNoPassed AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByFirmDateRptNoParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "JOIN rd.feeSchemeDetailModel f "
+ " WHERE r.firm.firmId=:firmIdPassed "
+ "AND r.receiptNo=:receiptNoPassed "
+ "AND (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND f.planId=:particularid AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByFirmDateReceiptNo",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ " WHERE r.firm.firmId=:firmIdPassed "
+ "AND r.receiptNo=:receiptNoPassed "
+ "AND (r.receiptDate BETWEEN :frmDt AND :toD) "),
@NamedQuery(name="ReceiptDetailsModel.findByFirmDateParticulars AND rd.feeSchemeDetailModel IS NOT NULL",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "JOIN rd.feeSchemeDetailModel f "
+ " WHERE r.firm.firmId=:firmIdPassed "
+ "AND (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND f.planId=:particularid AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByFirmReceiptNoParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r "
+ "JOIN rd.feeSchemeDetailModel f "
+ " WHERE r.firm.firmId=:firmIdPassed "
+ "AND f.planId=:particularid "
+ "AND r.receiptNo=:receiptNoPassed AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByStuDate",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "WHERE (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND  r.studentModel IS NOT NULL AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByMemDate",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "WHERE (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND  r.member IS NOT NULL AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByFirmDate",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "WHERE (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND  r.firm IS NOT NULL AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByStuParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "JOIN rd.feeSchemeDetailModel f "
+ "WHERE f.planId=:particularid "
+ "AND r.studentModel IS NOT NULL AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByMemParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "JOIN rd.feeSchemeDetailModel f "
+ "WHERE f.planId=:particularid "
+ "AND r.member IS NOT NULL AND rd.feeSchemeDetailModel IS NOT NULL"),
@NamedQuery(name="ReceiptDetailsModel.findByFirmParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "JOIN rd.feeSchemeDetailModel f "
+ "WHERE f.planId=:particularid "
+ "AND r.firm IS NOT NULL AND rd.feeSchemeDetailModel IS NOT NULL"),

@NamedQuery(name="ReceiptDetailsModel.findByStuIdDate",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "WHERE (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND  r.studentModel.studentId=:stuIdPassed AND rd.feeSchemeDetailModel IS NOT NULL"),*/
/*@NamedQuery(name="ReceiptDetailsModel.findByMemNoDate",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "WHERE (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND  r.member.memNo=:memNoPassed AND rd.feeSchemeDetailModel IS NOT NULL"),*/
/*@NamedQuery(name="ReceiptDetailsModel.findByFirmNoDate",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "WHERE (r.receiptDate BETWEEN :frmDt AND :toD) "
+ "AND  r.firm.firmId=:firmIdPassed AND rd.feeSchemeDetailModel IS NOT NULL"),*/
/*@NamedQuery(name="ReceiptDetailsModel.findByStuIdParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "JOIN rd.feeSchemeDetailModel f "
+ "WHERE f.planId=:particularid "
+ "AND r.studentModel.studentId=:stuIdPassed AND rd.feeSchemeDetailModel IS NOT NULL"),*/
/*@NamedQuery(name="ReceiptDetailsModel.findByMemNoParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "JOIN rd.feeSchemeDetailModel f "
+ "WHERE f.planId=:particularid "
+ "AND r.member.memNo=:memNoPassed AND rd.feeSchemeDetailModel IS NOT NULL"),*/
/*@NamedQuery(name="ReceiptDetailsModel.findByFirmNoParticulars",
query="SELECT rd FROM ReceiptDetailsModel rd "
+ "JOIN rd.receiptModel r  "
+ "JOIN rd.feeSchemeDetailModel f "
+ "WHERE f.planId=:particularid "
+ "AND r.firm.firmId=:firmIdPassed AND rd.feeSchemeDetailModel IS NOT NULL")*/
})
public class ReceiptDetailsModel implements Serializable{


	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_RECEIPT_NO = "ReceiptDetailsModel.findByReceiptNo";
	public static final String FIND_BY_RECEIPT_DT = "ReceiptDetailsModel.findByReceiptDt";
	public static final String FIND_BY_PARTICULARS = "ReceiptDetailsModel.findByParticularsId";

	public static final String FIND_BY_STUDENT_ID ="ReceiptDetailsModel.findByStudent";
	public static final String FIND_BY_MEM_NO = "ReceiptDetailsModel.findByMember";
	public static final String FIND_BY_FIRM_ID = "ReceiptDetailsModel.findByFirm";

	public static final String FIND_BY_STU_DATE_RECEIPT_NO_PARTICULARS = "ReceiptDetailsModel.findByStudentDateRptNoParticulars";
	public static final String FIND_BY_STU_DATE_RECEIPT_NO=	"ReceiptDetailsModel.findByStudentDateReceiptNo";
	public static final String FIND_BY_STU_DATE_PARTICULARS = "ReceiptDetailsModel.findByStuDateParticulars";
	public static final String FIND_BY_STU_RECEIPT_NO_PARTICULARS = "ReceiptDetailsModel.findByStuReceiptNoParticulars";

	public static final String FIND_BY_MEM_DATE_RECEIPT_NO_PARTICULARS = "ReceiptDetailsModel.findByMemDateRptNoParticulars";
	public static final String FIND_BY_MEM_DATE_RECEIPT_NO	=	"ReceiptDetailsModel.findByMemDateReceiptNo";
	public static final String FIND_BY_MEM_DATE_PARTICULARS = "ReceiptDetailsModel.findByMemDateParticulars";
	public static final String FIND_BY_MEM_RECEIPT_NO_PARTICULARS = "ReceiptDetailsModel.findByMemReceiptNoParticulars";

	public static final String FIND_BY_FIRM_DATE_RECEIPT_NO_PARTICULARS = "ReceiptDetailsModel.findByFirmDateRptNoParticulars";
	public static final String FIND_BY_FIRM_DATE_RECEIPT_NO	=	"ReceiptDetailsModel.findByFirmDateReceiptNo";
	public static final String FIND_BY_FIRM_DATE_PARTICULARS = "ReceiptDetailsModel.findByFirmDateParticulars";
	public static final String FIND_BY_FIRM_RECEIPT_NO_PARTICULARS = "ReceiptDetailsModel.findByFirmReceiptNoParticulars";

	public static final String FIND_BY_STU_DATE	=	"ReceiptDetailsModel.findByStuDate";
	public static final String FIND_BY_MEM_DATE =	"ReceiptDetailsModel.findByMemDate";
	public static final String FIND_BY_FIRM_DATE	=	"ReceiptDetailsModel.findByFirmDate";

	public static final String FIND_BY_STU_PARTICULARS	=	"ReceiptDetailsModel.findByStuParticulars";
	public static final String FIND_BY_MEM_PARTICULARS	=	"ReceiptDetailsModel.findByMemParticulars";
	public static final String FIND_BY_FIRM_PARTICULARS	=	"ReceiptDetailsModel.findByFirmParticulars";

	public static final String FIND_BY_STUID_DATE	=	"ReceiptDetailsModel.findByStuIdDate";
	public static final String FIND_BY_MEMNO_DATE =	"ReceiptDetailsModel.findByMemNoDate";
	public static final String FIND_BY_FIRMNO_DATE	=	"ReceiptDetailsModel.findByFirmNoDate";

	public static final String FIND_BY_STUID_PARTICULARS	=	"ReceiptDetailsModel.findByStuIdParticulars";
	public static final String FIND_BY_MEMNO_PARTICULARS	=	"ReceiptDetailsModel.findByMemNoParticulars";
	public static final String FIND_BY_FIRMNO_PARTICULARS	=	"ReceiptDetailsModel.findByFirmNoParticulars";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receipt_det_id")
	private long receiptDetailsId;

	@ManyToOne
	@JoinColumns({@JoinColumn(name="receipt_id", referencedColumnName="receipt_id"),@JoinColumn(name="receipt_no", referencedColumnName="receipt_no"), @JoinColumn(name="fy_id", referencedColumnName="fy_id"),@JoinColumn(name="jv_type",referencedColumnName="jv_type")})
	private ReceiptModel receiptModel;

	@OneToOne
	@JoinColumn(name="from_acc_id", referencedColumnName="acc_head_id")
	private AccHead accHead;

	/*@OneToOne
	@JoinColumn(name="scheme_id", referencedColumnName="plan_id")
	private FeeSchemeDetailModel feeSchemeDetailModel;*/

	@Column(name = "dr_amt",columnDefinition="Decimal(28,2) default '0.00'", nullable = false)
	private double drAmt;
	@Column(name = "cr_amt",columnDefinition="Decimal(28,2) default '0.00'", nullable = false)
	private double crAmt;

	@Column(name="remarks")
	private String remarks;

	@Column(name="qty")
	private int qty;

	public long getReceiptDetailsId() {
		return receiptDetailsId;
	}

	public void setReceiptDetailsId(long receiptDetailsId) {
		this.receiptDetailsId = receiptDetailsId;
	}

	public ReceiptModel getReceiptModel() {
		return receiptModel;
	}

	public void setReceiptModel(ReceiptModel receiptModel) {
		this.receiptModel = receiptModel;
	}


	/**
	 * @return the accHead
	 */
	public AccHead getAccHead() {
		return accHead;
	}

	/**
	 * @param accHead the accHead to set
	 */
	public void setAccHead(AccHead accHead) {
		this.accHead = accHead;
	}

	/**
	 * @return the drAmt
	 */
	public double getDrAmt() {
		return drAmt;
	}

	/**
	 * @param drAmt the drAmt to set
	 */
	public void setDrAmt(double drAmt) {
		this.drAmt = drAmt;
	}

	/**
	 * @return the crAmt
	 */
	public double getCrAmt() {
		return crAmt;
	}

	/**
	 * @param crAmt the crAmt to set
	 */
	public void setCrAmt(double crAmt) {
		this.crAmt = crAmt;
	}

	/**
	 * @return the feeSchemeDetailModel
	 *//*
	public FeeSchemeDetailModel getFeeSchemeDetailModel() {
		return feeSchemeDetailModel;
	}

	  *//**
	  * @param feeSchemeDetailModel the feeSchemeDetailModel to set
	  *//*
	public void setFeeSchemeDetailModel(FeeSchemeDetailModel feeSchemeDetailModel) {
		this.feeSchemeDetailModel = feeSchemeDetailModel;
	}*/

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accHead == null) ? 0 : accHead.hashCode());
		long temp;
		temp = Double.doubleToLongBits(crAmt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(drAmt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
		+ (int) (receiptDetailsId ^ (receiptDetailsId >>> 32));
		result = prime * result
		+ ((receiptModel == null) ? 0 : receiptModel.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ReceiptDetailsModel)) {
			return false;
		}
		ReceiptDetailsModel other = (ReceiptDetailsModel) obj;
		if (accHead == null) {
			if (other.accHead != null) {
				return false;
			}
		} else if (!accHead.equals(other.accHead)) {
			return false;
		}
		if (Double.doubleToLongBits(crAmt) != Double
				.doubleToLongBits(other.crAmt)) {
			return false;
		}
		if (Double.doubleToLongBits(drAmt) != Double
				.doubleToLongBits(other.drAmt)) {
			return false;
		}
		if (receiptDetailsId != other.receiptDetailsId) {
			return false;
		}
		if (receiptModel == null) {
			if (other.receiptModel != null) {
				return false;
			}
		} else if (!receiptModel.equals(other.receiptModel)) {
			return false;
		}
		if (remarks == null) {
			if (other.remarks != null) {
				return false;
			}
		} else if (!remarks.equals(other.remarks)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */

}
