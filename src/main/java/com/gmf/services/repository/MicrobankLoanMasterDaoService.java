package com.gmf.services.repository;

import com.gmf.services.model.LoanMaster;

import java.util.List;

public interface MicrobankLoanMasterDaoService {

    public List<LoanMaster> getActiveLoamAccounts(int groupMasterId);

    public void updateLoanMaster(double intAmount,int groupMasterId,int groupMemberId);

    public int getTotalLoanDisb(int groupMasterID);

    public void create(int groupMasterId, double amtLoanDisb, int groupMemberId);

    public LoanMaster findByMemberId(int groupMasterId , int groupMemberId);

    public boolean updateLoanMasterForNewLoan(int groupMasterid , int groupMemberId , double amountLoan);

    public double totalEarnedInterest(int groupMasterId);

    public LoanMaster fetchAmountLoanBalanceForInterestUpdate( int groupMasterId);

    public boolean updateAmountIntPaid(int groupMasterId ,int groupMemberId , double installmentAmount,double amountIntAccur );

}
