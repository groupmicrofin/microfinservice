package com.gmf.services.repository;

import com.gmf.services.model.MicroBnak;

public interface MicroBankDaoService {

    public MicroBnak addMicroBank(MicroBnak microBnak);

    public boolean addMicroBankBalance(int groupId);

    public int totalOtherExpense(int groupMasterId);

    public double totalMiscDr(int groupMasterId);

    public boolean miscelleniousDebitEventStart(int groupMasterId, double expenseAmount);

}
