/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.model.purchase;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import np.info.dpshkhnl.model.account.CodeValue;
import np.info.dpshkhnl.model.admin.PartnerModel;


@Entity
@Table(name = "inv_invoice")
//@NamedQueries({
//		@NamedQuery(name = "InventoryInvoiceModel.findAllInventoryInfo", query = "SELECT i FROM InventoryInvoiceModel i ORDER BY i.supId.suppName"),
//		@NamedQuery(name = "InventoryInvoiceModel.getInvoiceByDate", query = "SELECT i FROM InventoryInvoiceModel i WHERE i.invDtEn BETWEEN :fromDate AND :toDate AND i.status.cvId =:cvId"),
//		@NamedQuery(name = "InventoryInvoiceModel.getInvoiceByStatus", query = "SELECT i FROM InventoryInvoiceModel i WHERE i.status.cvId =:cvId"),
//		@NamedQuery(name = "InventoryInvoiceModel.getInvoiceByDateNew", query = "SELECT i FROM InventoryInvoiceModel i WHERE i.invDtEn BETWEEN :fromDate AND :toDate and i.pending_status=1"),
//		@NamedQuery(name = "InventoryInvoiceModel.getInvoiceDueByDate", query = "select i from InventoryInvoiceModel i where i.createdDate BETWEEN :fromDate AND :toDate and i.pending_status=0 "
//				+ "and i.status.cvLbl='Unpaid'"),
//		@NamedQuery(name = "InventoryInvoiceModel.getInvoiceDueBySupplier", query = "select i from InventoryInvoiceModel i where i.createdDate BETWEEN :fromDate AND :toDate and i.pending_status=0 "
//				+ "and i.status.cvLbl='Unpaid' "
//				+ "and i.supId.suppId=:supplierId"),
//		@NamedQuery(name = "InventoryInvoiceModel.getPaidInvoice", query = "select i from InventoryInvoiceModel i where i.status.cvLbl='Paid'") })
public class InventoryInvoiceModel implements Serializable {
	private static final long serialVersionUID = 1L;

//	public static final String GET_INVOICE_BY_STATUS = "InventoryInvoiceModel.getInvoiceByStatus";
//	public static final String GET_INVOICE_BY_DATE = "InventoryInvoiceModel.getInvoiceByDate";
//	public static final String GET_INVOICE_BY_DATE_NEW = "InventoryInvoiceModel.getInvoiceByDateNew";
//	public static final String GET_INVOICE_DUE_BY_DATE = "InventoryInvoiceModel.getInvoiceDueByDate";
//	public static final String GET_INVOICE_DUE_BY_SUPPLIER = "InventoryInvoiceModel.getInvoiceDueBySupplier";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id", unique = true)
	private int invId;

	@Column(name = "invoice_no")
	private String invNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "supplier_id")
	private PartnerModel supId;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<PurchasePaymentModel> purPaymentList;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<PurchaseDetailModel> lstPurchase;

	@OneToMany(mappedBy = "invInvoiceStk", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<InventoryMasterModel> invMasterList;

	@Column(name = "invoice_dt_np")
	private String invDtNp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "invoice_dt_en")
	private Date invDtEn;

	@Column(name = "invoice_amt", nullable = false)
	private Double invAmt;

	@Column(name = "invoice_vat_amt")
	private Double invVatAmt;
        
        @Column(name = "invoice_discount_amt")
	private Double invDiscountAmt;

    public Double getInvDiscountAmt() {
        return invDiscountAmt;
    }

    public void setInvDiscountAmt(Double invDiscountAmt) {
        this.invDiscountAmt = invDiscountAmt;
    }
        

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name = "update_count")
	private int updateCount;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", referencedColumnName = "cv_id")
	private CodeValue status;

	@Column(name = "payment_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date payment_date;

	@Column(name = "pending_status")
	private boolean pending_status;

	@Column(name = "paid_amount")
	private Double paidAmount;

	public boolean isPending_status() {
		return pending_status;
	}

	public void setPending_status(boolean pending_status) {
		this.pending_status = pending_status;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}


	public CodeValue getStatus() {
		return status;
	}

	public void setStatus(CodeValue status) {
		this.status = status;
	}

	public int getInvId() {
		return invId;
	}

	public void setInvId(int invId) {
		this.invId = invId;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	

	public List<PurchasePaymentModel> getPurPaymentList() {
		return purPaymentList;
	}

	public void setPurPaymentList(List<PurchasePaymentModel> purPaymentList) {
		this.purPaymentList = purPaymentList;
	}

	

	public List<InventoryMasterModel> getInvMasterList() {
		return invMasterList;
	}

	public void setInvMasterList(List<InventoryMasterModel> invMasterList) {
		this.invMasterList = invMasterList;
	}

	public String getInvDtNp() {
		return invDtNp;
	}

	public void setInvDtNp(String invDtNp) {
		this.invDtNp = invDtNp;
	}

	public Date getInvDtEn() {
		return invDtEn;
	}

	public void setInvDtEn(Date invDtEn) {
		this.invDtEn = invDtEn;
	}

	public Double getInvAmt() {
		return invAmt;
	}

	public void setInvAmt(Double invAmt) {
		this.invAmt = invAmt;
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

	public Double getInvVatAmt() {
		return invVatAmt;
	}

	public void setInvVatAmt(Double invVatAmt) {
		this.invVatAmt = invVatAmt;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

    public PartnerModel getSupId() {
        return supId;
    }

    public void setSupId(PartnerModel supId) {
        this.supId = supId;
    }

    public List<PurchaseDetailModel> getLstPurchase() {
        return lstPurchase;
    }

    public void setLstPurchase(List<PurchaseDetailModel> lstPurchase) {
        this.lstPurchase = lstPurchase;
    }

	


}
