/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.model.admin;


import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import np.info.dpshkhnl.model.account.AccHead;
/**
 *
 * @author Dpshkhnl
 */
    @Entity
@Table(name="patner")
public class PartnerModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int Id;
	
	@Column(name="created_By")
	private int createdBy;

	@Column(name="created_Date")
	private String createdDate;

	
	@Column(name="member_address")
	private String memberAddress;

	
     @JoinColumn(name = "acc_head" )
    @ManyToOne( cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private AccHead accountHead;
    
    
    @JoinColumn(name = "route_id" )
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private RouteManagementModel route;   

	@Column(name="member_contactNo")
	private String memberContactNo;

	@Column(name="member_name")
	private String memberName;
        
        @Column(name="contact_person")
	private String contactPerson;

	@Column(name="updated_By")
	private int updatedBy;

	@Column(name="updated_count")
	private int updatedCount;

	
	@Column(name = "updated_Date")
	private String updatedDate;
	
	@Column(name = "members_id")
	private String memberIds;


        @Column(name = "remarks")
	private String remarks;

        
        @Column(name = "pan_vat", nullable = false)
	private String panVat;
	
        @Column(name = "anta_sulka_no", nullable = false)
	private String antaSulkaNo;
        
       @Column(name = "supplier")
    private Boolean supplier;
    @Column(name = "customer")
    private Boolean customer;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }
 

    public String getMemberContactNo() {
        return memberContactNo;
    }

    public void setMemberContactNo(String memberContactNo) {
        this.memberContactNo = memberContactNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getUpdatedCount() {
        return updatedCount;
    }

    public void setUpdatedCount(int updatedCount) {
        this.updatedCount = updatedCount;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds;
    }

    public String getPanVat() {
        return panVat;
    }

    public void setPanVat(String panVat) {
        this.panVat = panVat;
    }

    public String getAntaSulkaNo() {
        return antaSulkaNo;
    }

    public void setAntaSulkaNo(String antaSulkaNo) {
        this.antaSulkaNo = antaSulkaNo;
    }

    public Boolean getSupplier() {
        return supplier;
    }

    public void setSupplier(Boolean supplier) {
        this.supplier = supplier;
    }

    public Boolean getCustomer() {
        return customer;
    }

    public void setCustomer(Boolean customer) {
        this.customer = customer;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public RouteManagementModel getRoute() {
        return route;
    }

    public void setRoute(RouteManagementModel route) {
        this.route = route;
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

	
	
}
