package com.gmf.services.service;

import com.gmf.services.model.EvenetLog;
import com.gmf.services.repository.MicroBankCalenderDaoServiceImpl;
import com.gmf.services.repository.MicroBankDaoServiceImpl;
import com.gmf.services.repository.MicroBankEventLogDaoServiceImpl;

public class MicroBankMeetingServiceImpl implements MicroBankMeetingService {

    MeetingCalenderServiceImpl meetingCalenderServiceImpl = new MeetingCalenderServiceImpl();
    LoanServiceImpl loanServiceImpl = new LoanServiceImpl();

    @Override
    public void startMeetingService(int groupMasterId) {
        System.out.println("meeting started for :" + groupMasterId);
        //Create meeting calender

        meetingCalenderServiceImpl.createMeetingCalender(groupMasterId);
        //For all active loans perform interest calculation and update loan master
        loanServiceImpl.performLoanInterestCharging(groupMasterId);

        System.out.println("start meeting activity completed");
    }

    @Override
    public void endMeetingUpadateService(int groupMasterId, String status) {
        System.out.println("meeting end for :" + groupMasterId);

        MicroBankCalenderDaoServiceImpl microBankCalenderDaoServiceImpl = new MicroBankCalenderDaoServiceImpl();
        microBankCalenderDaoServiceImpl.endMeeting(groupMasterId, status);
        System.out.println("updated meeting calender ");

    }

    @Override
    public void miscelleniousdebitevent(int groupMasterId, double expenseAmount, String description) {
        System.out.println("debit event start ");

        //update group balance
        MicroBankDaoServiceImpl microBankDaoServiceImpl = new MicroBankDaoServiceImpl();
        microBankDaoServiceImpl.miscelleniousDebitEventStart(groupMasterId, expenseAmount);
        System.out.println("updating  event for groupMaster");

        //insert in group event log
        EvenetLog evenetLog = new EvenetLog();
        evenetLog.setGroupMasterId(groupMasterId);
        evenetLog.setTxnCode("103");
        evenetLog.setTxnAmount(expenseAmount);
        evenetLog.setRemark(description);

        MicroBankEventLogDaoServiceImpl microBankEventLogDaoServiceImpl = new MicroBankEventLogDaoServiceImpl();
        microBankEventLogDaoServiceImpl.insertingGroupEventLog(evenetLog);
        System.out.println("insertinh group event log");

    }
}
