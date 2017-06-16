package np.info.dpshkhnl.model.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * @author nebula
 * 
 */

@Entity
@Table(name = "acc_head_mcg", uniqueConstraints = { @UniqueConstraint(columnNames = { "acc_code" }, name = "unq_acc_code_acc_head_mcg") })

public class AccHead implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ACC_HEAD_ID = "AccHeadMcg.findByAccHeadId";
	public static final String GET_MAX_CODE = "UniqueAccCode.generateNewOnThisAccRoot";
	public static final String Get_Acc_Alias_On_Select = "AccHeadMcg.Get_Acc_Alias_On_Select";
	public static final String GET_ALL_BY_ACC_CODE = "AccHeadMcg.GET_ALL_BY_ACC_CODE";
	public static final String GET_ALL_BANK_ACCOUNTS = "AccHeadMcg.findBankAccounts";
	public static final String CHECK_DUPICATE_NAME = "AccHeadMcg.findCheckDuplicateAccHeadName";
	public static final String LIST_ROOT_NODES = "AccHeadMcg.listRoots";
	public static final String LIST_CHILDREN = "AccHeadMcg.getChildren";
	public static final String FIND_ALL_NON_ROOT = "AccHeadMcg.findAllNotRoot";
	public static final String FIND_ALL_NON_ROOT_LIST = "AccHeadMcg.findAllNonRoot";
	public static final String FIND_ALL_CASH_IN_HAND = "AccHeadMcg.findAllCashInHand";
	public static final String LIST_PARENT_NODE_ONLY = "AccHeadMcg.listParentNodeOnly";
	public static final String LIST_NON_PARENT_NODE_ONLY = "AccHeadMcg.listNonParentNodeOnly";
	public static final String GET_BY_ACC_NAME = "AccHeadMcg.getAccHeadByName";
	/*
	 * When we allow the Hibernate to create the database table, the columns are
	 * created according to alphabetic order of column names defined in @Column
	 * in entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acc_head_id")
	private int accHeadId;

	@Column(name = "br_id")
	private int brId;

	/**
	 * There are two methods to add unique constraint viz. [1.] at entity level
	 * (BEST for composite unique keys) syntax is :
	 * 
	 * @Table(name="rdbms_table_name", uniqueConstraints=
	 *                                                                 {@UniqueConstraint(columnNames={"col_1","col_2",...,"col_n"
	 * 
	 * 
	 * 
	 * }
	 *                                 )}) [2.] at field level (BEST for
	 *                                 individual unique keys) syntax is :
	 * @Column(name="col_x", unique=true,...)
	 * 
	 *                       NOTE: both of these methods create unique
	 *                       constraints using default naming provided by RDBMS,
	 *                       so, we have not discovered any way till date, to
	 *                       explicitly name the unique key such generated.
	 */

	@Column(name = "acc_code", nullable = false)
	private String accCode;

	private String alias;

	@Column(name = "acc_name", nullable = false, unique = true)
	private String accName;

	@Column(name = "acc_type", nullable = false)
	private int accType;

	@Column(nullable = false)
	private int parent;

	@Column(name = "acc_level")
	private int accLevel;

	@Column(name = "min_bal", columnDefinition = "Decimal(28,2) default '0.00'")
	private double minBal;

	private String remarks;

	@Column(name = "created_by")
	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private java.util.Date createdDate;

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "updated_by")
	private int updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "update_count")
	private int updateCount;

	private String drcr;

	@SuppressWarnings("unused")
	@Column(name = "jv_status", nullable = false)
	private int jvStatus;

	@Transient
	private String parentName;
	
	@Column(name = "is_sum_acc")
	private int isSumacc;

	/**
	 * @return the is_sum_acc
	 */
	public int getIs_sum_acc() {
		return isSumacc;
	}

	/**
	 * @param is_sum_acc the is_sum_acc to set
	 */
	public void setIs_sum_acc(int is_sum_acc) {
		this.isSumacc = is_sum_acc;
	}

	/* @JoinTable(name="acc_head_ledger") */
	@OneToOne(mappedBy = "accountHead", cascade = CascadeType.ALL)
	private Ledger ledgerMcg;

	public AccHead() {
	}

	public Ledger getLedger() {
		return ledgerMcg;
	}

	public void setLedger(Ledger ledger) {
		this.ledgerMcg = ledger;
	}

	public int getAccHeadId() {
		return accHeadId;
	}

	public void setAccHeadId(int accHeadId) {
		this.accHeadId = accHeadId;
	}

	public int getBrId() {
		return brId;
	}

	public void setBrId(int brId) {
		this.brId = brId;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public int getAccType() {
		return accType;
	}

	public void setAccType(int accType) {
		this.accType = accType;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getAccLevel() {
		return accLevel;
	}

	public void setAccLevel(int accLevel) {
		this.accLevel = accLevel;
	}

	public double getMinBal() {
		return minBal;
	}

	public void setMinBal(double minBal) {
		this.minBal = minBal;
	}

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

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	public String getDrcr() {
		return drcr;
	}

	public void setDrcr(String drcr) {
		this.drcr = drcr;
	}

	public int getJvStatus() {
		return jvStatus = 1; // Since Summation is true for all account heads by
								// default.
	}

	public void setJvStatus(int jvStatus) {
		this.jvStatus = jvStatus;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentNamePassed) {
		/*
		 * if(accHeadId>0)//i.e. if it is not root node, only then we set
		 * parent'Name { String
		 * parentName=(String)DirectSqlUtils.getSingleValueFromTable
		 * ("select acc_name from acc_head_mcg where acc_head_id="
		 * +accHeadId+""); this.parentName = parentName; }
		 */
		this.parentName = parentNamePassed;
	}

	@Override
	public int hashCode() {
		return getAccHeadId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AccHead) {
			AccHead accHeadMcg = (AccHead) obj;
			return accHeadMcg.getAccHeadId() == accHeadId;
		}

		return false;
	}

	@PrePersist
	protected void onCreate() {
		setCreatedDate(new Date());
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

}
