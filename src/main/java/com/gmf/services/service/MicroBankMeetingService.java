package com.gmf.services.service;

public class MicroBankMeetingService {
    public void startMeetingService( int groupMasterId){
        System.out.println("meeting started for :"+groupMasterId);
        //Create meeting calender
        MeetingCalenderService  meetingCalenderService=new MeetingCalenderService();
        meetingCalenderService.createMeetingCalender(groupMasterId);
        //For all active loans perform interest calculation and update loan master
        LoanService loanService=new LoanService();
        //loanService.performLoanInterestCharging(groupMasterId);

        System.out.println("start meeting activity completed");
    }
}
