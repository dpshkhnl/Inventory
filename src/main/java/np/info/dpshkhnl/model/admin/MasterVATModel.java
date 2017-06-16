package np.info.dpshkhnl.model.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import np.info.dpshkhnl.model.account.AccHead;


@Entity
@Table(name = "master_vat_setting")
public class MasterVATModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int vatId;

    @OneToOne
    @JoinColumn(name = "purchase_acc", referencedColumnName = "acc_head_id")
    private AccHead purchaseAccount;

    @OneToOne
    @JoinColumn(name = "sales_acc", referencedColumnName = "acc_head_id")
    private AccHead salesAccount;

    @Column(name = "vat_percent")
    private double vatPercent;

    @OneToOne
    @JoinColumn(name = "service_ch_acc", referencedColumnName = "acc_head_id")
    private AccHead serviceChargeAccount;

    @Column(name = "service_ch_percent")
    private double serviceChargePercent;

    public int getVatId() {
	return vatId;
    }

    public void setVatId(int vatId) {
	this.vatId = vatId;
    }

   

    public double getVatPercent() {
	return vatPercent;
    }

    public void setVatPercent(double vatPercent) {
	this.vatPercent = vatPercent;
    }


    public double getServiceChargePercent() {
	return serviceChargePercent;
    }

    public void setServiceChargePercent(double serviceChargePercent) {
	this.serviceChargePercent = serviceChargePercent;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((purchaseAccount == null) ? 0 : purchaseAccount.hashCode());
	result = prime * result
		+ ((salesAccount == null) ? 0 : salesAccount.hashCode());
	result = prime
		* result
		+ ((serviceChargeAccount == null) ? 0 : serviceChargeAccount
			.hashCode());
	long temp;
	temp = Double.doubleToLongBits(serviceChargePercent);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + vatId;
	temp = Double.doubleToLongBits(vatPercent);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	MasterVATModel other = (MasterVATModel) obj;
	if (purchaseAccount == null) {
	    if (other.purchaseAccount != null)
		return false;
	} else if (!purchaseAccount.equals(other.purchaseAccount))
	    return false;
	if (salesAccount == null) {
	    if (other.salesAccount != null)
		return false;
	} else if (!salesAccount.equals(other.salesAccount))
	    return false;
	if (serviceChargeAccount == null) {
	    if (other.serviceChargeAccount != null)
		return false;
	} else if (!serviceChargeAccount.equals(other.serviceChargeAccount))
	    return false;
	if (Double.doubleToLongBits(serviceChargePercent) != Double
		.doubleToLongBits(other.serviceChargePercent))
	    return false;
	if (vatId != other.vatId)
	    return false;
	if (Double.doubleToLongBits(vatPercent) != Double
		.doubleToLongBits(other.vatPercent))
	    return false;
	return true;
    }

    public AccHead getPurchaseAccount() {
        return purchaseAccount;
    }

    public void setPurchaseAccount(AccHead purchaseAccount) {
        this.purchaseAccount = purchaseAccount;
    }

    public AccHead getSalesAccount() {
        return salesAccount;
    }

    public void setSalesAccount(AccHead salesAccount) {
        this.salesAccount = salesAccount;
    }

    public AccHead getServiceChargeAccount() {
        return serviceChargeAccount;
    }

    public void setServiceChargeAccount(AccHead serviceChargeAccount) {
        this.serviceChargeAccount = serviceChargeAccount;
    }

}
