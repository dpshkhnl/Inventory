/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.admin;

import com.github.adminfaces.template.exception.BusinessException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import np.info.dpshkhnl.infra.model.Filter;
import np.info.dpshkhnl.model.account.AccHead;
import np.info.dpshkhnl.model.admin.PartnerModel;
import np.info.dpshkhnl.model.admin.RouteManagementModel;
import np.info.dpshkhnl.service.account.AccountEJB;
import np.info.dpshkhnl.service.admin.PartnerEJB;
import np.info.dpshkhnl.service.admin.RouteEJB;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import static com.github.adminfaces.template.util.Assert.has;
import java.util.Date;
import np.info.dpshkhnl.model.account.Accounts;
import np.info.dpshkhnl.service.account.AccountHeadEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;

/**
 *
 * @author Dpshkhnl
 */
@Named
@ViewScoped
public class PartnerMB implements Serializable{
    
    @EJB
    private AccountHeadEJB accountHeadEJB;
    
    @EJB
    AccountEJB accountsEJB;
    
    @EJB
    PartnerEJB partnerEJB;
    
    @EJB
    RouteEJB routeEJB;
    
    private PartnerModel partnerModel;
    private List<PartnerModel> lsPartnerModels;
     private List<PartnerModel> selectedValue;
     private List<RouteManagementModel> lstRoutes;
     private List<AccHead> lstAccHeads;
     private double openingBal;

    public List<PartnerModel> getSelectedValue() {
        if (selectedValue == null)
            selectedValue = new ArrayList<>();
        return selectedValue;
    }

    public void setSelectedValue(List<PartnerModel> selectedValue) {
        this.selectedValue = selectedValue;
    }
     private List<PartnerModel> filteredValue;
     private Filter<PartnerModel> filter = new Filter<>(new PartnerModel());
     
     public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        getPartnerModel();
        if (has(partnerModel.getId())) {
            partnerModel = partnerEJB.find(partnerModel.getId());
        } 
    }

   

    public PartnerModel getPartnerModel() {
        if(partnerModel == null)
            partnerModel = new PartnerModel();
        if(partnerModel.getAccountHead()== null)
            partnerModel.setAccountHead(new AccHead());
       
        if(partnerModel.getRoute() == null)
            partnerModel.setRoute(new RouteManagementModel());
        return partnerModel;
    }

    public void setPartnerModel(PartnerModel partnerModel) {
        this.partnerModel = partnerModel;
    }

    public List<PartnerModel> getLsPartnerModels() {
        
        if (lsPartnerModels == null)
            lsPartnerModels = new ArrayList<>();
        
        lsPartnerModels = partnerEJB.findAll();
        return lsPartnerModels;
    }

    public void setLsPartnerModels(List<PartnerModel> lsPartnerModels) {
        this.lsPartnerModels = lsPartnerModels;
    }

 

    public List<PartnerModel> getFilteredValue() {
         if (filteredValue == null)
            filteredValue = new ArrayList<>();
        return filteredValue;
    }

    public void setFilteredValue(List<PartnerModel> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public Filter<PartnerModel> getFilter() {
        return filter;
    }

    public void setFilter(Filter<PartnerModel> filter) {
        this.filter = filter;
    }
    
     public void findById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
        selectedValue.add(partnerEJB.find(id));
    }
     
     public void savePartner() throws IOException
    {
        
         String msg;
        if (partnerModel.getId()== 0) {
            
            if(partnerModel.getCustomer())
            {
                Accounts acc = accountsEJB.findbyAccountName("Receivable");
                 partnerModel.getAccountHead().setAccCode(acc.getAccHeadId() +"."+partnerEJB.findMaxPartnerId()+1);
                 partnerModel.getAccountHead().setAccName(partnerModel.getMemberName());
                 partnerModel.getAccountHead().setAccType(acc);
                 partnerModel.getAccountHead().setDrBalance(openingBal);
                 partnerModel.getAccountHead().setAlias(partnerModel.getMemberName());
                 partnerModel.getAccountHead().setCreatedDate(new Date());
                 partnerModel.getAccountHead().setDrcr("dr");
            }else if(partnerModel.getSupplier())
            {
                Accounts acc = accountsEJB.findbyAccountName("Payable");
                 partnerModel.setRoute(null);
                 partnerModel.getAccountHead().setAccCode(acc.getAccHeadId() +"."+partnerEJB.findMaxPartnerId()+1);
                 partnerModel.getAccountHead().setAccName(partnerModel.getMemberName());
                 partnerModel.getAccountHead().setAccType(acc);
                 partnerModel.getAccountHead().setCrBalance(openingBal);
                 partnerModel.getAccountHead().setAlias(partnerModel.getMemberName());
                 partnerModel.getAccountHead().setCreatedDate(new Date());
                 partnerModel.getAccountHead().setDrcr("cr");  
            }
            
            partnerEJB.save(partnerModel);
            msg = "Partner  " + partnerModel.getMemberName()+ " created successfully";
        } else {
            partnerEJB.update(partnerModel);
            msg = "Partner  " + partnerModel.getMemberName()+ "  updated successfully";
        }
        Faces.getFlash().setKeepMessages(true);
            Faces.redirect("pages/admin/partner/partner-list.xhtml");
        addDetailMessage(msg);
    }
    public boolean isNew() {
        return partnerModel == null || partnerModel.getId()== 0;
    }
    public void clear() {
        partnerModel = null;
        getPartnerModel();
        
    }
    
     public void remove() throws IOException {
        if (has(partnerModel) && has(partnerModel.getId())) {
          partnerEJB.delete(partnerModel.getId(),PartnerModel.class);
            addDetailMessage("Partner " + partnerModel.getMemberName()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("/pages/admin/partner/partner-list.xhtml");
        }
    }
     
      public void delete() {
        int numCars = 0;
        for (PartnerModel partner : selectedValue) {
            numCars++;
            partnerEJB.delete(partner,PartnerModel.class);
            
        }
        selectedValue.clear();
        addDetailMessage(numCars + " Partner deleted successfully!");
    }
     public void findPartnerById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
        selectedValue.add(partnerEJB.find(id));
    }

    public List<RouteManagementModel> getLstRoutes() {
        if(lstRoutes == null)
            lstRoutes = new ArrayList<>();
        lstRoutes = routeEJB.findAll();
        return lstRoutes;
    }

    public void setLstRoutes(List<RouteManagementModel> lstRoutes) {
        this.lstRoutes = lstRoutes;
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

    public double getOpeningBal() {
        return openingBal;
    }

    public void setOpeningBal(double openingBal) {
        this.openingBal = openingBal;
    }
}
