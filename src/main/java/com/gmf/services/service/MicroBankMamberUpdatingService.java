package com.gmf.services.service;

import com.gmf.services.model.MeetingCalender;
import com.gmf.services.model.MicroBankMember;
import com.gmf.services.model.MicroBankParam;
import com.gmf.services.repository.MicroBankCalenderDaoService;
import com.gmf.services.repository.MicroBankMemberDaoService;
import com.gmf.services.repository.MicrobankParamDaoService;

public class MicroBankMamberUpdatingService {

    MicroBankMemberDaoService microBankMemberDaoService = new MicroBankMemberDaoService();
    MicrobankParamDaoService microbankParamDaoService = new MicrobankParamDaoService();
    MicroBankCalenderDaoService microBankCalenderDaoService = new MicroBankCalenderDaoService();

    public void updateMicroBankMember(int groupMasterId) {
        System.out.println("in microBank update service");
        //dao service
        MicroBankParam microBankParam = new MicroBankParam();
        MicroBankMember microBankMember = new MicroBankMember();
        MeetingCalender meetingCalender = new MeetingCalender();
        //1-get share balance
        int getShareBalance = microBankMemberDaoService.getShareBalance(microBankMember);

        //2-get share face value
        int getshareFaceValue = microbankParamDaoService.getShareFaceValue(groupMasterId);

        //3-get calender id
        int getCalenderId = microBankCalenderDaoService.getCalenderId(meetingCalender);

        //updateMicroBankMember

        //microBankMember.setShareBalance();
        //microBankMember.setCalenderID();
        microBankMember.setCalenderID(getCalenderId);
        microBankMember.setShareBalance(getShareBalance);

    }
}
