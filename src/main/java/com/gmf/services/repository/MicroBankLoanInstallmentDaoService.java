package com.gmf.services.repository;

import com.gmf.services.model.MicroBankLoanInstallments;

public interface MicroBankLoanInstallmentDaoService {

    public int getTotalLoanInstallmentAmountPais(int groupMasterId);

    public MicroBankLoanInstallments insertLoanInstallments(int groupMasterId, int groupMemberId , int calenderId, double amountIntPaid, double principleAmountPaid);




}
