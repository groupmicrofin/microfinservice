package com.gmf.services.service;

import com.gmf.services.model.EvenetLog;
import com.gmf.services.model.LoanMaster;
import com.gmf.services.model.MeetingCalender;
import com.gmf.services.model.MicroBankParam;
import com.gmf.services.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanServiceImpl implements LoanService {

    @Autowired
    MicrobankLoanMasterDaoService loanMasterDaoService;
    @Autowired
    MicrobankParamDaoService microbankParamDaoService;
    @Autowired
    MicrobankLoanMasterDaoService microbankLoanMasterDaoService;
    @Autowired
    MicroBankEventLogDaoService microBankEventLogDaoService;
    @Autowired
    MicroBankCalenderDaoService microBankCalenderDaoService;
    @Autowired
    MicroBankLoanInstallmentDaoService microBankLoanInstallmentDaoService;

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
            double amountLoanBalance = loanMaster.getAmountLoanBalance();

            //get loan interest rate from group param
            MicroBankParam bankParam = microbankParamDaoService.findById(groupMasterId);
            float loanInterestRate = getInterestRate(bankParam);
            System.out.println("loan interest rate:" + loanInterestRate);

            //calculate int amount
            double intAmount = loanMaster.getAmountLoanBalance() * loanInterestRate;
            System.out.println("int amount:" + intAmount);

            //get group member id
            int groupMemberId = loanMaster.getGroupMemeberId();
            System.out.println("group member id:" + groupMemberId);

            //update ln balance in loan master
            loanMasterDaoService.updateLoanMaster(intAmount, groupMasterId, groupMemberId);
        }
    }

    public float getInterestRate(MicroBankParam microBankParam) {
        int baseInterestPeriod = microBankParam.getLoanInterestBase();
        //default is Monthly
        int rateDivisor = 12;
        //Base period 2 is Monthly
        if (baseInterestPeriod == 2) {
            rateDivisor = 12;
        }
        //TODO other interest rate calculation required to be handled
        float interestRate = microBankParam.getLoanInterestRate() / rateDivisor;
        float loanInterestRate = interestRate / 100;
        System.out.println("loan interest rate:" + loanInterestRate);
        return loanInterestRate;
    }

    @Override
    public void performLoanDisbursement(int groupMasterId, double amtLoanDisb, int groupMemberId, String gaurnterData) {
        System.out.println("performing loan disbursement");
        //dao
        //insert into loan master

        LoanMaster loanMaster = microbankLoanMasterDaoService.findByMemberId(groupMasterId, groupMemberId);
        System.out.println("loan master:" + loanMaster);
        if (loanMaster.getId() == 0) {
            microbankLoanMasterDaoService.create(groupMasterId, amtLoanDisb, groupMemberId);
        } else {
            System.out.println("updatinh exsting loan");
            microbankLoanMasterDaoService.updateLoanMasterForNewLoan(groupMasterId, groupMemberId, amtLoanDisb);
        }

        //insert into group event log

        MeetingCalender meetingCalender = microBankCalenderDaoService.findByGroupMasterIdAndStatus(groupMasterId, "A");
        EvenetLog evenetLog = new EvenetLog();
        evenetLog.setGroupMasterId(groupMasterId);
        evenetLog.setGroupMemberId(groupMemberId);
        evenetLog.setTxnAmount(amtLoanDisb);
        evenetLog.setTxnCode("101");
        evenetLog.setRemark(gaurnterData);
        evenetLog.setCalenderId(meetingCalender.getId());
        System.out.println("remark setting:" + gaurnterData);

        boolean result = microBankEventLogDaoService.insertingGroupEventLog(evenetLog);
        System.out.println("inserted in group event log:" + result);

    }

    @Override
    public void perfomLoanInstallment(int groupMasterId, int groupMemberId, double installmentAmount) {
        System.out.println("perform loan installment collection process");

        //get amount interest paid , amount int accur ,amount loan balance

        LoanMaster loanMaster = microbankLoanMasterDaoService.findByMemberId(groupMasterId, groupMemberId);

        double amountIntPaid = loanMaster.getAmountIntPaid();
        System.out.println("amount int paid:" + amountIntPaid);
        double amountIntAccur = loanMaster.getAmountIntAccur();
        System.out.println("amount int accur:" + amountIntAccur);
        double interestPaidAmount = amountIntAccur;
        if (installmentAmount < amountIntAccur) {
            interestPaidAmount = installmentAmount;
        }
        double amountLoanBalance = loanMaster.getAmountLoanBalance();
        System.out.println("amount loan balance:" + amountLoanBalance);
        double principleAmountPaid = installmentAmount - interestPaidAmount;
        System.out.println("princ amount paid:" + principleAmountPaid);

        //update amount interest paid
        microbankLoanMasterDaoService.updateAmountIntPaid(groupMasterId, groupMemberId, installmentAmount, interestPaidAmount);
        System.out.println("loan installment updated ");

        //get calender id
        String status = "A";
        //int calenderId=microBankCalenderDaoService.getCalenderId(groupMasterId,status);
        MeetingCalender meetingCalender1 = microBankCalenderDaoService.findByGroupMasterIdAndStatus(groupMasterId, status);
        System.out.println("meeting calender:" + meetingCalender1);
        int calenderId = meetingCalender1.getId();
        System.out.println("calenderId is :" + calenderId);


        //update loan installments table
        microBankLoanInstallmentDaoService.insertLoanInstallments(groupMasterId, groupMemberId, calenderId, interestPaidAmount, principleAmountPaid);

        //insert group event log
        String remark = "installment paid";
        EvenetLog evenetLog = new EvenetLog();
        evenetLog.setGroupMasterId(groupMasterId);
        evenetLog.setGroupMemberId(groupMemberId);
        evenetLog.setTxnCode("102");
        evenetLog.setTxnAmount(installmentAmount);
        evenetLog.setRemark(remark);
        evenetLog.setCalenderId(calenderId);

        microBankEventLogDaoService.insertingGroupEventLog(evenetLog);

    }

}
