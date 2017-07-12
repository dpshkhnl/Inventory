/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.reports;

import java.util.ArrayList;
import np.info.dpshkhnl.model.sales.InventoryReceiptModel;
import np.info.dpshkhnl.model.sales.SalesDetailModel;

/**
 *
 * @author Dpshkhnl
 */
public class ReportsDocument {
    
    private static final long serialVersionUID = 1L;
	private InventoryReceiptModel receiptModel;
	private SalesDetailModel salesDetails;

	public ReportsDocument(Object obj) {
		if (obj instanceof InventoryReceiptModel) {
			this.receiptModel = (InventoryReceiptModel) obj;
		}
		else{
			this.salesDetails = (SalesDetailModel) obj;
		}
		
			
	}

    public InventoryReceiptModel getReceiptModel() {
        return receiptModel;
    }

    public void setReceiptModel(InventoryReceiptModel receiptModel) {
        if(receiptModel == null)
            receiptModel = new InventoryReceiptModel();
        if(receiptModel.getLstSales()  == null)
            receiptModel.setLstSales(new ArrayList<>());
        this.receiptModel = receiptModel;
    }

    public SalesDetailModel getSalesDetails() {
        if(salesDetails  == null)
          salesDetails = new SalesDetailModel();
        return salesDetails;
    }

    public void setSalesDetails(SalesDetailModel salesDetails) {
        this.salesDetails = salesDetails;
    }

}
