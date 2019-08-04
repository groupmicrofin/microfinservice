package com.gmf.services.repository;

import com.gmf.services.model.MeetingCalender;

public interface MicroBankCalenderDaoService {

    public int fetchNextMeetingCycleNo(int groupMasterId);

    public void createMeetingCalender(MeetingCalender meetingCalender);

    public MeetingCalender findByGroupMasterIdAndStatus(int groupMasterId, String status);

    public void endMeeting(int groupmasterId , String status);

    public int getCalenderId(int groupMasterId,String status);


}
