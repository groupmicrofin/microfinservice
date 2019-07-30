package com.gmf.services.service;

import com.gmf.services.model.EvenetLog;
import com.gmf.services.model.LoanMaster;
import com.gmf.services.model.MeetingCalender;
import com.gmf.services.repository.*;

import java.util.List;

public class LoanServiceImpl implements LoanService  {

    MicrobankLoanMasterDaoServiceImpl loanMasterDaoService = new MicrobankLoanMasterDaoServiceImpl();

    @Override
    public void performLoanInterestCharging(int groupMasterId) {
        System.out.println("loan interest calculated");
        //1. Get active LoanAccounts, do below for all accounts
        List<LoanMaster> loanMasterList = loanMasterDaoService.getActiveLoamAccounts(groupMasterId);
        System.out.println("total active loans:" + loanMasterList.size());
        //2. loan accounts calculate interest
        for (LoanMaster loanMaster : loanMasterList) {
            System.out.println("loan master :" + loanMaster);
            //TODO
            //get amount loan balance from loan master
            loanMasterDaoService.fetchAmountLoanBalanceForInterestUpdate(groupMasterId);
            System.out.println("loan amount taken");
            //get loan interest rate from group param
            MicrobankParamDaoServiceImpl microbankParamDaoServiceImpl = new MicrobankParamDaoServiceImpl();
            microbankParamDaoServiceImpl.fetchLanInterestRate(groupMasterId);
            loanMasterDaoService.updateLoanMaster(loanMaster);
        }
    }

    @Override
    public void performLoanDisbursement(int groupMasterId, double amtLoanDisb, int groupMemberId, String gaurnterData) {
        System.out.println("performing loan disbursement");
        //dao
        //insert into loan master
        MicrobankLoanMasterDaoServiceImpl microbankLoanMasterDaoServiceImpl = new MicrobankLoanMasterDaoServiceImpl();
        LoanMaster loanMaster = microbankLoanMasterDaoServiceImpl.findByMemberId(groupMasterId, groupMemberId);
        System.out.println("loan master:" + loanMaster);
        if (loanMaster.getId() == 0) {
            microbankLoanMasterDaoServiceImpl.create(groupMasterId, amtLoanDisb, groupMemberId);
        } else {
            System.out.println("updatinh exsting loan");
            microbankLoanMasterDaoServiceImpl.updateLoanMasterForNewLoan(groupMasterId, groupMemberId, amtLoanDisb);
        }

        //insert into group event log
        MicroBankEventLogDaoServiceImpl microBankEventLogDaoServiceImpl = new MicroBankEventLogDaoServiceImpl();
        MicroBankCalenderDaoServiceImpl microBankCalenderDaoServiceImpl = new MicroBankCalenderDaoServiceImpl();
        MeetingCalender meetingCalender = microBankCalenderDaoServiceImpl.findByGroupMasterIdAndStatus(groupMasterId, "A");
        EvenetLog evenetLog = new EvenetLog();

        evenetLog.setGroupMasterId(groupMasterId);
        evenetLog.setGroupMemberId(groupMemberId);
        evenetLog.setTxnAmount(amtLoanDisb);
        evenetLog.setTxnCode("101");
        evenetLog.setRemark(gaurnterData);
        evenetLog.setCalenderId(meetingCalender.getId());
        System.out.println("remark setting:" + gaurnterData);

        boolean result = microBankEventLogDaoServiceImpl.insertingGroupEventLog(evenetLog);
        System.out.println("inserted in group event log:" + result);

    }

    @Override
    public void perfomLoanInstallment(int groupMasterId,int groupMemberId,double installmentAmount){
        System.out.println("perform loan installment collection process");

        //get amount interest paid , amount int accur ,amount loan balance
        LoanMaster loanMaster=new LoanMaster();
        double amountIntPaid=loanMaster.getAmountIntPaid();
        double amountIntAccur=loanMaster.getAmountIntAccur();
        double amountLoanBalance=loanMaster.getAmountLoanBalance();
        MicrobankLoanMasterDaoServiceImpl microbankLoanMasterDaoServiceImpl =new MicrobankLoanMasterDaoServiceImpl();
        microbankLoanMasterDaoServiceImpl.findByMemberId(groupMasterId,groupMemberId);


        double principaleLoanInstallmentAmount=installmentAmount-amountIntAccur;
        double interestLoanInstallmentAmount=amountIntAccur;
        double principleAmountPaid=installmentAmount-amountIntAccur;

        //update amount interest paid
        microbankLoanMasterDaoServiceImpl.updateAmountIntPaid( installmentAmount, groupMasterId ,groupMemberId );
        System.out.println("loan installment updated ");

        //get calender id
        String status="A";
        MicroBankCalenderDaoServiceImpl microBankCalenderDaoServiceImpl = new MicroBankCalenderDaoServiceImpl();
        int calenderId=microBankCalenderDaoServiceImpl.getCalenderId(groupMasterId,status);

        //update loan installments table

        MicroBankLoanInstallmentDaoServiceImpl microBankLoanInstallmentDaoServiceImpl =new MicroBankLoanInstallmentDaoServiceImpl();
        //LoanService loanService=new LoanMaster();
        microBankLoanInstallmentDaoServiceImpl.insertLoanInstallments(groupMasterId,groupMemberId,calenderId,amountIntPaid,principleAmountPaid);


        //insert group event log

        MicroBankEventLogDaoServiceImpl microBankEventLogDaoServiceImpl =new MicroBankEventLogDaoServiceImpl();
        String remark="installment paid";

        EvenetLog evenetLog=new EvenetLog();
        evenetLog.setGroupMasterId(groupMasterId);
        evenetLog.setGroupMasterId(groupMemberId);
        evenetLog.setTxnCode("101");
        evenetLog.setTxnAmount(installmentAmount);
        evenetLog.setRemark(remark);
        evenetLog.setCalenderId(calenderId);


        microBankEventLogDaoServiceImpl.insertingGroupEventLog(evenetLog);


    }

}
