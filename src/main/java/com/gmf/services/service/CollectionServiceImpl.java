package com.gmf.services.service;

import com.gmf.services.model.MeetingCalender;
import com.gmf.services.model.MicroBankMember;
import com.gmf.services.model.MicroBankParam;
import com.gmf.services.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    MicrobankParamDaoService microbankParamDaoService;
    @Autowired
    MicroBankCalenderDaoService microBankCalenderDaoService;
    @Autowired
    MicroBankMemberDaoService microBankMemberDaoService;
    @Autowired
    MicrobankLoanMasterDaoService microbankLoanMasterDaoService;
    @Autowired
    MicroBankLoanInstallmentDaoService microBankLoanInstallmentDaoServic;
    @Autowired
    MicroBankDaoService microBankDaoService;

    //share collection for all
    @Override
    public void shareCollectionForAll(int groupMasterId) {
        System.out.println("in microBank update service");

        //get share face value
        MicroBankParam microBankParam = microbankParamDaoService.findById(groupMasterId);
        int shareFaceValue = microBankParam.getShareFaceValue();
        System.out.println("share face value:" + shareFaceValue);

        //3-get calender id
        MeetingCalender meetingCalender = microBankCalenderDaoService.findByGroupMasterIdAndStatus(groupMasterId, "A");
        int currentCalenderID = meetingCalender.getId();
        System.out.println("get calender id :" + currentCalenderID);

        //TODO add validation for already completed share collection for this calender id
        //updateMicroBankMember
        microBankMemberDaoService.addMeetingSharebalanceForAllMember(shareFaceValue, currentCalenderID, groupMasterId);
        System.out.println("updated share balance");
    }

    @Override
    public double totalCashBalance(int groupMasterId) {
        System.out.println("getting total cash balance");

        //get total share balance
        MicroBankMember microBankMember = new MicroBankMember();
        String memberStatus = "A";
        int totalShareBalance = microBankMemberDaoService.getTotalShareBalance(groupMasterId, memberStatus);
        System.out.println("total sharebalance :" + totalShareBalance);

        //get total loan disbu
        int totalLoanDisb = microbankLoanMasterDaoService.getTotalLoanDisb(groupMasterId);
        System.out.println("total loan disb:" + totalLoanDisb);

        //get total loan instalment paid amount
        int totalLoanInstallmentAmountPaid = microBankLoanInstallmentDaoServic.getTotalLoanInstallmentAmountPais(groupMasterId);
        System.out.println("total loan installment amount paid:" + totalLoanInstallmentAmountPaid);

        //get total other expenses
        int totalOtherExpenses = microBankDaoService.totalOtherExpense(groupMasterId);
        System.out.println("total other expenses:" + totalOtherExpenses);

        //total cash balance
        double totalCashBalance = totalShareBalance + totalLoanInstallmentAmountPaid - totalLoanDisb - totalOtherExpenses;
        System.out.println("total cash balance:" + totalCashBalance);

        return totalCashBalance;
    }

    @Override
    public void totalEarning(int groupMasterId) {
        System.out.println("getting total earning");

        //get total amount interest earned
        double totalEarnedInterest = microbankLoanMasterDaoService.totalEarnedInterest(groupMasterId);
        System.out.println("total interest earned:" + totalEarnedInterest);

        //get total amount misc directory
        double totalMiscAmount = microBankDaoService.totalMiscDr(groupMasterId);
        System.out.println("total misc amount:" + totalMiscAmount);

        //get total income available
        double totalEarning = totalEarnedInterest - totalMiscAmount;
        System.out.println("tottal earning :" + totalEarning);
    }

    //share collection for individual
    @Override
    public void shareCollectionForIndividual(int groupMasterID, int id) {
        System.out.println("in microBank update service");

        //get share face value
        MicroBankParam microBankParam = microbankParamDaoService.findById(groupMasterID);
        int shareFaceValue = microBankParam.getShareFaceValue();
        System.out.println("share face value:" + shareFaceValue);

        //3-get calender id
        MeetingCalender meetingCalender = microBankCalenderDaoService.findByGroupMasterIdAndStatus(groupMasterID, "A");
        int currentCalenderID = meetingCalender.getId();
        System.out.println("get calender id :" + currentCalenderID);

        microBankMemberDaoService.addMeetingSharBalanceForIndividual(shareFaceValue, currentCalenderID, groupMasterID, id);
        System.out.println("Individual share collection updated");
    }
}
