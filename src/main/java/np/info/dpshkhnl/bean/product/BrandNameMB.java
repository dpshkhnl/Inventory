/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.product;

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
import np.info.dpshkhnl.model.product.BrandModel;
import np.info.dpshkhnl.service.product.BrandNameEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

/**
 *
 * @author Dpshkhnl
 */

    @Named
@ViewScoped
public class BrandNameMB  implements Serializable{
    
    @EJB
    BrandNameEJB brandNameEJB;
    
    private BrandModel brandName;
    private List<BrandModel> lstBrands;
    private List<BrandModel> selectedValue; 
    private List<BrandModel> filteredValue;
    private Filter<BrandModel> filter = new Filter<>(new BrandModel());
    
     public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        getBrandName();
        if (has(brandName.getBrandNameId())) {
            brandName = brandNameEJB.find(brandName.getBrandNameId());
        } 
    }

    public BrandModel getBrandName() {
        if(brandName == null)
            brandName = new BrandModel();
        
       
        return brandName;
    }

    public void setBrandName(BrandModel brandName) {
        this.brandName = brandName;
    }

    public List<BrandModel> getLstBrands() {
        if(lstBrands == null)
            lstBrands = new ArrayList<>();
        lstBrands = brandNameEJB.findAll();
        return lstBrands;
    }

    public void setLstBrands(List<BrandModel> lstBrands) {
        this.lstBrands = lstBrands;
    }

    public List<BrandModel> getSelectedValue() {
         if(selectedValue == null)
            selectedValue = new ArrayList<>();
        return selectedValue;
    }

    public void setSelectedValue(List<BrandModel> selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List<BrandModel> getFilteredValue() {
         if(filteredValue == null)
            filteredValue = new ArrayList<>();
        return filteredValue;
    }

    public void setFilteredValue(List<BrandModel> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public Filter<BrandModel> getFilter() {
         
        return filter;
    }

    public void setFilter(Filter<BrandModel> filter) {
        this.filter = filter;
    }
    
      public void save()
    {
        
         String msg;
        if (brandName.getBrandNameId()== 0) {
            brandNameEJB.save(brandName);
            msg = "Brand" + brandName.getBrandName()+ " created successfully";
        } else {
            brandNameEJB.update(brandName);
            msg = "Brand " + brandName.getBrandName() + " updated successfully";
        }
        addDetailMessage(msg);
    }
    public boolean isNew() {
        return brandName == null || brandName.getBrandNameId()== 0;
    }
    public void clear() {
        brandName = null;
        getBrandName();
        
    }
    
     public void remove() throws IOException {
        if (has(brandName) && has(brandName.getBrandNameId())) {
          brandNameEJB.delete(brandName.getBrandNameId(),BrandModel.class);
            addDetailMessage("Brand " + brandName.getBrandName()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("unit-setting-list.xhtml");
        }
    }
     
      public void delete() {
        int numCars = 0;
        for (BrandModel route : selectedValue) {
            numCars++;
            brandNameEJB.delete(route.getBrandNameId(),BrandModel.class);
            
        }
        selectedValue.clear();
        addDetailMessage(numCars + " Brand deleted successfully!");
    }
      
        public void findBrandById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Brand ID to load");
        }
        selectedValue.add(brandNameEJB.find(id));
    }
    
}
