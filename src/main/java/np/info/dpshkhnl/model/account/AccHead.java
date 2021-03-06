package np.info.dpshkhnl.model.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "acc_heads", uniqueConstraints = { @UniqueConstraint(columnNames = { "acc_code" }, name = "unq_acc_code_acc_head_mcg") })

public class AccHead implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acc_head_id")
	private int accHeadId;



	@Column(name = "acc_code", nullable = false)
	private String accCode;

	private String alias;

	@Column(name = "acc_name", nullable = false, unique = true)
	private String accName;

        @OneToOne
	@JoinColumn(name = "acc_type", nullable = false)
	private Accounts accType;

	@Column(name = "min_bal", columnDefinition = "Decimal(28,2) default '0.00'")
	private double minBal;
        
        @Column(name = "dr_bal", columnDefinition = "Decimal(28,2) default '0.00'")
	private double drBalance;
        
        @Column(name = "cr_bal", columnDefinition = "Decimal(28,2) default '0.00'")
	private double crBalance;

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

	
	@JoinColumn(name="acc_head_ledger") 
	@OneToOne( cascade = CascadeType.ALL)
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

    public Accounts getAccType() {
        return accType;
    }

    public void setAccType(Accounts accType) {
        this.accType = accType;
    }

    public double getDrBalance() {
        return drBalance;
    }

    public void setDrBalance(double drBalance) {
        this.drBalance = drBalance;
    }

    public double getCrBalance() {
        return crBalance;
    }

    public void setCrBalance(double crBalance) {
        this.crBalance = crBalance;
    }

}
