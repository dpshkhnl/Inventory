/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.purchase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import np.info.dpshkhnl.model.account.AccHead;
import np.info.dpshkhnl.model.account.CodeValue;
import np.info.dpshkhnl.model.account.Ledger;
import np.info.dpshkhnl.model.admin.PartnerModel;
import np.info.dpshkhnl.model.admin.UnitModel;
import np.info.dpshkhnl.model.product.ItemCategoryModel;
import np.info.dpshkhnl.model.product.ItemProductModel;
import np.info.dpshkhnl.model.product.ItemTypeModel;
import np.info.dpshkhnl.model.purchase.InventoryInvoiceModel;
import np.info.dpshkhnl.model.purchase.InventoryMasterModel;
import np.info.dpshkhnl.model.purchase.PurchaseDetailModel;
import np.info.dpshkhnl.service.account.AccountHeadEJB;
import np.info.dpshkhnl.service.account.CodeValueEJB;
import np.info.dpshkhnl.service.account.LedgerEJB;
import np.info.dpshkhnl.service.admin.PartnerEJB;
import np.info.dpshkhnl.service.admin.UnitSettingEJB;
import np.info.dpshkhnl.service.admin.VatSettingEJB;
import np.info.dpshkhnl.service.product.ItemCategoryEJB;
import np.info.dpshkhnl.service.product.ItemProductEJB;
import np.info.dpshkhnl.service.product.ItemTypeEJB;
import np.info.dpshkhnl.service.purchase.InvoiceEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author Dpshkhnl
 */

@Named
@ViewScoped
public class PurchaseMB implements Serializable {
    
    @EJB
    PartnerEJB partnerEJB;
    
    @EJB
    ItemCategoryEJB itemCatEJB;
    
    @EJB
    ItemTypeEJB itemTypeEJB;
    
    @EJB
    ItemProductEJB productEJB;
    
    @EJB
    UnitSettingEJB unitEJB;
    
    @EJB
    InvoiceEJB invoiceEJB;
    
    @EJB
    CodeValueEJB codeValueEJB;
    
    @EJB
    VatSettingEJB vatSettingEJB;
    
    @EJB
    AccountHeadEJB accHeadEJB;
    
    @EJB
    LedgerEJB ledgerEJB;
    
    private InventoryInvoiceModel inventoryInvoiceModel;
     private List<InventoryInvoiceModel> lstInvoice;
     private List<InventoryInvoiceModel> selectedInvoice;
     private boolean openingStock;

    public List<InventoryInvoiceModel> getLstInvoice() {
         if(lstInvoice == null)
            lstInvoice = new ArrayList<>();
        return lstInvoice;
    }

    public void setLstInvoice(List<InventoryInvoiceModel> lstInvoice) {
        this.lstInvoice = lstInvoice;
    }
    private List<PartnerModel> lstSupModels;
    private List<ItemCategoryModel> lstCat;
    private List<ItemTypeModel> lstItemType;
    private List<ItemProductModel> lstProduct;
    private List<UnitModel> lstUnits;
    private int catId;
    private int typeId;
    private CodeValue status;
    private List<CodeValue> lstStatus;
    private List<PurchaseDetailModel> lstPurchaseDet;
    private List<PurchaseDetailModel> selectedPurchaseDet;
    private PurchaseDetailModel purchaseDetailModel;
    private InventoryMasterModel invMasterModel;
    private boolean useVat;
   private double total;
    private boolean useDiscount;
    private double gTotal;
    private double vatAmt;
    private double discountAmt;

    public InventoryInvoiceModel getInventoryInvoiceModel() {
        if(inventoryInvoiceModel == null)
            inventoryInvoiceModel = new InventoryInvoiceModel();
        if(inventoryInvoiceModel.getSupId() == null)
            inventoryInvoiceModel.setSupId(new PartnerModel());
        if(inventoryInvoiceModel.getInvMasterList() == null)
            inventoryInvoiceModel.setInvMasterList(new ArrayList<>());
        
        if(inventoryInvoiceModel.getPurPaymentList()== null)
            inventoryInvoiceModel.setPurPaymentList(new ArrayList<>());
        
       
        if(inventoryInvoiceModel.getStatus()== null)
            inventoryInvoiceModel.setStatus(new CodeValue());
        if(inventoryInvoiceModel.getLstPurchase() == null)
             inventoryInvoiceModel.setLstPurchase(new ArrayList<>());
        return inventoryInvoiceModel;
    }

