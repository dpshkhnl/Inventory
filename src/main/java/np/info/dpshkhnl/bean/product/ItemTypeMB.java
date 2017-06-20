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
import np.info.dpshkhnl.model.product.ItemCategoryModel;
import np.info.dpshkhnl.model.product.ItemTypeModel;
import np.info.dpshkhnl.service.product.ItemCategoryEJB;
import np.info.dpshkhnl.service.product.ItemTypeEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

/**
 *
 * @author Dpshkhnl
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Dpshkhnl
 */
    @Named
@ViewScoped
public class ItemTypeMB implements Serializable{
    
    @EJB
    ItemTypeEJB itemTypeEJB;
    
    @EJB
    ItemCategoryEJB itemCatEJB;
    
    private ItemTypeModel itemTypeModel;
    private List<ItemTypeModel> lstItemType;
    private List<ItemTypeModel> selectedValue; 
    private List<ItemTypeModel> filteredValue;
    private Filter<ItemTypeModel> filter = new Filter<>(new ItemTypeModel());
        private List<ItemCategoryModel> lstItemCat;
    
     public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        getItemTypeModel();
        if (has(itemTypeModel.getItemTypeId())) {
            itemTypeModel = itemTypeEJB.find(itemTypeModel.getItemTypeId());
        } 
    }

    public ItemTypeModel getItemTypeModel() {
        if(itemTypeModel == null)
            itemTypeModel = new ItemTypeModel();
        if(itemTypeModel.getCatId() == null)
            itemTypeModel.setCatId(new ItemCategoryModel());
        
       
        return itemTypeModel;
    }

    public void setBrandName(ItemTypeModel itemTypeModel) {
        this.itemTypeModel = itemTypeModel;
    }

    public List<ItemTypeModel> getLstItemType() {
        if(lstItemType == null)
            lstItemType = new ArrayList<>();
        lstItemType = itemTypeEJB.findAll();
        return lstItemType;
    }

    public void setLstItemType(List<ItemTypeModel> lstItemType) {
        this.lstItemType = lstItemType;
    }

    public List<ItemTypeModel> getSelectedValue() {
         if(selectedValue == null)
            selectedValue = new ArrayList<>();
        return selectedValue;
    }

    public void setSelectedValue(List<ItemTypeModel> selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List<ItemTypeModel> getFilteredValue() {
         if(filteredValue == null)
            filteredValue = new ArrayList<>();
        return filteredValue;
    }

    public void setFilteredValue(List<ItemTypeModel> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public Filter<ItemTypeModel> getFilter() {
         
        return filter;
    }

    public void setFilter(Filter<ItemTypeModel> filter) {
        this.filter = filter;
    }
    
      public void save()
    {
        
         String msg;
        if (itemTypeModel.getItemTypeId()== 0) {
            itemTypeEJB.save(itemTypeModel);
            msg = "Item" + itemTypeModel.getItemTypeName()+ " created successfully";
        } else {
            itemTypeEJB.update(itemTypeModel);
            msg = "Item " + itemTypeModel.getItemTypeName() + " updated successfully";
        }
        addDetailMessage(msg);
    }
    public boolean isNew() {
        return itemTypeModel == null || itemTypeModel.getItemTypeId()== 0;
    }
    public void clear() {
        itemTypeModel = null;
        getItemTypeModel();
        
    }
    
     public void remove() throws IOException {
        if (has(itemTypeModel) && has(itemTypeModel.getCatId())) {
          itemTypeEJB.delete(itemTypeModel.getCatId(),ItemTypeModel.class);
            addDetailMessage("Item " + itemTypeModel.getItemTypeName()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("item-type-list.xhtml");
        }
    }
     
      public void delete() {
        int numCars = 0;
        for (ItemTypeModel route : selectedValue) {
            numCars++;
            itemTypeEJB.delete(route,ItemTypeModel.class);
            
        }
        selectedValue.clear();
        addDetailMessage(numCars + " Item Type deleted successfully!");
    }
      
        public void findItemTypeById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Item Type ID to load");
        }
        selectedValue.add(itemTypeEJB.find(id));
    }

    public List<ItemCategoryModel> getLstItemCat() {
        if(lstItemCat == null)
            lstItemCat = new ArrayList<>();
        lstItemCat = itemCatEJB.findAll();
        return lstItemCat;
    }

    public void setLstItemCat(List<ItemCategoryModel> lstItemCat) {
        this.lstItemCat = lstItemCat;
    }
    
}


