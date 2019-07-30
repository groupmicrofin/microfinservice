package com.gmf.services.service;

import com.gmf.services.model.MeetingCalender;
import com.gmf.services.model.MicroBankParam;
import com.gmf.services.repository.MicroBankCalenderDaoServiceImpl;
import com.gmf.services.repository.MicroBankMemberDaoServiceImpl;
import com.gmf.services.repository.MicrobankParamDaoServiceImpl;

public class MeetingCalenderServiceImpl implements MeetingCalenderService {

    MicroBankCalenderDaoServiceImpl calenderDaoService = new MicroBankCalenderDaoServiceImpl();
    MicroBankMemberDaoServiceImpl memberDaoService = new MicroBankMemberDaoServiceImpl();
    MicrobankParamDaoServiceImpl paramDaoService = new MicrobankParamDaoServiceImpl();

    @Override
    public void createMeetingCalender(int groupMasterID) {
        System.out.println("Meeting calender created ");
        //1. Get max current cycle no
        int maxCycleNo = calenderDaoService.fetchNextMeetingCycleNo(groupMasterID);
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
