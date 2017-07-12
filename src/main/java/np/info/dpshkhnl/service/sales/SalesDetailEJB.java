/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.service.sales;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import np.info.dpshkhnl.model.ReportCustomModel;
import np.info.dpshkhnl.model.sales.InventoryReceiptModel;
import np.info.dpshkhnl.model.sales.SalesDetailModel;
import np.info.dpshkhnl.service.core.GenericDAO;
import np.info.dpshkhnl.util.DirectSqlUtils;

/**
 *
 * @author Dpshkhnl
 */
@Stateless
public class SalesDetailEJB extends GenericDAO<SalesDetailModel> {
	public SalesDetailEJB() {
		super(SalesDetailModel.class);
} 
        
         public List<SalesDetailModel> searchByCriteria(int itemTypeId ,int productId, int catId , Date dtFrm, Date dtTo,int customerid)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String qry = "SELECT i from SalesDetailModel  i ";
            String cond ="";
            if(catId != 0)
            {
                cond +="  i.itemProductModel.itemId.catId.catId = " + catId;
            }
            if(itemTypeId!= 0)
            {
                cond+=cond.equals("")?"":" and ";
                cond +=" i.itemProductModel.itemId.itemTypeId.itemTypeId = "+itemTypeId;
            }
            if(productId!= 0)
            {
                cond+=cond.equals("")?"":" and ";
                cond +=" i.itemProductModel.productId= "+productId;
            }
            if(customerid!= 0)
            {
                cond+=cond.equals("")?"":" and ";
                cond +=" i.inventoryReceiptModel.supId.Id= "+customerid;
            }
             if(dtFrm!= null)
            {
                cond+=cond.equals("")?"":" and ";
                cond +=" i.inventoryReceiptModel.receiptDtEn >= '"+sdf.format(dtFrm)+"'";
            }
             if(dtTo!= null)
            {
                cond+=cond.equals("")?"":" and ";
                cond +=" i.inventoryReceiptModel.receiptDtEn <= '"+sdf.format(dtTo)+"'";
            }
            qry += cond.equals("")?"":" where "+cond;
            System.out.println(qry);
           Query query = getEntityManager().createQuery(qry);
           
           
		return query.getResultList();
        }
         
         public  List<ReportCustomModel> loadDetails(Date dtFrm, Date dtTo, int routeId)
         {
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String qry = "SELECT item_product.product_code as item_name,sum(inv_sale_detail.out_qty) as out_qty\n" +
                     " ,inv_sale_detail.cost_price,inv_sale_detail.per_cost,inv_unit_mcg.name as unit_name from inv_sale_detail \n" +
                     " INNER JOIN item_product on inv_sale_detail.inv_product_id = item_product.product_id\n"+
                     " INNER JOIN inv_receipt receipt on receipt.receipt_id = inv_sale_detail.inv_sale_id "+
                     " INNER JOIN inv_unit_mcg  on inv_unit_mcg.unit_id = inv_sale_detail.inv_unit_id  ";
           String cond = "";
           String groupBy ="GROUP BY \n" +
                     " per_cost,item_product.product_code,inv_sale_detail.cost_price,inv_unit_mcg.name";
        if (routeId != 0) {
            cond += "  i.supId.Id = " + routeId;
        }
        if (dtFrm != null) {
            cond += cond.equals("") ? "" : " and ";
            cond += " receipt.receipt_dt_en >= '" + sdf.format(dtFrm) + "'";
        }
        if (dtTo != null) {
            cond += cond.equals("") ? "" : " and ";
            cond += " receipt.receipt_dt_en <= '" + sdf.format(dtTo) + "'";
        }
            qry += cond.equals("") ? "" : " where " + cond; 
            qry += groupBy;
             List<ReportCustomModel> lstReport = new ArrayList<>();
            List<Object> objects = (List<Object>) DirectSqlUtils
				.getValueListFromTable(qry);
		for (Object obj : objects) {
                    ReportCustomModel custom = new ReportCustomModel();
                    Object[] values = (Object[]) obj;
                    custom.setItemName((String)values[0]);
                    custom.setOutQty((Integer)values[1]);
                    custom.setCostPrice((double)values[2]);
                    custom.setPerCost((double)values[3]);
                    custom.setUnitName((String)values[4]);
			lstReport.add(custom);
                }
            
                   return lstReport;
         } 
}
