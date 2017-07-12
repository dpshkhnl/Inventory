/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.account;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import np.info.dpshkhnl.model.account.AccHead;
import np.info.dpshkhnl.model.account.Accounts;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class AccountEJB  extends GenericDAO<Accounts> {
	public AccountEJB() {
		super(Accounts.class);
	}
        
          public Accounts findbyAccountName(String accountName)
        {
            
            String qry = "SELECT i from Accounts  i where i.accName like '"+accountName+"'";
            
           Query query = getEntityManager().createQuery(qry);
           
           
		return (Accounts) query.getSingleResult();
        }
          
           
         public List<Accounts> findbyRemarks(String remarks)
        {
            
            String qry = "SELECT i from Accounts  i where i.remarks ='"+remarks+"'";
            
           Query query = getEntityManager().createQuery(qry);
           
           
		return  query.getResultList();
        }
         
}
