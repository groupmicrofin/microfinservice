package com.gmf.services.model;

public class LoanMaster {

    private int id;
    private int groupMasterId;
    private int groupMemeberId;
    private int amtLoanDisb;
    private int amountLoanBalance;
    private int amountIntAccur;
    private int amountIntPaid;

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

    public int getAmtLoanDisb() {
        return amtLoanDisb;
    }

    public void setAmtLoanDisb(int amtLoanDisb) {
        this.amtLoanDisb = amtLoanDisb;
    }

    public int getAmountLoanBalance() {
        return amountLoanBalance;
    }

    public void setAmountLoanBalance(int amountLoanBalance) {
        this.amountLoanBalance = amountLoanBalance;
    }

    public int getAmountIntAccur() {
        return amountIntAccur;
    }

    public void setAmountIntAccur(int amountIntAccur) {
        this.amountIntAccur = amountIntAccur;
    }

    public int getAmountIntPaid() {
        return amountIntPaid;
    }

    public void setAmountIntPaid(int amountIntPaid) {
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
