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
import np.info.dpshkhnl.model.admin.UnitModel;
import np.info.dpshkhnl.model.product.BrandModel;
import np.info.dpshkhnl.model.product.ItemCategoryModel;
import np.info.dpshkhnl.model.product.ItemProductModel;
import np.info.dpshkhnl.model.product.ItemTypeModel;
import np.info.dpshkhnl.service.admin.UnitSettingEJB;
import np.info.dpshkhnl.service.product.BrandNameEJB;
import np.info.dpshkhnl.service.product.ItemCategoryEJB;
import np.info.dpshkhnl.service.product.ItemProductEJB;
import np.info.dpshkhnl.service.product.ItemTypeEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

/**
 *
 * @author Dpshkhnl
 */
  @Named
@ViewScoped
public class ItemProductMB implements Serializable{
    
    @EJB
    ItemProductEJB itemProductEJB;
    
    @EJB
    ItemCategoryEJB itemCatEJB;
    
    @EJB
    ItemTypeEJB itemTypeEJB;
    
    @EJB
    BrandNameEJB brandNameEJB;
    
    @EJB
    UnitSettingEJB unitEJB;
    
    private ItemProductModel itemProductModel;
    private List<ItemProductModel> lstItemProduct;
    private List<ItemProductModel> selectedValue; 
    private List<ItemProductModel> filteredValue;
    private Filter<ItemProductModel> filter = new Filter<>(new ItemProductModel());
        private List<ItemCategoryModel> lstItemCat;
         private List<ItemTypeModel> lstItemType;
          private List<BrandModel> lstBrands;
          private List<UnitModel> lstUnits;
    
     public void init() {
        if(Faces.isAjaxRequest()){
           return;
        }
        getItemProductModel();
        if (has(itemProductModel.getProductId())) {
            itemProductModel = itemProductEJB.find(itemProductModel.getProductId());
        } 
    }

    public ItemProductModel getItemProductModel() {
        if(itemProductModel == null)
            itemProductModel = new ItemProductModel();
        if(itemProductModel.getBrandId()== null)
            itemProductModel.setBrandId(new BrandModel());
        if(itemProductModel.getItemId()== null)
            itemProductModel.setItemId(new ItemTypeModel());
        if(itemProductModel.getUnitId()== null)
            itemProductModel.setUnitId(new UnitModel());
        
       
        return itemProductModel;
    }

    public void setItemProductModel(ItemProductModel itemProductModel) {
        this.itemProductModel = itemProductModel;
    }

    public List<ItemProductModel> getLstItemProduct() {
        if(lstItemProduct == null)
            lstItemProduct = new ArrayList<>();
        lstItemProduct = itemProductEJB.findAll();
        return lstItemProduct;
    }

    public void setLstItemProduct(List<ItemProductModel> lstItemProduct) {
        this.lstItemProduct = lstItemProduct;
    }

    public List<ItemProductModel> getSelectedValue() {
         if(selectedValue == null)
            selectedValue = new ArrayList<>();
        return selectedValue;
    }

    public void setSelectedValue(List<ItemProductModel> selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List<ItemProductModel> getFilteredValue() {
         if(filteredValue == null)
            filteredValue = new ArrayList<>();
        return filteredValue;
    }

    public void setFilteredValue(List<ItemProductModel> filteredValue) {
        this.filteredValue = filteredValue;
    }

    public Filter<ItemProductModel> getFilter() {
         
        return filter;
    }

    public void setFilter(Filter<ItemProductModel> filter) {
        this.filter = filter;
    }
    
      public void save()
    {
        
         String msg;
        if (itemProductModel.getProductId()== 0) {
            itemProductEJB.save(itemProductModel);
            msg = "Item" + itemProductModel.getProductCode()+ " created successfully";
        } else {
            itemProductEJB.update(itemProductModel);
            msg = "Item " + itemProductModel.getProductCode()+ " updated successfully";
        }
        addDetailMessage(msg);
    }
    public boolean isNew() {
        return itemProductModel == null || itemProductModel.getProductId()== 0;
    }
    public void clear() {
        itemProductModel = null;
        getItemProductModel();
        
    }
    
     public void remove() throws IOException {
        if (has(itemProductModel) && has(itemProductModel.getProductId())) {
          itemProductEJB.delete(itemProductModel.getProductId(),ItemProductModel.class);
            addDetailMessage("Item " + itemProductModel.getProductCode()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("item-product-list.xhtml");
        }
    }
     
      public void delete() {
        int numCars = 0;
        for (ItemProductModel route : selectedValue) {
            numCars++;
            itemProductEJB.delete(route.getProductId(),ItemProductModel.class);
            
        }
        selectedValue.clear();
        addDetailMessage(numCars + " Item Type deleted successfully!");
    }
      
        public void findItemTypeById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Item Type ID to load");
        }
        selectedValue.add(itemProductEJB.find(id));
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

    public List<BrandModel> getLstBrands() {
        if(lstBrands == null)
            lstBrands = new ArrayList<>();
        lstBrands = brandNameEJB.findAll();
        return lstBrands;
    }

    public void setLstBrands(List<BrandModel> lstBrands) {
        this.lstBrands = lstBrands;
    }

    public List<ItemTypeModel> getLstItemType() {
       if(lstItemType == null)
           lstItemType  = new ArrayList<>();
       lstItemType = itemTypeEJB.findAll();
        return lstItemType;
    }

    public void setLstItemType(List<ItemTypeModel> lstItemType) {
        this.lstItemType = lstItemType;
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
    
}

