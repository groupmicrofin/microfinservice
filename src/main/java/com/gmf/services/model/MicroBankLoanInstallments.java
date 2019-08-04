package com.gmf.services.model;

import java.time.LocalDate;

public class MicroBankLoanInstallments {

    private int id;
    private int groupMasterId;
    private int groupMemeberId;
    private int calenderId;
    private double amountIntPaid;
    private double amountPrincPaid;
    private LocalDate dateInstallments;

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

    public int getGroupMemeberId() {
        return groupMemeberId;
    }

    public void setGroupMemeberId(int groupMemeberId) {
        this.groupMemeberId = groupMemeberId;
    }

    public int getCalenderId() {
        return calenderId;
    }

    public void setCalenderId(int calenderId) {
        this.calenderId = calenderId;
    }

    public double getAmountIntPaid() {
        return amountIntPaid;
    }

    public void setAmountIntPaid(double amountIntPaid) {
        this.amountIntPaid = amountIntPaid;
    }

    public double getAmountPrincPaid() {
        return amountPrincPaid;
    }

    public void setAmountPrincPaid(double amountPrincPaid) {
        this.amountPrincPaid = amountPrincPaid;
    }

    public LocalDate getDateInstallments() {
        return dateInstallments;
    }

    public void setDateInstallments(LocalDate dateInstallments) {
        this.dateInstallments = dateInstallments;
    }

    @Override
    public String toString() {
        return "MicroBankLoanInstallments{" +
                "id=" + id +
                ", groupMasterId=" + groupMasterId +
                ", groupMemeberId=" + groupMemeberId +
                ", calenderId=" + calenderId +
                ", amountIntPaid=" + amountIntPaid +
                ", amountPrincPaid=" + amountPrincPaid +
                ", dateInstallments=" + dateInstallments +
                '}';
    }
}
