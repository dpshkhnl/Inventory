package np.info.dpshkhnl.model.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

//import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;

//import com.magnus.model.user.User;
//import com.magnus.model.util.FiscalYrModel;

/**
 * @author nebula
 * 
 */

@Entity
@Table(name = "ledger_mcg", uniqueConstraints = { @UniqueConstraint(name = "unqLedgerConstraint", columnNames = {
		"led_id", "fy_id", "jv_no", "jv_type" }) })
public class Ledger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String LIST_FOR_LEDGER_REPORT = "LedgerMcg.forLedgerReport";
	public static final String FIND_BROUGHT_DOWN_AMOUNT = "LedgerMcg.broughtDownAmt";
	public static final String FIND_BY_ACC_HEAD_ID = "Ledger.findByAccHeadId";
	public static final String FIND_BY_DATE = "Ledger.findByDate";
	public static final String FIND_BY_DATE_AND_ACC_TYPE = "Ledger.findByDateAndAccType";
	public static final String FIND_TRIAL_BY_DATE = "Ledger.findTrialByDate";
	public static final String FIND_CASH_LEDGER = "Ledger.FindCashLedger";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "led_id")
	private long ledId;

	/*
	 * @ManyToOne(fetch=FetchType.LAZY) //@JoinColumn(name="jv_no",
	 * referencedColumnName="jv_no")
	 * 
	 * @JoinColumns({@JoinColumn(name="jv_no", referencedColumnName="jv_no"),
	 * 
	 * @JoinColumn(name="fy_id",referencedColumnName="fy_id")})
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "jv_no", referencedColumnName = "jv_no"),
			@JoinColumn(name = "fy_id", referencedColumnName = "fy_id", insertable = false, updatable = false),
			@JoinColumn(name = "jv_type", referencedColumnName = "jv_type", insertable = false, updatable = false) })
	private JournalVoucherModel journalVourcher;

	@OneToOne
	@JoinColumn(name = "acc_code_id", referencedColumnName = "acc_head_id")
	private AccHead accountHead;

	@ManyToOne
	@JoinColumn(name = "to_acc_code_id", referencedColumnName = "acc_head_id")
	private AccHead toAccountHead;

	@Column(name = "dr_amt", columnDefinition = "Decimal(28,2) default '0.00'")
	private double drAmt;

	@Column(name = "cr_amt", columnDefinition = "Decimal(28,2) default '0.00'")
	private double crAmt;

	@Temporal(TemporalType.DATE)
	@Column(name = "posted_date")
	private Date postedDate;

	@Column(name = "created_date_np")
	private String createdDateNp;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "br_id")
	private int brId;

	@ManyToOne
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FiscalYrModel fiscalYear;

	@ManyToOne
	@JoinColumn(name = "jv_type", referencedColumnName = "cv_id")
	private CodeValue jvType;

	@Column(name = "is_opening")
	private int isOpening;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "posted_by", referencedColumnName="id") private User
	 * postedBy;
	 */

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "updated_by", referencedColumnName="id") private User
	 * updatedBy;
	 */

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@Version
	@Column(name = "update_count")
	private int updateCount;

	public Ledger() {
	}

	public long getLedId() {
		return ledId;
	}

	public void setLedId(int ledId) {
		this.ledId = ledId;
	}

	public double getDrAmt() {
		return drAmt;
	}

	public void setDrAmt(double drAmt) {
		this.drAmt = drAmt;
	}

	public double getCrAmt() {
		return crAmt;
	}

	public void setCrAmt(double crAmt) {
		this.crAmt = crAmt;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getCreatedDateNp() {
		return createdDateNp;
	}

	public void setCreatedDateNp(String createdDateNp) {
		this.createdDateNp = createdDateNp;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public AccHead getAccountHead() {
		return accountHead;
	}

	public void setAccountHead(AccHead accountHead) {
		this.accountHead = accountHead;
	}

	public AccHead getToAccountHead() {
		return toAccountHead;
	}

	public void setToAccountHead(AccHead toAccountHead) {
		this.toAccountHead = toAccountHead;
	}

	public int getBrId() {
		return brId;
	}

	public void setBrId(int brId) {
		this.brId = brId;
	}

	/*
	 * public FiscalYrModel getFiscalYear() { return fiscalYear; }
	 */

	/*
	 * public void setFiscalYear(FiscalYrModel fiscalYear) { this.fiscalYear =
	 * fiscalYear; }
	 */

	public int getIsOpening() {
		return isOpening;
	}

	public void setIsOpening(int isOpening) {
		this.isOpening = isOpening;
	}

	/*
	 * public User getPostedBy() { return postedBy; }
	 */

	/*
	 * public void setPostedBy(User postedBy) { this.postedBy = postedBy; }
	 */

	/*
	 * public User getUpdatedBy() { return updatedBy; }
	 */

	/*
	 * public void setUpdatedBy(User updatedBy) { this.updatedBy = updatedBy; }
	 */

	/*
	 * public JournalVoucherModel getJournalVourcher() { return journalVourcher;
	 * }
	 */

	/*
	 * public void setJournalVourcher(JournalVoucherModel journalVourcher) {
	 * this.journalVourcher = journalVourcher; }
	 */

	/**
	 * @return the jvType
	 */
	public CodeValue getJvType() {
		return jvType;
	}

	/**
	 * @param jvType
	 *            the jvType to set
	 */
	public void setJvType(CodeValue jvType) {
		this.jvType = jvType;
	}

	@Override
	public int hashCode() {
		return (int) getLedId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ledger) {
			Ledger ledgerMcg = (Ledger) obj;
			return ledgerMcg.getLedId() == ledId;
		}

		return false;
	}

	@PrePersist
	protected void onCreate() {
		// postedDate = new Date();
		createdDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

	public void setJournalVourcher(JournalVoucherModel journalVourcher) {
		this.journalVourcher = journalVourcher;
	}

	public JournalVoucherModel getJournalVourcher() {
		return journalVourcher;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @param ledId
	 *            the ledId to set
	 */
	public void setLedId(long ledId) {
		this.ledId = ledId;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void setFiscalYear(FiscalYrModel fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public FiscalYrModel getFiscalYear() {
		return fiscalYear;
	}

}
