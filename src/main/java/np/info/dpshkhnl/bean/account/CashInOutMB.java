/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.print.attribute.standard.Severity;
import np.info.dpshkhnl.model.account.AccHead;
import np.info.dpshkhnl.model.account.Accounts;
import np.info.dpshkhnl.model.account.CustomAccountDataModel;
import np.info.dpshkhnl.model.account.FiscalYrModel;
import np.info.dpshkhnl.model.account.Ledger;
import np.info.dpshkhnl.model.admin.PartnerModel;
import np.info.dpshkhnl.service.account.AccountEJB;
import np.info.dpshkhnl.service.account.AccountHeadEJB;
import np.info.dpshkhnl.service.account.LedgerEJB;
import np.info.dpshkhnl.service.admin.PartnerEJB;
import np.info.dpshkhnl.util.JrUtils;
import static np.info.dpshkhnl.util.Utils.addDetailMessage;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author Dpshkhnl
 */

@Named
@ViewScoped
public class CashInOutMB implements Serializable{
    
    @EJB
    AccountEJB accountEJB;

    
    @EJB
    AccountHeadEJB accHeadEJB;
    
    @EJB
    PartnerEJB partnerEJB;
    
    @EJB
    LedgerEJB ledgerEJB;
    
    private List<Accounts> lstCashInAccHead;
    private List<Accounts> lstCashOutAccHead;
    private List<AccHead> lstBankAndCash;
    private List<AccHead> lstPaymentFrom;
    private int paymentAccHead;
    private List<CustomAccountDataModel> lstData;
    private AccHead payAccHead;
    private List<Ledger> lstLastTransactions;
    private Ledger ledger;
    private String amountInWords;
    private double amount;
    
    public List<Accounts> getLstCashInAccHead() {
        if(lstCashInAccHead == null)
            lstCashInAccHead = new ArrayList<>();
        lstCashInAccHead = accountEJB.findbyRemarks("Cash In");
        
        return lstCashInAccHead;
    }

    public void setLstCashInAccHead(List<Accounts> lstCashInAccHead) {
        this.lstCashInAccHead = lstCashInAccHead;
    }

    public List<Accounts> getLstCashOutAccHead() {
        if(lstCashOutAccHead == null)
            lstCashOutAccHead = new ArrayList<>();
         lstCashOutAccHead = accountEJB.findbyRemarks("Cash Out");
        return lstCashOutAccHead;
    }

    public void setLstCashOutAccHead(List<Accounts> lstCashOutAccHead) {
        this.lstCashOutAccHead = lstCashOutAccHead;
    }

    public List<AccHead> getLstBankAndCash() {
        if(lstBankAndCash == null)
            lstBankAndCash = new ArrayList<>();
        lstBankAndCash = accHeadEJB.findbyAccount("Bank and Cash");
        return lstBankAndCash;
    }

    public void setLstBankAndCash(List<AccHead> lstBankAndCash) {
        this.lstBankAndCash = lstBankAndCash;
    }

    public List<AccHead> getLstPaymentFrom() {
        if(lstPaymentFrom == null)
            lstPaymentFrom = new ArrayList<>();
        if(paymentAccHead != 0)
        {
            Accounts accHead = accountEJB.find(paymentAccHead);
            lstPaymentFrom = accHeadEJB.findbyAccount(accHead.getAccName());
        }
        return lstPaymentFrom;
    }

    public void setLstPaymentFrom(List<AccHead> lstPaymentFrom) {
        this.lstPaymentFrom = lstPaymentFrom;
    }

    public int getPaymentAccHead() {
        return paymentAccHead;
    }

    public void setPaymentAccHead(int paymentAccHead) {
        this.paymentAccHead = paymentAccHead;
    }

    public List<CustomAccountDataModel> getLstData() {
        if(lstData == null)
            lstData = new ArrayList<>();
        return lstData;
    }

    public void setLstData(List<CustomAccountDataModel> lstData) {
        this.lstData = lstData;
    }
    
