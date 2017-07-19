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
import np.info.dpshkhnl.model.admin.RouteManagementModel;
import np.info.dpshkhnl.service.admin.RouteEJB;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import static com.github.adminfaces.template.util.Assert.has;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;

/**
 *
 * @author Dpshkhnl
 */
@Named
@ViewScoped
public class RouteMB implements Serializable{
    
    @EJB
    RouteEJB routeEJB;
    
    private RouteManagementModel routeManagementModel;
    private List<RouteManagementModel> lstRoutes;
    private List<RouteManagementModel> selectedRoutes; 
    private List<RouteManagementModel> filteredValue;
    private Filter<RouteManagementModel> filter = new Filter<>(new RouteManagementModel());
    
   
  public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        getRouteManagementModel();
        if (has(routeManagementModel.getBranchId())) {
            routeManagementModel = routeEJB.find(routeManagementModel.getBranchId());
        } 
    }

    public RouteManagementModel getRouteManagementModel() {
        if(routeManagementModel == null)
            routeManagementModel = new RouteManagementModel();
        return routeManagementModel;
    }

    public void setRouteManagementModel(RouteManagementModel routeManagementModel) {
        this.routeManagementModel = routeManagementModel;
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

    

    public List<RouteManagementModel> getFilteredValue() {
        if(filteredValue == null)
            filteredValue = new ArrayList<>();
        return filteredValue;
    }

    public void setFilteredValue(List<RouteManagementModel> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public Filter<RouteManagementModel> getFilter() {
        if(filter == null)
            filter = new Filter<>();
        return filter;
    }

    public void setFilter(Filter<RouteManagementModel> filter) {
        this.filter = filter;
    }
     public void saveRoute() throws IOException
    {
        
         String msg;
        if (routeManagementModel.getBranchId()== 0) {
            routeEJB.save(routeManagementModel);
            msg = "Route" + routeManagementModel.getBranchName()+ " created successfully";
        } else {
            routeEJB.update(routeManagementModel);
            msg = "Route " + routeManagementModel.getBranchName() + " updated successfully";
        }
        addDetailMessage(msg);
        Faces.getFlash().setKeepMessages(true);
            Faces.redirect("pages/admin/route/route-list.xhtml");
    }
    public boolean isNew() {
        return routeManagementModel == null || routeManagementModel.getBranchId()== 0;
    }
    public void clear() {
        routeManagementModel = null;
        getRouteManagementModel();
        
    }
    
     public void remove() throws IOException {
        if (has(routeManagementModel) && has(routeManagementModel.getBranchId())) {
          routeEJB.delete(routeManagementModel.getBranchId(),RouteManagementModel.class);
            addDetailMessage("Account Head " + routeManagementModel.getBranchName()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("pages/admin/route/route-list.xhtml");
        }
    }
     
      public void delete() {
        int numCars = 0;
        for (RouteManagementModel route : selectedRoutes) {
            numCars++;
            routeEJB.delete(route,RouteManagementModel.class);
            
        }
        selectedRoutes.clear();
        addDetailMessage(numCars + " Routes deleted successfully!");
    }

    public List<RouteManagementModel> getSelectedRoutes() {
        if(selectedRoutes == null)
            selectedRoutes = new ArrayList<>();
        return selectedRoutes;
    }

    public void setSelectedRoutes(List<RouteManagementModel> selectedRoutes) {
        this.selectedRoutes = selectedRoutes;
    }
     public void findRouteById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Car ID to load");
        }
        selectedRoutes.add(routeEJB.find(id));
    }
}
