/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.purchase;

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
     
        public List<InventoryInvoiceModel> findbySupplierId(int supId,int statusId)
        {
            
            String qry = "SELECT i from InventoryInvoiceModel  i ";
            String cond ="";
            if(supId != 0)
            {
                cond +="  i.supId.Id = " + supId;
            }
            if(statusId!= 0)
            {
                cond+=cond.equals("")?"":" and ";
                cond +="  i.status.cvId = "+statusId;
            }
            qry += cond.equals("")?"":" where "+cond;
           Query query = getEntityManager().createQuery(qry);
           
           
		return query.getResultList();
        }
    
}