    public void loadDetails()
    {
        lstData = null;
        getLstData();
        payAccHead = accHeadEJB.find(payAccHead.getAccHeadId());
        
        CustomAccountDataModel data = new CustomAccountDataModel();
        data.setSno(1);
        data.setName("Account  Name ");
        data.setDetails(payAccHead.getAccName());
        lstData.add(data);
        
        data = new CustomAccountDataModel();
        data.setSno(2);
        data.setName("Account Code ");
        data.setDetails(payAccHead.getAccCode());
        lstData.add(data);
        
        data = new CustomAccountDataModel();
        data.setSno(3);
        data.setName("Receiveable Amount");
        data.setDetails(String.valueOf(payAccHead.getDrBalance()));
        lstData.add(data);
        
        data = new CustomAccountDataModel();
        data.setSno(3);
        data.setName("Payable Amount");
        data.setDetails(String.valueOf(payAccHead.getCrBalance()));
        lstData.add(data);
        
        if(partnerEJB.findbyAccountHead(payAccHead.getAccHeadId()) != null){
            
            PartnerModel partnerModel = partnerEJB.findbyAccountHead(payAccHead.getAccHeadId());
            data = new CustomAccountDataModel();
         data.setSno(3);
        data.setName("Party Name");
        data.setDetails(partnerModel.getMemberName());
        lstData.add(data);
        
        data = new CustomAccountDataModel();
         data.setSno(4);
        data.setName("Party Address");
        data.setDetails(partnerModel.getMemberAddress());
        lstData.add(data);
        
        data = new CustomAccountDataModel();
         data.setSno(5);
        data.setName("Contact Person");
        data.setDetails(partnerModel.getContactPerson());
        lstData.add(data);
        data = new CustomAccountDataModel();
         data.setSno(5);
        data.setName("Contact");
        data.setDetails(partnerModel.getMemberContactNo());
        lstData.add(data);
        
         
        }
        
        lstLastTransactions = null;
        getLstLastTransactions();
        lstLastTransactions = ledgerEJB.findbyAccountHead(payAccHead.getAccHeadId());
        ledger.setToAccountHead(payAccHead);
        
    }

    public AccHead getPayAccHead() {
        if(payAccHead == null)
            payAccHead = new AccHead();
        if(payAccHead.getAccType() == null)
            payAccHead.setAccType(new Accounts());
        return payAccHead;
    }

    public void setPayAccHead(AccHead payAccHead) {
        this.payAccHead = payAccHead;
    }

    public List<Ledger> getLstLastTransactions() {
        if(lstLastTransactions == null)
            lstLastTransactions = new ArrayList<>();
        return lstLastTransactions;
    }

    public void setLstLastTransactions(List<Ledger> lstLastTransactions) {
        this.lstLastTransactions = lstLastTransactions;
    }

    public Ledger getLedger() {
        if(ledger == null)
            ledger = new Ledger();
        if(ledger.getAccountHead() == null)
            ledger.setAccountHead(new AccHead());
        if(ledger.getToAccountHead() == null)
            ledger.setToAccountHead(new AccHead());
        if(ledger.getFiscalYear() == null)
            ledger.setFiscalYear(new FiscalYrModel());
        
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    public String getAmountInWords() {
        System.out.println("jgkjbkhAmt:::"+amount);
        if(amount!= 0)
        {
            amountInWords = JrUtils.toWordsMiracle(String.valueOf(amount));
        }      
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void sayHello()
    {
        addDetailMessage("Hello darkness my old friennnd",FacesMessage.SEVERITY_INFO);
        
    }
    public void receivePayment()
    {
         System.out.println("Amt:::"+amount);
        getLedger();
        ledger.setDrAmt(amount);
        ledger.setToAccountHead(payAccHead);
        ledger.setCreatedDate(new Date());
        ledger.setPostedDate(new Date());
        ledger.setFiscalYear(null);
        ledger.setRemarks("Payment From "+payAccHead.getAccName());
       
        try {
            ledgerEJB.postToLedger(ledger,accHeadEJB);
        } catch (Exception ex) {
            addDetailMessage("Payment Failed",FacesMessage.SEVERITY_ERROR);
        }
        addDetailMessage("Payment SucessFul",FacesMessage.SEVERITY_INFO);
    }
    
     public void payPayment()
    {
         System.out.println("Amt:::"+amount);
        getLedger();
        ledger.setCrAmt(amount);
        ledger.setAccountHead(payAccHead);
        ledger.setCreatedDate(new Date());
        ledger.setPostedDate(new Date());
        ledger.setFiscalYear(null);
        ledger.setRemarks("Payment Done to "+payAccHead.getAccName());
       
        try {
            ledgerEJB.postToLedger(ledger,accHeadEJB);
        } catch (Exception ex) {
            addDetailMessage("Payment Failed",FacesMessage.SEVERITY_ERROR);
        }
        addDetailMessage("Payment SucessFul",FacesMessage.SEVERITY_INFO);
    }

    

   
    
}
