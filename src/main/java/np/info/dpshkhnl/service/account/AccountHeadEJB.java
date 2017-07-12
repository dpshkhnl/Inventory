/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.account;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import np.info.dpshkhnl.model.account.AccHead;
import np.info.dpshkhnl.model.account.Accounts;
import np.info.dpshkhnl.model.purchase.InventoryInvoiceModel;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */

@Stateless
public class AccountHeadEJB  extends GenericDAO<AccHead> {
	public AccountHeadEJB() {
		super(AccHead.class);
	}
        
        public List<AccHead> findbyAccount(String accName)
        {
            
            String qry = "SELECT i from AccHead  i where i.accType.accName ='"+accName+"'";
            
           Query query = getEntityManager().createQuery(qry);
           
           
		return  query.getResultList();
        }
       
        public void updateAccountHeadBalance(int accHeadId , double amt,String drcr)
        {
            AccHead accHead = find(accHeadId);
            if(drcr.equals("cr"))
            {
                accHead.setCrBalance(accHead.getCrBalance()+amt);
            }else
            {
                accHead.setDrBalance(accHead.getDrBalance()+amt);
            }
            if (accHead.getCrBalance() == 0 || accHead.getDrBalance()==0 )
            {
                
            }else
            {
                if(accHead.getCrBalance() >= accHead.getDrBalance())
                {
                    accHead.setCrBalance(accHead.getCrBalance() - accHead.getDrBalance());
                    accHead.setDrBalance(0);
                }
                else
                {
                     accHead.setDrBalance(accHead.getDrBalance() - accHead.getCrBalance());
                    accHead.setCrBalance(0);
                }
            }
            update(accHead);
            
        }
         
}