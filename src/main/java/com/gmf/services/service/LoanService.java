package com.gmf.services.service;

public interface LoanService {

    public void performLoanInterestCharging(int groupMasterId);

    public void performLoanDisbursement(int groupMasterId, double amtLoanDisb, int groupMemberId, String gaurnterData);

    public void perfomLoanInstallment(int groupMasterId,int groupMemberId,double installmentAmount);


}
