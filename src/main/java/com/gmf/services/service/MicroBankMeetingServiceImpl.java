package com.gmf.services.service;

import com.gmf.services.model.EvenetLog;
import com.gmf.services.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MicroBankMeetingServiceImpl implements MicroBankMeetingService {

    @Autowired
    MeetingCalenderService meetingCalenderService;
    @Autowired
    LoanService loanService;
    @Autowired
    MicroBankCalenderDaoService microBankCalenderDaoService;
    @Autowired
    MicroBankDaoService microBankDaoService;
    @Autowired
    MicroBankEventLogDaoService microBankEventLogDaoService;


    @Override
    public void startMeetingService(int groupMasterId) {
        System.out.println("meeting started for :" + groupMasterId);
        //TODO validation for already started meeting
        //Create meeting calender
        meetingCalenderService.createMeetingCalender(groupMasterId);

        //For all active loans perform interest calculation and update loan master
        loanService.performLoanInterestCharging(groupMasterId);
        System.out.println("start meeting activity completed");
    }

    @Override
    public void endMeetingUpadateService(int groupMasterId, String status) {
        System.out.println("meeting end process for :" + groupMasterId);
        //TODO validation for already closed meeting
        microBankCalenderDaoService.endMeeting(groupMasterId, status);
        System.out.println("updated meeting calender ");
    }

    @Override
    public void miscelleniousdebitevent(int groupMasterId, double expenseAmount, String description) {
        System.out.println("debit event start ");

        //update group balance
        microBankDaoService.miscelleniousDebitEventStart(groupMasterId, expenseAmount);
        System.out.println("updating  event for groupMaster");

        //insert in group event log
        EvenetLog evenetLog = new EvenetLog();
        evenetLog.setGroupMasterId(groupMasterId);
        evenetLog.setTxnCode("103");
        evenetLog.setTxnAmount(expenseAmount);
        evenetLog.setRemark(description);

        microBankEventLogDaoService.insertingGroupEventLog(evenetLog);
        System.out.println("insertinh group event log");
    }
}