    public void setInventoryInvoiceModel(InventoryInvoiceModel inventoryInvoiceModel) {
        this.inventoryInvoiceModel = inventoryInvoiceModel;
    }

    public List<PartnerModel> getLstSupModels() {
        if(lstSupModels == null)
            lstSupModels = new ArrayList<>();
       lstSupModels= partnerEJB.findAllbySupplier();
        return lstSupModels;
    }

    public void setLstSupModels(List<PartnerModel> lstSupModels) {
        this.lstSupModels = lstSupModels;
    }

    public List<ItemCategoryModel> getLstCat() {
        if(lstCat == null)
            lstCat = new ArrayList<>();
        lstCat = itemCatEJB.findAll();
        return lstCat;
    }

    public void setLstCat(List<ItemCategoryModel> lstCat) {
        this.lstCat = lstCat;
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

    public List<ItemProductModel> getLstProduct() {
         if(lstProduct == null)
            lstProduct = new ArrayList<>();
         lstProduct= productEJB.findAll();
        return lstProduct;
    }

    public void setLstProduct(List<ItemProductModel> lstProduct) {
        this.lstProduct = lstProduct;
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

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    

   

    public List<PurchaseDetailModel> getLstPurchaseDet() {
        if(lstPurchaseDet == null)
            lstPurchaseDet = new ArrayList<>();
        return lstPurchaseDet;
    }

    public void setLstPurchaseDet(List<PurchaseDetailModel> lstPurchaseDet) {
        this.lstPurchaseDet = lstPurchaseDet;
    }

    public InventoryMasterModel getInvMasterModel() {
        return invMasterModel;
    }

    public void setInvMasterModel(InventoryMasterModel invMasterModel) {
        this.invMasterModel = invMasterModel;
    }

    public PurchaseDetailModel getPurchaseDetailModel() {
        if(purchaseDetailModel == null)
            purchaseDetailModel = new PurchaseDetailModel();
        if(purchaseDetailModel.getInventoryInvoiceModel() == null)
            purchaseDetailModel.setInventoryInvoiceModel(new InventoryInvoiceModel());
        if(purchaseDetailModel.getItemProductModel() == null)
            purchaseDetailModel.setItemProductModel(new ItemProductModel());
        if(purchaseDetailModel.getUnitModel() == null)
            purchaseDetailModel.setUnitModel(new UnitModel());
       
        return purchaseDetailModel;
    }

    public void setPurchaseDetailModel(PurchaseDetailModel purchaseDetailModel) {
        this.purchaseDetailModel = purchaseDetailModel;
    }

    public void addProductItem()
    {
        getLstPurchaseDet();
        purchaseDetailModel.setItemProductModel(productEJB.find(purchaseDetailModel.getItemProductModel().getProductId()));
        lstPurchaseDet.add(purchaseDetailModel);
      
        getInventoryInvoiceModel();
//        if(inventoryInvoiceModel.getInvAmt()== null || inventoryInvoiceModel.getInvAmt().equals(""))
//        {
//            inventoryInvoiceModel.setInvAmt(0.00);
//        }
        total = total+purchaseDetailModel.getTotalCost();
        getGrandTotal();
        purchaseDetailModel = null;
    }

    public List<PurchaseDetailModel> getSelectedPurchaseDet() {
        
        if(selectedPurchaseDet == null)
            selectedPurchaseDet = new ArrayList<>();
        return selectedPurchaseDet;
    }

    public void setSelectedPurchaseDet(List<PurchaseDetailModel> selectedPurchaseDet) {
        this.selectedPurchaseDet = selectedPurchaseDet;
    }
    
    
    public void savePurchase()
    {
        inventoryInvoiceModel.setCreatedBy(1);
        inventoryInvoiceModel.setLstPurchase(lstPurchaseDet);
        inventoryInvoiceModel.setStatus(codeValueEJB.getCodeValueByTypeAndLabel("InvoiceStatus","Unpaid"));
       if(isOpeningStock())
       {
           inventoryInvoiceModel.setStatus(codeValueEJB.getCodeValueByTypeAndLabel("InvoiceStatus","Opening Stock"));
       }
        List<InventoryMasterModel> lstMaster = new ArrayList<>();
        for(PurchaseDetailModel purModel : lstPurchaseDet)
        {
            purModel.setInventoryInvoiceModel(inventoryInvoiceModel);
            InventoryMasterModel invMasterModel = new InventoryMasterModel();
            invMasterModel.setProductId(purModel.getItemProductModel());
            invMasterModel.setInQty(unitEJB.getUnitCountOfAllParent(purModel.getUnitModel())*purModel.getInQty());
            invMasterModel.setInvInvoiceStk(purModel.getInventoryInvoiceModel());
            invMasterModel.setTotalCost(purModel.getTotalCost());
            
            invMasterModel.setUnitSalePrice(purModel.getSellingPrice());
            
            lstMaster.add(invMasterModel);
        }
        if(openingStock){
        AccHead accountHead = null;
         List<AccHead>  lst = accHeadEJB.findbyAccount("Purchases");
         for(AccHead acc : lst)
         {
             if (acc.getAccName().equals("Purchase"))
             {
                 accountHead = acc;
             }else
             {
                 addDetailMessage("Please Create Account Head With Acc Name Purchase"); 
                 return;
             }
         }
         if(accountHead == null)
         {
             addDetailMessage("Please Create Account Head With Acc Name Purchase"); 
                 return;
         }
        inventoryInvoiceModel.setInvMasterList(lstMaster);
        Ledger ledger = new Ledger();
        ledger.setDrAmt(inventoryInvoiceModel.getInvAmt());
        ledger.setAccountHead(accountHead);
        ledger.setFiscalYear(null);
        ledger.setToAccountHead(partnerEJB.find(inventoryInvoiceModel.getSupId().getId()).getAccountHead());
        ledger.setPostedDate(inventoryInvoiceModel.getInvDtEn());
        ledger.setRemarks("Purchase From "+inventoryInvoiceModel.getSupId().getMemberName());
        ledger.setCreatedDate(new Date());
        
        try {
            ledgerEJB.postToLedger(ledger,accHeadEJB);
        } catch (Exception ex) {
            addDetailMessage("Error Occured ,Please Try again Later "); 
                 return;
        }
        }
        invoiceEJB.save(inventoryInvoiceModel);
        addDetailMessage("Purchase Completed");
    }
    
     public void purchaseReturn()
    {
        inventoryInvoiceModel.setCreatedBy(1);
        inventoryInvoiceModel.setLstPurchase(lstPurchaseDet);
        inventoryInvoiceModel.setStatus(codeValueEJB.getCodeValueByTypeAndLabel("InvoiceStatus","Purchase Return"));
       
        List<InventoryMasterModel> lstMaster = new ArrayList<>();
        for(PurchaseDetailModel purModel : lstPurchaseDet)
        {
            purModel.setInventoryInvoiceModel(inventoryInvoiceModel);
            InventoryMasterModel invMasterModel = new InventoryMasterModel();
            invMasterModel.setProductId(purModel.getItemProductModel());
            invMasterModel.setOutQty(unitEJB.getUnitCountOfAllParent(purModel.getUnitModel())*purModel.getRetQty());
            invMasterModel.setInvInvoiceStk(purModel.getInventoryInvoiceModel());
            invMasterModel.setTotalCost(purModel.getTotalCost());
            
            //invMasterModel.setUnitSalePrice(purModel.getSellingPrice());
            
            lstMaster.add(invMasterModel);
        }
        inventoryInvoiceModel.setInvMasterList(lstMaster);
        
        
        AccHead accountHead = null;
         List<AccHead>  lst = accHeadEJB.findbyAccount("Puchases");
         for(AccHead acc : lst)
         {
             if (acc.getAccName().equals("Purchase Return"))
             {
                 accountHead = acc;
             }else
             {
                 addDetailMessage("Please Create Account Head With Acc Name Purchase Return"); 
                 return;
             }
         }
         
        inventoryInvoiceModel.setInvMasterList(lstMaster);
        Ledger ledger = new Ledger();
        ledger.setCrAmt(inventoryInvoiceModel.getInvAmt());
        ledger.setToAccountHead(accountHead);
       ledger.setAccountHead(partnerEJB.find(inventoryInvoiceModel.getSupId().getId()).getAccountHead());
      
        ledger.setFiscalYear(null);
        ledger.setPostedDate(inventoryInvoiceModel.getInvDtEn());
        ledger.setRemarks("Purchase Return From "+inventoryInvoiceModel.getSupId().getMemberName());
        ledger.setCreatedDate(new Date());
        
        try {
            ledgerEJB.postToLedger(ledger,accHeadEJB);
        } catch (Exception ex) {
            addDetailMessage("Error Occured ,Please Try again Later "); 
                 return;
        }
        
        
        invoiceEJB.save(inventoryInvoiceModel);
        addDetailMessage("Purchase Return Completed");
    }

     public void getPurchaseTotal()
     {
         purchaseDetailModel.setTotalCost(purchaseDetailModel.getInQty()*purchaseDetailModel.getPerCost());
         
     }
     public void calculateVat()
     {
        if(useVat)
        {
          //  vat = vatSettingEJB.fi
            inventoryInvoiceModel.setInvVatAmt(total*13/100);
            
        }
        else
        {
           inventoryInvoiceModel.setInvVatAmt(0.00); 
        }
           getGrandTotal();      
     }
     
    public void getGrandTotal()
    {
       if(inventoryInvoiceModel.getInvVatAmt() == null)
       {
           inventoryInvoiceModel.setInvVatAmt(0.00);
       }
       if(inventoryInvoiceModel.getInvDiscountAmt()== null)
       {
           inventoryInvoiceModel.setInvDiscountAmt(0.00);
       }
       
        inventoryInvoiceModel.setInvAmt(total+inventoryInvoiceModel.getInvVatAmt() -inventoryInvoiceModel.getInvDiscountAmt());
    }

     
     public void getPurchaseReturnTotal()
     {
         purchaseDetailModel.setTotalCost(purchaseDetailModel.getRetQty()*purchaseDetailModel.getPerCost());
         
     }

    
    public void loadInvoicesByCustomer()
    {
        getLstInvoice();
         lstInvoice =invoiceEJB.findbySupplierId(inventoryInvoiceModel.getSupId().getId(),status.getCvId());
    }

    public List<InventoryInvoiceModel> getSelectedInvoice() {
        if(selectedInvoice == null)
            selectedInvoice = new ArrayList<>();
        return selectedInvoice;
    }

    public void setSelectedInvoice(List<InventoryInvoiceModel> selectedInvoice) {
        this.selectedInvoice = selectedInvoice;
    }
    public void loadPurchaseDetails()
    {
       total = 0.0;
       gTotal = 0.0;
       vatAmt = 0.0;
       discountAmt = 0.0;
            lstPurchaseDet = null;
            getLstPurchaseDet();
            for (InventoryInvoiceModel inv : selectedInvoice)
            {
                if(inv.getInvAmt() == null)
                    inv.setInvAmt(0.00);
                if(inv.getInvVatAmt() == null)
                    inv.setInvVatAmt(0.00);
                if(inv.getInvDiscountAmt() == null)
                    inv.setInvDiscountAmt(0.00);
                 List<PurchaseDetailModel> lstTemp = inv.getLstPurchase();
                 lstPurchaseDet.addAll(lstTemp);
                  gTotal = gTotal + inv.getInvAmt();
                  vatAmt = vatAmt + inv.getInvVatAmt();
                  discountAmt = discountAmt + inv.getInvDiscountAmt();
                       
            }
          total = gTotal-vatAmt+discountAmt; 
    }

    public CodeValue getStatus() {
        if(status == null)
            status = new CodeValue();
        return status;
    }

    public void setStatus(CodeValue status) {
        this.status = status;
    }

    public List<CodeValue> getLstStatus() {
        if(lstStatus == null)
            lstStatus = new ArrayList<>();
        lstStatus = codeValueEJB.findByCVType("InvoiceStatus");
        return lstStatus;
    }

    public void setLstStatus(List<CodeValue> lstStatus) {
        this.lstStatus = lstStatus;
    }

    public boolean isUseVat() {
        return useVat;
    }

    public void setUseVat(boolean useVat) {
        this.useVat = useVat;
    }

    public boolean isUseDiscount() {
        return useDiscount;
    }

    public void setUseDiscount(boolean useDiscount) {
        this.useDiscount = useDiscount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getgTotal() {
        return gTotal;
    }

    public void setgTotal(double gTotal) {
        this.gTotal = gTotal;
    }

    public double getVatAmt() {
        return vatAmt;
    }

    public void setVatAmt(double vatAmt) {
        this.vatAmt = vatAmt;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public boolean isOpeningStock() {
        return openingStock;
    }

    public void setOpeningStock(boolean openingStock) {
        this.openingStock = openingStock;
    }
    
}
