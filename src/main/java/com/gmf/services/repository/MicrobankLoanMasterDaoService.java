package com.gmf.services.repository;

import com.gmf.services.model.LoanMaster;

import java.util.ArrayList;
import java.util.List;

public class MicrobankLoanMasterDaoService {


    public List<LoanMaster> getActiveLoamAccounts(int groupMasterId) {
        System.out.println("active loan accounts taken");

        List<LoanMaster> loanAccountList = new ArrayList<>();
        //TODO
        //Get Results from Query , create LoanMaster object.. set values.. add to the list

        return loanAccountList;
    }

    public void updateLoanMaster(LoanMaster loanMaster){
        System.out.println("update interest in loan master");
        //TODO
    }
}
