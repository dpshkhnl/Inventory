/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.account;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import np.info.dpshkhnl.model.account.Ledger;
import np.info.dpshkhnl.service.core.GenericDAO;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class LedgerEJB extends GenericDAO<Ledger> {

    @EJB
    AccountHeadEJB accHeadEJB;
    
    public LedgerEJB() {
        super(Ledger.class);
    }
    
    public List<Ledger> findbyAccountHead(int accHeadId) {

        String qry = "SELECT i from Ledger  i where i.accountHead.accHeadId =" + accHeadId ;

        Query query = getEntityManager().createQuery(qry);

        return query.getResultList();
    }
    
    public List<Ledger> loadDataByDate(Date frmDate,Date toDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String qry = "SELECT i from Ledger  i where i.drAmt > 0 and i.postedDate between '" +sdf.format(frmDate)+"' and '"+sdf.format(toDate)+"'"  ;

        Query query = getEntityManager().createQuery(qry);

        return query.getResultList();
    }

    public void postToLedger(Ledger ledger,AccountHeadEJB accHeadEJB) throws Exception {
        {  
            save(ledger);
            Ledger ledger2 = new Ledger();
            ledger2.setAccountHead(ledger.getToAccountHead());
            ledger2.setToAccountHead(ledger.getAccountHead());
            ledger2.setCreatedDate(ledger.getCreatedDate());
            ledger2.setFiscalYear(null);
            ledger2.setIsOpening(ledger.getIsOpening());
            ledger2.setPostedDate(ledger.getPostedDate());
            ledger2.setRefNo(ledger.getRefNo());
            ledger2.setRemarks(ledger.getRemarks());
            ledger2.setCrAmt(ledger.getDrAmt());
            ledger2.setDrAmt(ledger.getCrAmt());
            save(ledger2);
            accHeadEJB.updateAccountHeadBalance(ledger.getAccountHead().getAccHeadId(), ledger.getDrAmt(), "dr");
            accHeadEJB.updateAccountHeadBalance(ledger.getToAccountHead().getAccHeadId(), ledger.getCrAmt(), "cr");
                
            } 
        
    }

}
