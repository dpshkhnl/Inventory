/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.account;

import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import np.info.dpshkhnl.infra.model.Filter;
import np.info.dpshkhnl.model.Car;
import np.info.dpshkhnl.model.account.AccHead;
import np.info.dpshkhnl.model.account.Ledger;
import np.info.dpshkhnl.service.account.AccountHeadEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Dpshkhnl
 */
@Named
@ViewScoped
public class AccountHeadMB implements Serializable{
    
    @EJB
    AccountHeadEJB accountHeadEJB;
    
    private AccHead accHead;
    private List<AccHead> lstAccHeads;
    private List<AccHead> selectedAccHeads; 
     private List<AccHead> filteredValue;
     private LazyDataModel<AccHead> accHeads;

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
            accountHeadEJB.save(accHead);
            msg = "Account Head " + accHead.getAccName()+ " created successfully";
        } else {
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
        selectedAccHeads.add(accountHeadEJB.find(id));
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

}
