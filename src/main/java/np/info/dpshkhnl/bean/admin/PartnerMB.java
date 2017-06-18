/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.admin;

import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;
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
import np.info.dpshkhnl.service.account.AccountHeadEJB;
import np.info.dpshkhnl.service.admin.PartnerEJB;
import np.info.dpshkhnl.service.admin.RouteEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

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
    PartnerEJB partnerEJB;
    
    @EJB
    RouteEJB routeEJB;
    
    private PartnerModel partnerModel;
    private List<PartnerModel> lsPartnerModels;
     private List<PartnerModel> selectedValue;
     private List<RouteManagementModel> lstRoutes;
     private List<AccHead> lstAccHeads;

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
        if(partnerModel.getAccountPayable()== null)
            partnerModel.setAccountPayable(new AccHead());
        if(partnerModel.getAccountReceivable()== null)
            partnerModel.setAccountReceivable(new AccHead());
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
     
     public void savePartner()
    {
        
         String msg;
        if (partnerModel.getId()== 0) {
            partnerModel.setDebit(Double.valueOf(0));
            partnerModel.setCredit(Double.valueOf(0));
            partnerEJB.save(partnerModel);
            msg = "Partner  " + partnerModel.getMemberName()+ " created successfully";
        } else {
            partnerEJB.update(partnerModel);
            msg = "Partner  " + partnerModel.getMemberName()+ "  updated successfully";
        }
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
          partnerEJB.delete(partnerModel,PartnerModel.class);
            addDetailMessage("Partner " + partnerModel.getMemberName()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("partner-list.xhtml");
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
}
