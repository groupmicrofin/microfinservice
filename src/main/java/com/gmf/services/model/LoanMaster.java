package com.gmf.services.model;

public class LoanMaster {

    private int id;
    private int groupMasterId;
    private int groupMemeberId;
    private double amtLoanDisb;
    private double amountLoanBalance;
    private double amountIntAccur;
    private double amountIntPaid;

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

    public double getAmtLoanDisb() {
        return amtLoanDisb;
    }

    public void setAmtLoanDisb(double amtLoanDisb) {
        this.amtLoanDisb = amtLoanDisb;
    }

    public double getAmountLoanBalance() {
        return amountLoanBalance;
    }

    public void setAmountLoanBalance(double amountLoanBalance) {
        this.amountLoanBalance = amountLoanBalance;
    }

    public double getAmountIntAccur() {
        return amountIntAccur;
    }

    public void setAmountIntAccur(double amountIntAccur) {
        this.amountIntAccur = amountIntAccur;
    }

    public double getAmountIntPaid() {
        return amountIntPaid;
    }

    public void setAmountIntPaid(double amountIntPaid) {
        this.amountIntPaid = amountIntPaid;
    }

    @Override
    public String toString() {
        return "LoanMaster{" +
                "id=" + id +
                ", groupMasterId=" + groupMasterId +
                ", groupMemeberId=" + groupMemeberId +
                ", amtLoanDisb=" + amtLoanDisb +
                ", amountLoanBalance=" + amountLoanBalance +
                ", amountIntAccur=" + amountIntAccur +
                ", amountIntPaid=" + amountIntPaid +
                '}';
    }
}
