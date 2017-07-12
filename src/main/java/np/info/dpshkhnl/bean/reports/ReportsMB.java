/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.reports;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import np.info.dpshkhnl.model.ReportCustomModel;
import np.info.dpshkhnl.model.account.Ledger;
import np.info.dpshkhnl.model.admin.PartnerModel;
import np.info.dpshkhnl.model.admin.RouteManagementModel;
import np.info.dpshkhnl.model.purchase.InventoryInvoiceModel;
import np.info.dpshkhnl.model.sales.InventoryReceiptModel;
import np.info.dpshkhnl.model.sales.SalesDetailModel;
import np.info.dpshkhnl.service.account.LedgerEJB;
import np.info.dpshkhnl.service.admin.PartnerEJB;
import np.info.dpshkhnl.service.admin.RouteEJB;
import np.info.dpshkhnl.service.purchase.InvoiceEJB;
import np.info.dpshkhnl.service.sales.ReceiptEJB;
import np.info.dpshkhnl.service.sales.SalesDetailEJB;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;
        

/**
 *
 * @author Dpshkhnl
 */
@Named
@ViewScoped
public class ReportsMB implements Serializable {

    @EJB
    SalesDetailEJB salesDetailEJB;
    
    @EJB
    PartnerEJB partnerEJB;
    
    @EJB
    ReceiptEJB receiptEJB;
    
    @EJB
    InvoiceEJB invoiceEJB;
    
    @EJB
    RouteEJB routeEJB;
    
    @EJB
    LedgerEJB ledgerEJB;

    private Date dateFrm;
    private Date dateTo;
    private int catId;
    private int itemTypeId;
    private int productId;
    private int customerId;
    private int routeId;
    private List<PartnerModel> lstCustomer;
    private List<PartnerModel> lstSupplier;
    private List<InventoryInvoiceModel> lstPurchase;
    private List<InventoryReceiptModel> lstReceipt;
    private List<RouteManagementModel> lstRoutes;
    private List<PartnerModel> lstCustomerCredit;
    private List<Ledger> lstLedger;
    private TreeNode root;
    private List<ReportCustomModel> lstSalesDet;
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    private List<SalesDetailModel> lstSales;

    public Date getDateFrm() {
        if (dateFrm == null) {
            return new Date();
        }
        return dateFrm;
    }

    public void setDateFrm(Date dateFrm) {
        this.dateFrm = dateFrm;
    }

