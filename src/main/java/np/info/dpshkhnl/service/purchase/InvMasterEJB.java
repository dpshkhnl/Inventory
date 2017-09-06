/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.purchase;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import np.info.dpshkhnl.model.purchase.InventoryInvoiceModel;
import np.info.dpshkhnl.model.purchase.InventoryMasterModel;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class InvMasterEJB extends GenericDAO<InventoryMasterModel> {

    public InvMasterEJB() {
        super(InventoryMasterModel.class);
    } 
    
    public List<InventoryMasterModel> findbyProductId(int productId) {

        String qry = "SELECT i from InventoryMasterModel  i where (i.inQty- i.outQty) >0 and i.productId.productId ="+productId+" order by i.invMasterId ASC";
        
        Query query = getEntityManager().createQuery(qry);

        return query.getResultList();
    }
}
