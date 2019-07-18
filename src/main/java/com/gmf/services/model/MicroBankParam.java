package com.gmf.services.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class MicroBankParam {

    private int id;
    private int groupMasterId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate groupStartDate;
    private int meetingFrequency;
    private String meetingSchedule;
    private int shareFaceValue;
    private float loanInterestRate;
    private float loanInterestBase;
    private int lnDisbAmountMaxLimitPercent;
    private int loanGaurantersCount;
    private LocalDate auditCreatedDate;
    private LocalDate auditUpdatedDate;

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

    public LocalDate getGroupStartDate() {
        return groupStartDate;
    }

    public void setGroupStartDate(LocalDate groupStartDate) {
        this.groupStartDate = groupStartDate;
    }

    public int getMeetingFrequency() {
        return meetingFrequency;
    }

    public void setMeetingFrequency(int meetingFrequency) {
        this.meetingFrequency = meetingFrequency;
    }

    public String getMeetingSchedule() {
        return meetingSchedule;
    }

    public void setMeetingSchedule(String meetingSchedule) {
        this.meetingSchedule = meetingSchedule;
    }

    public int getShareFaceValue() {
        return shareFaceValue;
    }

    public void setShareFaceValue(int shareFaceValue) {
        this.shareFaceValue = shareFaceValue;
    }

    public float getLoanInterestRate() {
        return loanInterestRate;
    }

    public void setLoanInterestRate(float loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }

    public float getLoanInterestBase() {
        return loanInterestBase;
    }

    public void setLoanInterestBase(float loanInterestBase) {
        this.loanInterestBase = loanInterestBase;
    }

    public int getLnDisbAmountMaxLimitPercent() {
        return lnDisbAmountMaxLimitPercent;
    }

    public void setLnDisbAmountMaxLimitPercent(int lnDisbAmountMaxLimitPercent) {
        this.lnDisbAmountMaxLimitPercent = lnDisbAmountMaxLimitPercent;
    }

    public int getLoanGaurantersCount() {
        return loanGaurantersCount;
    }

    public void setLoanGaurantersCount(int loanGaurantersCount) {
        this.loanGaurantersCount = loanGaurantersCount;
    }

    public LocalDate getAuditCreatedDate() {
        return auditCreatedDate;
    }

    public void setAuditCreatedDate(LocalDate auditCreatedDate) {
        this.auditCreatedDate = auditCreatedDate;
    }

    public LocalDate getAuditUpdatedDate() {
        return auditUpdatedDate;
    }

    public void setAuditUpdatedDate(LocalDate auditUpdatedDate) {
        this.auditUpdatedDate = auditUpdatedDate;
    }

    @Override
    public String toString() {
        return "MicroBankParam{" +
                "id=" + id +
                ", groupMasterId=" + groupMasterId +
                ", groupStartDate=" + groupStartDate +
                ", meetingFrequency=" + meetingFrequency +
                ", meetingSchedule='" + meetingSchedule + '\'' +
                ", shareFaceValue=" + shareFaceValue +
                ", loanInterestRate=" + loanInterestRate +
                ", loanInterestBase=" + loanInterestBase +
                ", lnDisbAmountMaxLimitPercent=" + lnDisbAmountMaxLimitPercent +
                ", loanGaurantersCount=" + loanGaurantersCount +
                ", auditCreatedDate=" + auditCreatedDate +
                ", auditUpdatedDate=" + auditUpdatedDate +
                '}';
    }
}


