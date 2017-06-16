/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import np.info.dpshkhnl.model.account.AccHead;
import np.info.dpshkhnl.model.admin.MasterVATModel;
import np.info.dpshkhnl.service.account.AccountHeadEJB;
import np.info.dpshkhnl.service.admin.VatSettingEJB;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author Dpshkhnl
 */
@Named
@ViewScoped
public class TaxSettingMB implements Serializable{

    @EJB
     VatSettingEJB vatSettingEJB;
    
    @EJB
    AccountHeadEJB accountHeadEJB;
    
    private MasterVATModel masterVATModel;
    private List<AccHead> accHeads;
    
    @PostConstruct
    public void init()
    {
        getMasterVATModel();
        if(!vatSettingEJB.findAll().isEmpty())
        {
           masterVATModel = vatSettingEJB.findAll().get(0);
        }
    }

    public MasterVATModel getMasterVATModel() {
        if(masterVATModel == null)
            masterVATModel = new MasterVATModel();
        if(masterVATModel.getPurchaseAccount() == null)
            masterVATModel.setPurchaseAccount(new AccHead());
        if(masterVATModel.getSalesAccount()== null)
            masterVATModel.setSalesAccount(new AccHead());
        if(masterVATModel.getServiceChargeAccount()== null)
            masterVATModel.setServiceChargeAccount(new AccHead());
        
        
        return masterVATModel;
    }

    public void setMasterVATModel(MasterVATModel masterVATModel) {
        this.masterVATModel = masterVATModel;
    }

    public List<AccHead> getAccHeads() {
        if(accHeads == null)
            accHeads = new ArrayList<>();
        accHeads=accountHeadEJB.findAll();
        return accHeads;
    }

    public void setAccHeads(List<AccHead> accHeads) {
        this.accHeads = accHeads;
    }
    
     public void saveVatSetting()
    {
        
         String msg;
        if (masterVATModel.getVatId() == 0) {
            vatSettingEJB.save(masterVATModel);
            msg = "Vat Setting created successfully";
        } else {
            vatSettingEJB.update(masterVATModel);
            msg = "Vat Setting updated successfully";
        }
        addDetailMessage(msg);
    }
}
