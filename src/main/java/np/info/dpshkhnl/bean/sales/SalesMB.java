/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.sales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import np.info.dpshkhnl.model.sales.InventoryReceiptModel;
import np.info.dpshkhnl.model.sales.SalesDetailModel;
import np.info.dpshkhnl.service.account.AccountHeadEJB;
import np.info.dpshkhnl.service.account.CodeValueEJB;
import np.info.dpshkhnl.service.account.LedgerEJB;
import np.info.dpshkhnl.service.admin.PartnerEJB;
import np.info.dpshkhnl.service.admin.UnitSettingEJB;
import np.info.dpshkhnl.service.admin.VatSettingEJB;
import np.info.dpshkhnl.service.product.ItemCategoryEJB;
import np.info.dpshkhnl.service.product.ItemProductEJB;
import np.info.dpshkhnl.service.product.ItemTypeEJB;
import np.info.dpshkhnl.service.purchase.InvMasterEJB;
import np.info.dpshkhnl.service.purchase.InvoiceEJB;
import np.info.dpshkhnl.service.sales.ReceiptEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author Dpshkhnl
 */
@Named
@ViewScoped
public class SalesMB implements Serializable {
    
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
    ReceiptEJB receiptEJB;
    
     @EJB
    AccountHeadEJB accHeadEJB;
    
    @EJB
    LedgerEJB ledgerEJB;
    
    @EJB
    InvMasterEJB invMasterEJB;
    
