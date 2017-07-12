/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.purchase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import np.info.dpshkhnl.model.product.BrandModel;
import np.info.dpshkhnl.model.purchase.InventoryInvoiceModel;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class InvoiceEJB extends GenericDAO<InventoryInvoiceModel> {

    public InvoiceEJB() {
        super(InventoryInvoiceModel.class);
    }

    public List<InventoryInvoiceModel> findbySupplierId(int supId, int statusId) {

        String qry = "SELECT i from InventoryInvoiceModel  i ";
        String cond = "";
        if (supId != 0) {
            cond += "  i.supId.Id = " + supId;
        }
        if (statusId != 0) {
            cond += cond.equals("") ? "" : " and ";
            cond += "  i.status.cvId = " + statusId;
        }
        qry += cond.equals("") ? "" : " where " + cond;
        Query query = getEntityManager().createQuery(qry);

        return query.getResultList();
    }

    public List<InventoryInvoiceModel> findbyCriteria(Date dtFrm, Date dtTo, int customerid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String qry = "SELECT i from InventoryInvoiceModel  i ";
        String cond = "";
        if (customerid != 0) {
            cond += "  i.supId.Id = " + customerid;
        }
        if (dtFrm != null) {
            cond += cond.equals("") ? "" : " and ";
            cond += " i.invDtEn >= '" + sdf.format(dtFrm) + "'";
        }
        if (dtTo != null) {
            cond += cond.equals("") ? "" : " and ";
            cond += " i.invDtEn <= '" + sdf.format(dtTo) + "'";
        }
        qry += cond.equals("") ? "" : " where " + cond;
        Query query = getEntityManager().createQuery(qry);

        return query.getResultList();
    }

}
