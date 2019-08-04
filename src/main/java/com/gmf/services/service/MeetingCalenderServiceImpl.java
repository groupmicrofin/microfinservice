package com.gmf.services.service;

import com.gmf.services.model.MeetingCalender;
import com.gmf.services.model.MicroBankParam;
import com.gmf.services.repository.MicroBankCalenderDaoServiceImpl;
import com.gmf.services.repository.MicroBankMemberDaoServiceImpl;
import com.gmf.services.repository.MicrobankParamDaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeetingCalenderServiceImpl implements MeetingCalenderService {

    @Autowired
    MicroBankCalenderDaoServiceImpl calenderDaoService;
    @Autowired
    MicroBankMemberDaoServiceImpl memberDaoService;
    @Autowired
    MicrobankParamDaoServiceImpl paramDaoService;




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
        meetingCalender.setTotalActiveMembers(totalActiveLnAccts);
        calenderDaoService.createMeetingCalender(meetingCalender);
    }
}