    private InventoryReceiptModel  inventoryReceiptModel;
    private SalesDetailModel salesDetailModel;
    private List<SalesDetailModel> lstSalesDetails;
    private List<InventoryReceiptModel> lstReceiptModels;
    private List<PartnerModel> lstSupModels;
    private List<ItemCategoryModel> lstCat;
    private List<ItemTypeModel> lstItemType;
    private List<ItemProductModel> lstProduct;
    private List<UnitModel> lstUnits;
    private int catId;
    private int typeId;
    private CodeValue status;
    private InventoryMasterModel invMasterModel;
    private boolean useVat;
    private double total;
    private boolean useDiscount;
    private double gTotal;
    private double vatAmt;
    private double discountAmt;
    private String remQnty;
    private List<SalesDetailModel> selectedSalesDet;
    private List<InventoryReceiptModel> selectedReceipts;
    private List<CodeValue> lstStatus;
    public List<PartnerModel> getLstSupModels() {
        if(lstSupModels == null)
            lstSupModels = new ArrayList<>();
       lstSupModels= partnerEJB.findAllbyCustomer();
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

  

    public InventoryMasterModel getInvMasterModel() {
        return invMasterModel;
    }

    public void setInvMasterModel(InventoryMasterModel invMasterModel) {
        this.invMasterModel = invMasterModel;
    }

  
    public CodeValue getStatus() {
        if(status == null)
            status = new CodeValue();
        return status;
    }

    public void setStatus(CodeValue status) {
        this.status = status;
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
    
    
    public InventoryReceiptModel getInventoryReceiptModel() {
        if(inventoryReceiptModel == null)
            inventoryReceiptModel = new InventoryReceiptModel();
         if(inventoryReceiptModel.getSupId() == null)
            inventoryReceiptModel.setSupId(new PartnerModel());
        if(inventoryReceiptModel.getInvMasterList() == null)
            inventoryReceiptModel.setInvMasterList(new ArrayList<>());
        
        if(inventoryReceiptModel.getStatus()== null)
            inventoryReceiptModel.setStatus(new CodeValue());
        if(inventoryReceiptModel.getLstSales() == null)
             inventoryReceiptModel.setLstSales(new ArrayList<>());
        return inventoryReceiptModel;
    }

    public void setInventoryReceiptModel(InventoryReceiptModel inventoryReceiptModel) {
        this.inventoryReceiptModel = inventoryReceiptModel;
    }

    public SalesDetailModel getSalesDetailModel() {
        if(salesDetailModel == null)
            salesDetailModel = new SalesDetailModel();
        if(salesDetailModel.getInventoryReceiptModel() == null)
            salesDetailModel.setInventoryReceiptModel(new InventoryReceiptModel());
        if(salesDetailModel.getItemProductModel() == null)
            salesDetailModel.setItemProductModel(new ItemProductModel());
        if(salesDetailModel.getUnitModel() == null)
            salesDetailModel.setUnitModel(new UnitModel());
        return salesDetailModel;
    }

    public void setSalesDetailModel(SalesDetailModel salesDetailModel) {
        this.salesDetailModel = salesDetailModel;
    }

    public List<SalesDetailModel> getLstSalesDetails() {
        if (lstSalesDetails == null)
            lstSalesDetails = new ArrayList<>();
        return lstSalesDetails;
    }

    public void setLstSalesDetails(List<SalesDetailModel> lstSalesDetails) {
        this.lstSalesDetails = lstSalesDetails;
    }

    public List<InventoryReceiptModel> getLstReceiptModels() {
        if (lstReceiptModels == null)
            lstReceiptModels = new ArrayList<>();
        return lstReceiptModels;
    }

    public void setLstReceiptModels(List<InventoryReceiptModel> lstReceiptModels) {
        this.lstReceiptModels = lstReceiptModels;
    }
    
     public void addProductItem()
    {
        getLstSalesDetails();
        salesDetailModel.setItemProductModel(productEJB.find(salesDetailModel.getItemProductModel().getProductId()));
        lstSalesDetails.add(salesDetailModel);
      
        getInventoryReceiptModel();
        total = total+salesDetailModel.getTotalCost();
        getGrandTotal();
        salesDetailModel = null;
    }
     
     public void getGrandTotal()
    {
       if(inventoryReceiptModel.getReceiptVatAmt() == null)
       {
           inventoryReceiptModel.setReceiptVatAmt(0.00);
       }
       if(inventoryReceiptModel.getReceiptDiscountAmt()== null)
       {
           inventoryReceiptModel.setReceiptDiscountAmt(0.00);
       }
       
        inventoryReceiptModel.setReceiptAmt(total+inventoryReceiptModel.getReceiptVatAmt() -inventoryReceiptModel.getReceiptDiscountAmt());
    }
     
      public void getSalesTotal()
     {
         salesDetailModel.setTotalCost(salesDetailModel.getOutQty()*salesDetailModel.getPerCost());
         
     }
     public void calculateVat()
     {
        if(useVat)
        {
          //  vat = vatSettingEJB.fi
            inventoryReceiptModel.setReceiptVatAmt(total*13/100);
            
        }
        else
        {
           inventoryReceiptModel.setReceiptVatAmt(0.00); 
        }
           getGrandTotal();      
     }

    public List<SalesDetailModel> getSelectedSalesDet() {
        if(selectedSalesDet == null)
            selectedSalesDet = new ArrayList<>();
        return selectedSalesDet;
    }

    public void setSelectedSalesDet(List<SalesDetailModel> selectedSalesDet) {
        this.selectedSalesDet = selectedSalesDet;
    }
    
     public void saveSales()
    {
        inventoryReceiptModel.setCreatedBy(1);
        inventoryReceiptModel.setLstSales(lstSalesDetails);
        inventoryReceiptModel.setStatus(codeValueEJB.getCodeValueByTypeAndLabel("InvoiceStatus","Unpaid"));
       
        List<InventoryMasterModel> lstMaster = new ArrayList<>();
        for(SalesDetailModel purModel : lstSalesDetails)
        {
            purModel.setInventoryReceiptModel(inventoryReceiptModel);
            InventoryMasterModel invMasterModel = new InventoryMasterModel();
            invMasterModel.setProductId(purModel.getItemProductModel());
            invMasterModel.setOutQty(unitEJB.getUnitCountOfAllParent(purModel.getUnitModel().getUnitId())*purModel.getOutQty());
            invMasterModel.setInvReceipt(purModel.getInventoryReceiptModel());
            invMasterModel.setTotalCost(purModel.getTotalCost());
            
            invMasterModel.setUnitSalePrice(purModel.getCostPrice());
            
            lstMaster.add(invMasterModel);
        }
        
        AccHead accountHead = null;
         List<AccHead>  lst = accHeadEJB.findbyAccount("Sales");
         for(AccHead acc : lst)
         {
             if (acc.getAccName().equals("Sales"))
             {
                 accountHead = acc;
             }else
             {
                 addDetailMessage("Please Create Account Head With Acc Name Sales"); 
                 return;
             }
         }
          if(accountHead == null)
         {
             addDetailMessage("Please Create Account Head With Acc Name Purchase"); 
                 return;
         }
         
        Ledger ledger = new Ledger();
        ledger.setCrAmt(inventoryReceiptModel.getReceiptAmt());
        ledger.setToAccountHead(accountHead);
        ledger.setFiscalYear(null);
        ledger.setAccountHead(partnerEJB.find(inventoryReceiptModel.getSupId().getId()).getAccountHead());
        ledger.setPostedDate(inventoryReceiptModel.getReceiptDtEn());
        ledger.setRemarks("Sales To "+inventoryReceiptModel.getSupId().getMemberName());
        ledger.setCreatedDate(new Date());
        
        try {
            ledgerEJB.postToLedger(ledger,accHeadEJB);
        } catch (Exception ex) {
            addDetailMessage("Error Occured ,Please Try again Later "); 
                 return;
        }
        inventoryReceiptModel.setInvMasterList(lstMaster);
        receiptEJB.save(inventoryReceiptModel);
        addDetailMessage("Sales Completed");
    }
     
     
        public void SalesReturn()
    {
        inventoryReceiptModel.setCreatedBy(1);
        inventoryReceiptModel.setLstSales(lstSalesDetails);
        inventoryReceiptModel.setStatus(codeValueEJB.getCodeValueByTypeAndLabel("InvoiceStatus","Sales Return"));
       
        List<InventoryMasterModel> lstMaster = new ArrayList<>();
        
        for(SalesDetailModel purModel : lstSalesDetails)
        {
            List<InventoryMasterModel> lstInvMaster = invMasterEJB.findbyProductId(purModel.getItemProductModel().getProductId());
            purModel.setInventoryReceiptModel(inventoryReceiptModel);
           for(InventoryMasterModel invMasterModel : lstInvMaster){
            invMasterModel.setOutQty(unitEJB.getUnitCountOfAllParent(purModel.getUnitModel().getUnitId())*purModel.getRetQty());
            invMasterModel.setInvReceipt(purModel.getInventoryReceiptModel());
            invMasterModel.setTotalCost(purModel.getTotalCost());
            
            //invMasterModel.setUnitSalePrice(purModel.getSellingPrice());
            
            lstMaster.add(invMasterModel);
           }
        }
        
        AccHead accountHead = null;
         List<AccHead>  lst = accHeadEJB.findbyAccount("Sales");
         for(AccHead acc : lst)
         {
             if (acc.getAccName().equals("Sales"))
             {
                 accountHead = acc;
             }else
             {
                 addDetailMessage("Please Create Account Head With Acc Name Sales"); 
                 return;
             }
         }
        Ledger ledger = new Ledger();
        ledger.setDrAmt(inventoryReceiptModel.getReceiptAmt());
        ledger.setAccountHead(accountHead);
        ledger.setToAccountHead(partnerEJB.find(inventoryReceiptModel.getSupId().getId()).getAccountHead());
        ledger.setFiscalYear(null);
        ledger.setPostedDate(inventoryReceiptModel.getReceiptDtEn());
        ledger.setRemarks("Sales Return From "+inventoryReceiptModel.getSupId().getMemberName());
        ledger.setCreatedDate(new Date());
        
        try {
            ledgerEJB.postToLedger(ledger,accHeadEJB);
        } catch (Exception ex) {
            addDetailMessage("Error Occured ,Please Try again Later "); 
                 return;
        }
        inventoryReceiptModel.setInvMasterList(lstMaster);
        receiptEJB.save(inventoryReceiptModel);
        addDetailMessage("Sales Return Completed");
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
    
    public void getSalesReturnTotal()
     {
         salesDetailModel.setTotalCost(salesDetailModel.getRetQty()*salesDetailModel.getPerCost());
         
     }

     public void loadsalesDetails()
    {
       total = 0.0;
       gTotal = 0.0;
       vatAmt = 0.0;
       discountAmt = 0.0;
            lstSalesDetails = null;
            getLstSalesDetails();
            for (InventoryReceiptModel inv : selectedReceipts)
            {
                if(inv.getReceiptAmt() == null)
                    inv.setReceiptAmt(0.00);
                if(inv.getReceiptVatAmt() == null)
                    inv.setReceiptVatAmt(0.00);
                if(inv.getReceiptDiscountAmt() == null)
                    inv.setReceiptDiscountAmt(0.00);
                 List<SalesDetailModel> lstTemp = inv.getLstSales();
                 lstSalesDetails.addAll(lstTemp);
                  gTotal = gTotal + inv.getReceiptAmt();
                  vatAmt = vatAmt + inv.getReceiptVatAmt();
                  discountAmt = discountAmt + inv.getReceiptDiscountAmt();
                       
            }
          total = gTotal-vatAmt+discountAmt; 
    }
     public void loadIReceiptByCustomer()
    {
        getLstReceiptModels();
         lstReceiptModels =receiptEJB.findbySupplierId(inventoryReceiptModel.getSupId().getId(),status.getCvId());
    }

    public List<InventoryReceiptModel> getSelectedReceipts() {
        if(selectedReceipts == null)
            selectedReceipts = new ArrayList<>();
        return selectedReceipts;
    }

    public void setSelectedReceipts(List<InventoryReceiptModel> selectedReceipts) {
        this.selectedReceipts = selectedReceipts;
    }
    
    public void loadCostPrice()
    {
       int remQ=0; 
         ItemProductModel itm = productEJB.find(salesDetailModel.getItemProductModel().getProductId());
         List<InventoryMasterModel> lstInvMaster = invMasterEJB.findbyProductId(itm.getProductId());
         if(lstInvMaster.size() == 0)
         {
               addDetailMessage("No Item Found At Stock,Please Purchase first"); 
                 return; 
         }
         salesDetailModel.setCostPrice(lstInvMaster.get(0).getUnitCostPrice());
         for(InventoryMasterModel invMaster : lstInvMaster)
         {
             remQ = remQ+invMaster.getInQty() - invMaster.getOutQty();
             
         }
         remQnty = String.valueOf(remQ)+" Pcs";
         System.out.println("np.info.dpshkhnl.bean.sales.SalesMB.loadCostPrice()"+remQnty);
    }

    public String getRemQnty() {
        return remQnty;
    }

    public void setRemQnty(String remQnty) {
        this.remQnty = remQnty;
    }
}
