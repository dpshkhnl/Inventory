/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import np.info.dpshkhnl.model.admin.PartnerModel;
import np.info.dpshkhnl.service.core.GenericDAO;
import np.info.dpshkhnl.util.DirectSqlUtils;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class PartnerEJB extends GenericDAO<PartnerModel> {

    public PartnerEJB() {
        super(PartnerModel.class);
    }

    public PartnerModel findbyAccountHead(int accHeadId) {

        String qry = "SELECT i from PartnerModel  i where i.accountHead.accHeadId =" + accHeadId + "";

        Query query = getEntityManager().createQuery(qry);
        PartnerModel partner;

        try {
            partner = (PartnerModel) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return partner;
    }

    public List<PartnerModel> findAllbyCustomer() {
        String qry = "";
        qry = "SELECT i from PartnerModel  i where i.customer =true";

        Query query = getEntityManager().createQuery(qry);
        return (List<PartnerModel>) query.getResultList();

    }
    
    public List<PartnerModel> findAllbyCustomerByRoute(int routeId) {
        String qry = "";
        qry = "SELECT i from PartnerModel  i where i.customer =true and i.route.branchId = "+routeId;

        Query query = getEntityManager().createQuery(qry);
        return (List<PartnerModel>) query.getResultList();

    }

    public List<PartnerModel> findAllbySupplier() {
        String qry = "";
        qry = "SELECT i from PartnerModel  i where i.supplier =true";

        Query query = getEntityManager().createQuery(qry);
        return (List<PartnerModel>) query.getResultList();

    }
    
    public int findMaxPartnerId() {
        String qry = "";
        qry = "SELECT Max(i.Id) from PartnerModel  i";

        Query query = getEntityManager().createQuery(qry);
        return (int) query.getSingleResult();

    }
    
    public List<PartnerModel> findAllCustomerCreditByToDate(Date toDate ,int routeId, int customerId) {
        String qry = "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        qry ="SELECT * from (SELECT acc_head_id,(SUM(dr_bal)-SUM(cr_bal)) as amount\n" +
" FROM acc_heads where acc_type = 1 GROUP BY acc_head_id)t where t.amount > 0  ";
//        qry = "SELECT * from ( SELECT acc_code_id,(SUM(dr_amt)-SUM(cr_amt)) as amount "
//                + " FROM ledger where posted_date <'"+sdf.format(toDate)+"' GROUP BY acc_code_id)t where t.amount > 0  ;";
//        
        
        System.out.println("hello"+qry);
        List<PartnerModel> lstPartner = new ArrayList<>();
        List<Object> objects = (List<Object>) DirectSqlUtils
				.getValueListFromTable(qry);
        
		for (Object obj : objects) {
			Object[] values = (Object[]) obj;
                       int accountid =  (Integer)values[0];
                       BigDecimal amount = (BigDecimal) values[1];
                       PartnerModel partnerModel = findbyAccountHead(accountid);
                       if(partnerModel == null)
                           continue;
                       partnerModel.setRemarks(String.valueOf(amount));
                       if(routeId != 0 && routeId != partnerModel.getRoute().getBranchId() )
                       {
                           continue;
                       }
                       if(customerId != 0 && customerId != partnerModel.getId() )
                       {
                           continue;
                       }
                       lstPartner.add(partnerModel);
                }
               return lstPartner;
    }
    
    

}
