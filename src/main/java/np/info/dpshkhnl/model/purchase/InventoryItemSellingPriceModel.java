/**
 * 
 */
package np.info.dpshkhnl.model.purchase;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import np.info.dpshkhnl.model.admin.UnitModel;
import np.info.dpshkhnl.model.product.ItemProductModel;

/**
 * @author suman
 * 
 */
@Entity
@Table(name = "inv_item_selling_price")
@NamedQueries({
		@NamedQuery(name = "InventoryItemSellingPriceModel.findUnitByInvMasterId", query = "select u from InventoryItemSellingPriceModel u where u.invMaster.invMasterId = :invMasterId"),
		@NamedQuery(name = "InventoryItemSellingPriceModel.findSellingPriceByProductId", query = "select u from InventoryItemSellingPriceModel u where u.prod.productId = :prodId") })
public class InventoryItemSellingPriceModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String GET_UNIT_BY_INV_MASTERID = "InventoryItemSellingPriceModel.findUnitByInvMasterId";
	public final static String GET_SELLING_PRICE_BY_PRODUCT_ID = "InventoryItemSellingPriceModel.findSellingPriceByProductId";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "sell_price")
	private double sellPrice;

	@ManyToOne
	@JoinColumn(name = "inv_master_id", referencedColumnName = "inv_master_id")
	private InventoryMasterModel invMaster;

	@ManyToOne
	@JoinColumn(name = "sale_unit", referencedColumnName = "unit_id")
	private UnitModel invUnit;

	@ManyToOne
	@JoinColumn(name = "prod_id", referencedColumnName = "product_id")
	private ItemProductModel prod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_dt")
	private java.util.Date createdDt;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the sellPrice
	 */
	public double getSellPrice() {
		return sellPrice;
	}

	/**
	 * @param sellPrice
	 *            the sellPrice to set
	 */
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * @return the invMaster
	 */
	public InventoryMasterModel getInvMaster() {
		return invMaster;
	}

	/**
	 * @param invMaster
	 *            the invMaster to set
	 */
	public void setInvMaster(InventoryMasterModel invMaster) {
		this.invMaster = invMaster;
	}

	/**
	 * @return the invUnit
	 */
	public UnitModel getInvUnit() {
		return invUnit;
	}

	/**
	 * @param invUnit
	 *            the invUnit to set
	 */
	public void setInvUnit(UnitModel invUnit) {
		this.invUnit = invUnit;
	}

	/**
	 * @return the createdDt
	 */
	public Date getCreatedDt() {
		return createdDt;
	}

	/**
	 * @param createdDt
	 *            the createdDt to set
	 */
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
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
		result = prime * result
				+ ((createdDt == null) ? 0 : createdDt.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((invMaster == null) ? 0 : invMaster.hashCode());
		result = prime * result + ((invUnit == null) ? 0 : invUnit.hashCode());
		long temp;
		temp = Double.doubleToLongBits(sellPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItemSellingPriceModel other = (InventoryItemSellingPriceModel) obj;
		if (createdDt == null) {
			if (other.createdDt != null)
				return false;
		} else if (!createdDt.equals(other.createdDt))
			return false;
		if (id != other.id)
			return false;
		if (invMaster == null) {
			if (other.invMaster != null)
				return false;
		} else if (!invMaster.equals(other.invMaster))
			return false;
		if (invUnit == null) {
			if (other.invUnit != null)
				return false;
		} else if (!invUnit.equals(other.invUnit))
			return false;
		if (Double.doubleToLongBits(sellPrice) != Double
				.doubleToLongBits(other.sellPrice))
			return false;
		return true;
	}

	

	@PrePersist
	protected void onCreate() {
		createdDt = new Date();

	}

    public ItemProductModel getProd() {
        return prod;
    }

    public void setProd(ItemProductModel prod) {
        this.prod = prod;
    }

}
