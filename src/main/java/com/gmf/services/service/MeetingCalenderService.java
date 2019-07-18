package com.gmf.services.service;

import com.gmf.services.model.MeetingCalender;
import com.gmf.services.model.MicroBankParam;
import com.gmf.services.repository.MicroBankCalenderDaoService;
import com.gmf.services.repository.MicroBankMemberDaoService;
import com.gmf.services.repository.MicrobankParamDaoService;

public class MeetingCalenderService {

    MicroBankCalenderDaoService calenderDaoService = new MicroBankCalenderDaoService();
    MicroBankMemberDaoService memberDaoService = new MicroBankMemberDaoService();
    MicrobankParamDaoService paramDaoService = new MicrobankParamDaoService();

    public void createMeetingCalender(int groupMasterID) {
        System.out.println("Meeting calender created ");
        //1. Get max current cycle no
        int maxCycleNo = calenderDaoService.maxCurrentCycleNumber(groupMasterID);
        //2. Get total active member count
        int totalActiveLnAccts = memberDaoService.getTotalActiveMember(groupMasterID);
        //3. Get Group Param
        MicroBankParam microBankParam = paramDaoService.findById(groupMasterID);
        int shareFaceValue = microBankParam.getShareFaceValue();
        //4. create meeting calender
        MeetingCalender meetingCalender = new MeetingCalender();
        meetingCalender.setCycleNo(maxCycleNo);
        meetingCalender.setGroupMasterId(groupMasterID);
        meetingCalender.setShareAmount(shareFaceValue);
        meetingCalender.setStatus("A");
        calenderDaoService.createMeetingCalender(meetingCalender);
    }
}
