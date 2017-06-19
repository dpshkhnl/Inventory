/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.product;


/**
 *
 * @author Dpshkhnl
 */

import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import np.info.dpshkhnl.infra.model.Filter;
import np.info.dpshkhnl.model.product.ItemCategoryModel;
import np.info.dpshkhnl.service.product.ItemCategoryEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

/**
 *
 * @author Dpshkhnl
 */
    @Named
@ViewScoped
public class ItemCategoryMB implements Serializable{
    
    @EJB
    ItemCategoryEJB itemCategoryEJB;
    
    private ItemCategoryModel itemCategoryModel;
    private List<ItemCategoryModel> lstCategory;
    private List<ItemCategoryModel> selectedValue; 
    private List<ItemCategoryModel> filteredValue;
    private Filter<ItemCategoryModel> filter = new Filter<>(new ItemCategoryModel());
    
     public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        getItemCategoryModel();
        if (has(itemCategoryModel.getCatId())) {
            itemCategoryModel = itemCategoryEJB.find(itemCategoryModel.getCatId());
        } 
    }

    public ItemCategoryModel getItemCategoryModel() {
        if(itemCategoryModel == null)
            itemCategoryModel = new ItemCategoryModel();
        
       
        return itemCategoryModel;
    }

    public void setBrandName(ItemCategoryModel itemCategoryModel) {
        this.itemCategoryModel = itemCategoryModel;
    }

    public List<ItemCategoryModel> getLstCategory() {
        if(lstCategory == null)
            lstCategory = new ArrayList<>();
        lstCategory = itemCategoryEJB.findAll();
        return lstCategory;
    }

    public void setLstBrands(List<ItemCategoryModel> lstCategory) {
        this.lstCategory = lstCategory;
    }

    public List<ItemCategoryModel> getSelectedValue() {
         if(selectedValue == null)
            selectedValue = new ArrayList<>();
        return selectedValue;
    }

    public void setSelectedValue(List<ItemCategoryModel> selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List<ItemCategoryModel> getFilteredValue() {
         if(filteredValue == null)
            filteredValue = new ArrayList<>();
        return filteredValue;
    }

    public void setFilteredValue(List<ItemCategoryModel> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public Filter<ItemCategoryModel> getFilter() {
         
        return filter;
    }

    public void setFilter(Filter<ItemCategoryModel> filter) {
        this.filter = filter;
    }
    
      public void save()
    {
        
         String msg;
        if (itemCategoryModel.getCatId()== 0) {
            itemCategoryEJB.save(itemCategoryModel);
            msg = "Item" + itemCategoryModel.getCatName()+ " created successfully";
        } else {
            itemCategoryEJB.update(itemCategoryModel);
            msg = "Item " + itemCategoryModel.getCatName() + " updated successfully";
        }
        addDetailMessage(msg);
    }
    public boolean isNew() {
        return itemCategoryModel == null || itemCategoryModel.getCatId()== 0;
    }
    public void clear() {
        itemCategoryModel = null;
        getItemCategoryModel();
        
    }
    
     public void remove() throws IOException {
        if (has(itemCategoryModel) && has(itemCategoryModel.getCatId())) {
          itemCategoryEJB.delete(itemCategoryModel.getCatId(),ItemCategoryModel.class);
            addDetailMessage("Item " + itemCategoryModel.getCatName()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("item-category-list.xhtml");
        }
    }
     
      public void delete() {
        int numCars = 0;
        for (ItemCategoryModel route : selectedValue) {
            numCars++;
            itemCategoryEJB.delete(route,ItemCategoryModel.class);
            
        }
        selectedValue.clear();
        addDetailMessage(numCars + " Brand deleted successfully!");
    }
      
        public void findBrandById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Brand ID to load");
        }
        selectedValue.add(itemCategoryEJB.find(id));
    }
    
}