    public Date getDateTo() {
        if (dateTo == null) {
            return new Date();
        }
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(int itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void loadProductWiseSales() {
        lstSales = new ArrayList<>();
        lstSales = salesDetailEJB.searchByCriteria(itemTypeId, productId, catId, dateFrm, dateTo,customerId);

    }
    
    public void loadSalesReport() {
        lstReceipt = new ArrayList<>();
        lstReceipt = receiptEJB.findbyCriteria( dateFrm, dateTo,customerId);

    }
    
    public void loadPurchaseReport() {
        lstPurchase = new ArrayList<>();
        lstPurchase = invoiceEJB.findbyCriteria(dateFrm, dateTo,customerId);

    }

    public List<SalesDetailModel> getLstSales() {
        if (lstSales == null) {
            lstSales = new ArrayList<>();
        }
        return lstSales;
    }

    public void setLstSales(List<SalesDetailModel> lstSales) {
        this.lstSales = lstSales;
    }

    public void reset() {
        dateFrm = null;
        dateTo = null;
        catId = 0;
        itemTypeId = 0;
        productId = 0;
        lstSales = null;
        customerId = 0;
        routeId = 0;
        lstPurchase = null;
        lstReceipt = null;
        lstLedger = null;
    }

    public List<PartnerModel> getLstCustomer() {
        if(lstCustomer == null)
            lstCustomer = new ArrayList<>();
        if(routeId == 0)
        lstCustomer = partnerEJB.findAllbyCustomer();
        else
            lstCustomer = partnerEJB.findAllbyCustomerByRoute(routeId);
        return lstCustomer;
    }

    public void setLstCustomer(List<PartnerModel> lstCustomer) {
        this.lstCustomer = lstCustomer;
    }

    public List<InventoryInvoiceModel> getLstPurchase() {
        if(lstPurchase == null)
            lstPurchase = new ArrayList<>();
        return lstPurchase;
    }

    public void setLstPurchase(List<InventoryInvoiceModel> lstPurchase) {
        this.lstPurchase = lstPurchase;
    }

    public List<InventoryReceiptModel> getLstReceipt() {
        if(lstReceipt == null)
            lstReceipt = new ArrayList<>();
        return lstReceipt;
    }

    public void setLstReceipt(List<InventoryReceiptModel> lstReceipt) {
        this.lstReceipt = lstReceipt;
    }

    public List<PartnerModel> getLstSupplier() {
        if(lstSupplier == null);
        lstSupplier = new ArrayList<>();
        lstSupplier  = partnerEJB.findAllbySupplier();
        return lstSupplier;
    }

    public void setLstSupplier(List<PartnerModel> lstSupplier) {
        this.lstSupplier = lstSupplier;
    }

    public List<RouteManagementModel> getLstRoutes() {
       if(lstRoutes == null)
           lstRoutes = new ArrayList<>();
      lstRoutes= routeEJB.findAll();
        return lstRoutes;
    }

    public void setLstRoutes(List<RouteManagementModel> lstRoutes) {
        this.lstRoutes = lstRoutes;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }
    
    public void loadCreditLegers()
    {
        lstCustomerCredit = null;
        getLstCustomerCredit();
        lstCustomerCredit = partnerEJB.findAllCustomerCreditByToDate(dateTo,routeId,customerId);
    }

    public List<PartnerModel> getLstCustomerCredit() {
        if(lstCustomerCredit == null)
            lstCustomerCredit = new ArrayList<>();
        return lstCustomerCredit;
    }

    public void setLstCustomerCredit(List<PartnerModel> lstCustomerCredit) {
        this.lstCustomerCredit = lstCustomerCredit;
    }

    public TreeNode getRoot() {
        if(root == null)
              root = new DefaultTreeNode("Root", null);
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    public void loadSalesDetails()
    {
        
       lstReceipt = receiptEJB.findbyCriteria( dateFrm, dateTo,customerId);
        root = new DefaultTreeNode("Root", null);
		Iterator<InventoryReceiptModel> itr = lstReceipt.iterator();
		while (itr.hasNext()) {
			InventoryReceiptModel invRecipt = itr.next();
			TreeNode documents = new DefaultTreeNode(new ReportsDocument(invRecipt), root);

			List<SalesDetailModel> listSales = invRecipt.getLstSales();
//                        if(listSales.size()>1)
//                        {
//                            TreeNode work = new DefaultTreeNode("Root", "");
//                        }
			Iterator<SalesDetailModel> itrSales = listSales.iterator();
			while (itrSales.hasNext()) {
				SalesDetailModel salesDet = itrSales.next();
				TreeNode work = new DefaultTreeNode(new ReportsDocument(salesDet), documents);
			}

		}
    }
    
    public void loadCashFlow()
    {
        getLstLedger();
        lstLedger = ledgerEJB.loadDataByDate(dateFrm,dateTo);
        System.out.println("np.info.dpshkhnl.bean.reports.ReportsMB.loadCashFlow()"+lstLedger.size());
    }

    public List<Ledger> getLstLedger() {
        if(lstLedger == null)
            lstLedger = new ArrayList<>();
        return lstLedger;
    }

    public void setLstLedger(List<Ledger> lstLedger) {
        this.lstLedger = lstLedger;
    }
    
    public void loadSalesDetailsByRoute()
    {
        getLstSalesDet();
        lstSalesDet = salesDetailEJB.loadDetails(dateFrm,dateTo,routeId);
        
    }

    public List<ReportCustomModel> getLstSalesDet() {
        if(lstSalesDet == null)
            lstSalesDet = new ArrayList<>();
        return lstSalesDet;
    }

    public void setLstSalesDet(List<ReportCustomModel> lstSalesDet) {
        this.lstSalesDet = lstSalesDet;
    }
}
