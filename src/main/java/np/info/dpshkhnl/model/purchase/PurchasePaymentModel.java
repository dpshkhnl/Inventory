package np.info.dpshkhnl.model.purchase;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import np.info.dpshkhnl.model.account.AccHead;


@Entity
@Table(name = "inv_purchase_payment_mcg")
//@NamedQueries({ @NamedQuery(name = "PurchasePaymentModel.getPurchasePayementByInvoiceNo", 
//query = "SELECT i FROM PurchasePaymentModel i WHERE i.invInvoicePur =:invNo") })
public class PurchasePaymentModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//public static final String GET_PURCHASE_BY_INVOICE_NO = "PurchasePaymentModel.getPurchasePayementByInvoiceNo";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_Id", unique = true)
	private int payId;

	@Column(name = "payment_amt", nullable = false)
	private Double payAmt;

	@OneToOne
	@JoinColumn(referencedColumnName = "acc_head_id", name = "payment_acc")
	private AccHead payAcc;

	@Column(name = "remarks", nullable = false)
	private String remarks;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="invoice_id",referencedColumnName= "invoice_id")
	private InventoryInvoiceModel invInvoicePur;

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
	@Column(name = "update_count")
	private int updateCount;

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public Double getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}

    public AccHead getPayAcc() {
        return payAcc;
    }

    public void setPayAcc(AccHead payAcc) {
        this.payAcc = payAcc;
    }

	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public InventoryInvoiceModel getInvInvoicePur() {
		return invInvoicePur;
	}

	public void setInvInvoicePur(InventoryInvoiceModel invInvoicePur) {
		this.invInvoicePur = invInvoicePur;
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

	/*@PrePersist
	protected void onCreate() {
		createdDate = new Date();
	//	updatedDate = new Date();
	}*/

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + payId;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (!(obj instanceof PurchasePaymentModel)) {
			return false;
		}
		PurchasePaymentModel other = (PurchasePaymentModel) obj;
		if (payId != other.payId) {
			return false;
		}
		return true;
	}

}
