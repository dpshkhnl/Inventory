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
import np.info.dpshkhnl.model.account.CodeValue;
import np.info.dpshkhnl.model.admin.RouteManagementModel;
import np.info.dpshkhnl.model.admin.UnitModel;
import np.info.dpshkhnl.service.admin.UnitSettingEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

/**
 *
 * @author Dpshkhnl
 */

@Named
@ViewScoped
public class UnitSettingMB  implements Serializable{
    
    @EJB
    UnitSettingEJB unitEJB;
    
    private UnitModel unitModel;
    private List<UnitModel> lstUnits;
    private List<UnitModel> selectedValue; 
    private List<UnitModel> filteredValue;
    private Filter<UnitModel> filter = new Filter<>(new UnitModel());
    
     public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        getUnitModel();
        if (has(unitModel.getUnitId())) {
            unitModel = unitEJB.find(unitModel.getUnitId());
        } 
    }

    public UnitModel getUnitModel() {
        if(unitModel == null)
            unitModel = new UnitModel();
       
       
        return unitModel;
    }

    public void setUnitModel(UnitModel unitModel) {
        this.unitModel = unitModel;
    }

    public List<UnitModel> getLstUnits() {
        if(lstUnits == null)
            lstUnits = new ArrayList<>();
        lstUnits = unitEJB.findAll();
        return lstUnits;
    }

    public void setLstUnits(List<UnitModel> lstUnits) {
        this.lstUnits = lstUnits;
    }

    public List<UnitModel> getSelectedValue() {
         if(selectedValue == null)
            selectedValue = new ArrayList<>();
        return selectedValue;
    }

    public void setSelectedValue(List<UnitModel> selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List<UnitModel> getFilteredValue() {
         if(filteredValue == null)
            filteredValue = new ArrayList<>();
        return filteredValue;
    }

    public void setFilteredValue(List<UnitModel> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public Filter<UnitModel> getFilter() {
         
        return filter;
    }

    public void setFilter(Filter<UnitModel> filter) {
        this.filter = filter;
    }
    
      public void saveRoute()
    {
        
         String msg;
        if (unitModel.getUnitId()== 0) {
            unitEJB.save(unitModel);
            msg = "Unit" + unitModel.getUnitName()+ " created successfully";
        } else {
            unitEJB.update(unitModel);
            msg = "Unit " + unitModel.getUnitName() + " updated successfully";
        }
        addDetailMessage(msg);
    }
    public boolean isNew() {
        return unitModel == null || unitModel.getUnitId()== 0;
    }
    public void clear() {
        unitModel = null;
        getUnitModel();
        
    }
    
     public void remove() throws IOException {
        if (has(unitModel) && has(unitModel.getUnitId())) {
          unitEJB.delete(unitModel,UnitModel.class);
            addDetailMessage("Unit " + unitModel.getUnitName()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("unit-setting-list.xhtml");
        }
    }
     
      public void delete() {
        int numCars = 0;
        for (UnitModel route : selectedValue) {
            numCars++;
            unitEJB.delete(route,UnitModel.class);
            
        }
        selectedValue.clear();
        addDetailMessage(numCars + " Unit deleted successfully!");
    }
      
        public void findUnitById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Unit ID to load");
        }
        selectedValue.add(unitEJB.find(id));
    }
}
