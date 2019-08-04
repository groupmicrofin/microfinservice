package com.gmf.services.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class MicroBankBalances {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate lastMeetingDate;
    private LocalDate nextMeetingDate;
    private int id;
    private int groupMasterId;
    private double amountShareFaceBalance;
    private double amountShareFaceBalanceOthers;
    private double amountMiscDr;
    private String lastActivityStatus;

    public LocalDate getLastMeetingDate() {
        return lastMeetingDate;
    }

    public void setLastMeetingDate(LocalDate lastMeetingDate) {
        this.lastMeetingDate = lastMeetingDate;
    }

    public LocalDate getNextMeetingDate() {
        return nextMeetingDate;
    }

    public void setNextMeetingDate(LocalDate nextMeetingDate) {
        this.nextMeetingDate = nextMeetingDate;
    }

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

    public double getAmountShareFaceBalance() {
        return amountShareFaceBalance;
    }

    public void setAmountShareFaceBalance(double amountShareFaceBalance) {
        this.amountShareFaceBalance = amountShareFaceBalance;
    }

    public double getAmountShareFaceBalanceOthers() {
        return amountShareFaceBalanceOthers;
    }

    public void setAmountShareFaceBalanceOthers(double amountShareFaceBalanceOthers) {
        this.amountShareFaceBalanceOthers = amountShareFaceBalanceOthers;
    }

    public double getAmountMiscDr() {
        return amountMiscDr;
    }

    public void setAmountMiscDr(double amountMiscDr) {
        this.amountMiscDr = amountMiscDr;
    }

    public String getLastActivityStatus() {
        return lastActivityStatus;
    }

    public void setLastActivityStatus(String lastActivityStatus) {
        this.lastActivityStatus = lastActivityStatus;
    }

    @Override
    public String toString() {
        return "MicroBankBalances{" +
                "lastMeetingDate=" + lastMeetingDate +
                ", nextMeetingDate=" + nextMeetingDate +
                ", id=" + id +
                ", groupMasterId=" + groupMasterId +
                ", amountShareFaceBalance=" + amountShareFaceBalance +
                ", amountShareFaceBalanceOthers=" + amountShareFaceBalanceOthers +
                ", amountMiscDr=" + amountMiscDr +
                ", lastActivityStatus='" + lastActivityStatus + '\'' +
                '}';
    }
}
