package com.gmf.services.service;

import com.gmf.services.model.MeetingCalender;
import com.gmf.services.model.MicroBankMember;
import com.gmf.services.model.MicroBankParam;
import com.gmf.services.repository.*;

public class CollectionServiceImpl implements CollectionService {

    MicrobankParamDaoServiceImpl microbankParamDaoServiceImpl = new MicrobankParamDaoServiceImpl();
    MicroBankCalenderDaoServiceImpl microBankCalenderDaoServiceImpl = new MicroBankCalenderDaoServiceImpl();
    MicroBankMemberDaoServiceImpl microBankMemberDaoServiceImpl = new MicroBankMemberDaoServiceImpl();


    //share collection for all
    @Override
    public void shareCollectionForAll(int groupMasterId) {
        System.out.println("in microBank update service");

        //get share face value
        MicroBankParam microBankParam = microbankParamDaoServiceImpl.findById(groupMasterId);
        int shareFaceValue = microBankParam.getShareFaceValue();
        System.out.println("share face value:" + shareFaceValue);

        //3-get calender id
        MeetingCalender meetingCalender = microBankCalenderDaoServiceImpl.findByGroupMasterIdAndStatus(groupMasterId, "A");
        int currentCalenderID = meetingCalender.getId();
        System.out.println("get calender id :" + currentCalenderID);

        //TODO add validation for already completed share collection for this calender id
        //updateMicroBankMember
        microBankMemberDaoServiceImpl.addMeetingSharebalanceForAllMember(shareFaceValue, currentCalenderID, groupMasterId);
        System.out.println("updated share balance");
    }

    @Override
    public void totalCashBalance(int groupMasterId) {
        System.out.println("getting total cash balance");

        //get total share balance
        MicroBankMemberDaoServiceImpl microBankMemberDaoServiceImpl = new MicroBankMemberDaoServiceImpl();
        MicroBankMember microBankMember = new MicroBankMember();
        String memberStatus = "A";
        int totalShareBalance = microBankMemberDaoServiceImpl.getTotalShareBalance(groupMasterId, memberStatus);
        System.out.println("total sharebalance :" + totalShareBalance);

        //get total loan disbu
        MicrobankLoanMasterDaoServiceImpl microbankLoanMasterDaoServiceImpl = new MicrobankLoanMasterDaoServiceImpl();
        int totalLoanDisb = microbankLoanMasterDaoServiceImpl.getTotalLoanDisb(groupMasterId);
        System.out.println("total loan disb:" + totalLoanDisb);


        //get total loan instalment paid amount
        MicroBankLoanInstallmentDaoServiceImpl microBankLoanInstallmentDaoServic = new MicroBankLoanInstallmentDaoServiceImpl();
        int totalLoanInstallmentAmountPaid = microBankLoanInstallmentDaoServic.getTotalLoanInstallmentAmountPais(groupMasterId);
        System.out.println("total loan installment amount paid:" + totalLoanInstallmentAmountPaid);

        //get total other expenses
        MicroBankDaoServiceImpl microBankDaoServiceImpl = new MicroBankDaoServiceImpl();
        int totalOtherExpenses = microBankDaoServiceImpl.totalOtherExpense(groupMasterId);
        System.out.println("total other expenses:" + totalOtherExpenses);

        //total cash balance
        int totalCashBalance = totalShareBalance + totalLoanInstallmentAmountPaid - totalLoanDisb - totalOtherExpenses;
        System.out.println("total cash balance:" + totalCashBalance);
    }

    @Override
    public void totalEaening(int groupMasterId) {
        System.out.println("getting total earning");

        //get total amount interest earned
        MicrobankLoanMasterDaoServiceImpl microbankLoanMasterDaoServiceImpl = new MicrobankLoanMasterDaoServiceImpl();
        double totalEarnedInterest = microbankLoanMasterDaoServiceImpl.totalEarnedInterest(groupMasterId);
        System.out.println("total interest earned:" + totalEarnedInterest);

        //get total amount misc directory
        MicroBankDaoServiceImpl microBankDaoServiceImpl = new MicroBankDaoServiceImpl();
        double totalMiscAmount = microBankDaoServiceImpl.totalMiscDr(groupMasterId);
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
        MicroBankParam microBankParam = microbankParamDaoServiceImpl.findById(groupMasterID);
        int shareFaceValue = microBankParam.getShareFaceValue();
        System.out.println("share face value:" + shareFaceValue);

        //3-get calender id
        MeetingCalender meetingCalender = microBankCalenderDaoServiceImpl.findByGroupMasterIdAndStatus(groupMasterID, "A");
        int currentCalenderID = meetingCalender.getId();
        System.out.println("get calender id :" + currentCalenderID);

        microBankMemberDaoServiceImpl.addMeetingSharBalanceForIndividual(shareFaceValue, currentCalenderID, groupMasterID, id);
        System.out.println("Individual share collection updated");
    }
}
