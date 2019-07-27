package com.gmf.services.service;

public class MicroBankMeetingService {

    MeetingCalenderService  meetingCalenderService=new MeetingCalenderService();
    LoanService loanService=new LoanService();

    public void startMeetingService( int groupMasterId){
        System.out.println("meeting started for :"+groupMasterId);
        //Create meeting calender

        meetingCalenderService.createMeetingCalender(groupMasterId);
        //For all active loans perform interest calculation and update loan master
        loanService.performLoanInterestCharging(groupMasterId);

        System.out.println("start meeting activity completed");
    }
}
