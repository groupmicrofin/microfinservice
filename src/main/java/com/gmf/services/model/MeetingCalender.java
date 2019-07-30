package com.gmf.services.model;

import java.util.Date;

public class MeetingCalender {
    private int id;
    private int groupMasterId;
    private int cycleNo;
    private int shareAmount;
    private Date meetingStartDate;
    private Date meetingEndDate;
    private int totalActiveMembers;
    private String status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupMasterId() {
        return groupMasterId;
    }

    public void setGroupMasterId(int groupMasterId) {
        this.groupMasterId = groupMasterId;
    }

    public int getCycleNo() {
        return cycleNo;
    }

    public void setCycleNo(int cycleNo) {
        this.cycleNo = cycleNo;
    }

    public int getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(int shareAmount) {
        this.shareAmount = shareAmount;
    }

    public Date getMeetingStartDate() {
        return meetingStartDate;
    }

    public void setMeetingStartDate(Date meetingStartDate) {
        this.meetingStartDate = meetingStartDate;
    }

    public Date getMeetingEndDate() {
        return meetingEndDate;
    }

    public void setMeetingEndDate(Date meetingEndDate) {
        this.meetingEndDate = meetingEndDate;
    }

    public int getTotalActiveMembers() {
        return totalActiveMembers;
    }

    public void setTotalActiveMembers(int totalActiveMembers) {
        this.totalActiveMembers = totalActiveMembers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MicroBankMeetingCalender{" +
                "id=" + id +
                ", groupMasterId=" + groupMasterId +
                ", cycleNo=" + cycleNo +
                ", shareAmount=" + shareAmount +
                ", meetingStartDate=" + meetingStartDate +
                ", meetingEndDate=" + meetingEndDate +
                ", totalActiveMembers=" + totalActiveMembers +
                ", status='" + status + '\'' +
                '}';
    }
}
