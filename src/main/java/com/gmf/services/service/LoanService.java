package com.gmf.services.service;

import com.gmf.services.model.LoanMaster;
import com.gmf.services.repository.MicrobankLoanMasterDaoService;

import java.util.List;

public class LoanService {

    MicrobankLoanMasterDaoService loanMasterDaoService=new MicrobankLoanMasterDaoService();

    public void performLoanInterestCharging(int groupMasterId) {
        System.out.println("loan interest calculated");
        //1. Get active LoanAccounts, do below for all accounts
        List<LoanMaster> loanMasterList = loanMasterDaoService.getActiveLoamAccounts(groupMasterId);
        //2. loan accounts calculate interest
        for(LoanMaster loanMaster:loanMasterList){
            //TODO
            loanMasterDaoService.updateLoanMaster(loanMaster);
        }
    }
}
