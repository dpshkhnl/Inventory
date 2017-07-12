/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.account;

import com.github.adminfaces.template.exception.BusinessException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import np.info.dpshkhnl.infra.model.Filter;
import np.info.dpshkhnl.model.account.AccHead;
import np.info.dpshkhnl.model.account.Ledger;
import np.info.dpshkhnl.service.account.AccountEJB;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;
import np.info.dpshkhnl.service.account.AccountHeadEJB;
import static com.github.adminfaces.template.util.Assert.has;
import java.util.Date;
import np.info.dpshkhnl.model.account.Accounts;
import np.info.dpshkhnl.model.account.FiscalYrModel;
import np.info.dpshkhnl.service.account.LedgerEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;

/**
 *
 * @author Dpshkhnl
 */
@Named
@ViewScoped
public class AccountHeadMB implements Serializable{
    
    @EJB
    AccountEJB accountEJB;
    
    @EJB
    AccountHeadEJB accountHeadEJB;
    
    @EJB
    LedgerEJB ledgerEJB;
    
    private AccHead accHead;
    private List<AccHead> lstAccHeads;
    private List<AccHead> selectedAccHeads; 
     private List<AccHead> filteredValue;
     private LazyDataModel<AccHead> accHeads;
     private List<Accounts>lstAllAccHead;
     private Ledger ledger;

    private Filter<AccHead> filter = new Filter<>(new AccHead());
    
   
    
    public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        getAccHead();
        if (has(accHead.getAccHeadId())) {
            accHead = accountHeadEJB.find(accHead.getAccHeadId());
        } 
    }

    public AccHead getAccHead() {
        if(accHead == null)
            accHead = new AccHead();
        if(accHead.getLedger() == null)
            accHead.setLedger(new Ledger());
        if(accHead.getAccType() == null)
           accHead.setAccType(new Accounts());
        return accHead;
    }

    public void setAccHead(AccHead accHead) {
        this.accHead = accHead;
    }

    public List<AccHead> getLstAccHeads() {
        if(lstAccHeads == null)
            lstAccHeads = new ArrayList<>();
        lstAccHeads = accountHeadEJB.findAll();
        return lstAccHeads;
    }

    public void setLstAccHeads(List<AccHead> lstAccHeads) {
        this.lstAccHeads = lstAccHeads;
    }
    public void saveAccountHead()
    {
        
         String msg;
        if (accHead.getAccHeadId() == 0) {
      
             ledger = null;
            getLedger();
            Accounts acc = accountEJB.find(accHead.getAccType().getAccHeadId());
            if(acc.getDrcr().equals("dr"))
            {
                ledger.setDrAmt(accHead.getMinBal());
               
            }
            else
            {
                ledger.setCrAmt(accHead.getMinBal());
            }
              ledger.setAccountHead(accHead);
              ledger.setToAccountHead(null);
              ledger.setCreatedDate(new Date());
              ledger.setIsOpening(1);
              ledger.setPostedDate(new Date());
              ledger.setFiscalYear(null);
              ledger.setRemarks("Opening Balance");
              accHead.setLedger(ledger);
              accHead.setDrcr(acc.getDrcr());
              accountHeadEJB.save(accHead);
            ledger = null;
            msg = "Account Head " + accHead.getAccName()+ " created successfully";
        } else {
            
             Accounts acc = accountEJB.find(accHead.getAccType().getAccHeadId());
            if(acc.getDrcr().equals("dr"))
            {
                accHead.getLedger().setDrAmt(accHead.getMinBal());
               
            }
            else
            {
                accHead.getLedger().setCrAmt(accHead.getMinBal());
            }
            
            accountHeadEJB.update(accHead);
            msg = "Account Head " + accHead.getAccName() + " updated successfully";
        }
        addDetailMessage(msg);
    }
    public boolean isNew() {
        return accHead == null || accHead.getAccHeadId() == 0;
    }
    public void clear() {
        accHead = null;
        getAccHead();
        
    }
    
     public void remove() throws IOException {
        if (has(accHead) && has(accHead.getAccHeadId())) {
          accountHeadEJB.delete(accHead,AccHead.class);
            addDetailMessage("Account Head " + accHead.getAccName()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("account-head-list.xhtml");
        }
    }
     
      public void delete() {
        int numCars = 0;
        for (AccHead accountHead : selectedAccHeads) {
            numCars++;
            accountHeadEJB.delete(accountHead,AccHead.class);
            
        }
        selectedAccHeads.clear();
        addDetailMessage(numCars + " Account Heads deleted successfully!");
    }

    public List<AccHead> getSelectedAccHeads() {
        if(selectedAccHeads == null)
            selectedAccHeads = new ArrayList<>();
        return selectedAccHeads;
    }

    public void setSelectedAccHeads(List<AccHead> selectedAccHeads) {
        this.selectedAccHeads = selectedAccHeads;
    }

    public List<AccHead> getFilteredValue() {
         if(filteredValue == null)
            filteredValue = new ArrayList<>();
        return filteredValue;
    }

    public void setFilteredValue(List<AccHead> filteredValue) {
        this.filteredValue = filteredValue;
    }
     public void findAccountHeadById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
        //selectedAccHeads.add(accountHeadEJB.find(id));
    }

    public LazyDataModel<AccHead> getAccHeads() {
        return accHeads;
    }

    public void setAccHeads(LazyDataModel<AccHead> accHeads) {
        this.accHeads = accHeads;
    }

    public Filter<AccHead> getFilter() {
        return filter;
    }

    public void setFilter(Filter<AccHead> filter) {
        this.filter = filter;
    }

    public List<Accounts> getLstAllAccHead() {
        if(lstAllAccHead == null)
            lstAllAccHead = new ArrayList<>();
       lstAllAccHead= accountEJB.findAll();
        return lstAllAccHead;
    }

    public void setLstAllAccHead(List<Accounts> lstAllAccHead) {
        this.lstAllAccHead = lstAllAccHead;
  }

    public Ledger getLedger() {
        if(ledger == null)
            ledger = new Ledger();
        if(ledger.getAccountHead() == null)
            ledger.setAccountHead(new AccHead());
        if(ledger.getFiscalYear() == null)
            ledger.setFiscalYear(new FiscalYrModel());
        if(ledger.getToAccountHead() == null)
            ledger.setToAccountHead(new AccHead());
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

}
