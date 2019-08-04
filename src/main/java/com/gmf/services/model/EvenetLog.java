package com.gmf.services.model;

import java.time.LocalDate;

public class EvenetLog {

    private int id;
    private int groupMasterId;
    private int groupMemberId;
    private String txnCode;
    private double txnAmount;
    private LocalDate txmDate;
    private String remark;
    private int calenderId;

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

    public int getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(int groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public String getTxnCode() {
        return txnCode;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public double getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public LocalDate getTxmDate() {
        return txmDate;
    }

    public void setTxmDate(LocalDate txmDate) {
        this.txmDate = txmDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCalenderId() {
        return calenderId;
    }

    public void setCalenderId(int calenderId) {
        this.calenderId = calenderId;
    }

    @Override
    public String toString() {
        return "EvenetLog{" +
                "id=" + id +
                ", groupMasterId=" + groupMasterId +
                ", groupMemberId=" + groupMemberId +
                ", txnCode='" + txnCode + '\'' +
                ", txnAmount=" + txnAmount +
                ", txmDate=" + txmDate +
                ", remark='" + remark + '\'' +
                ", calenderId=" + calenderId +
                '}';
    }
}
