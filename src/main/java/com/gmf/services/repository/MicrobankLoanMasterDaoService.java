package com.gmf.services.repository;

import com.gmf.services.model.LoanMaster;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MicrobankLoanMasterDaoService {

    private String DB_URL = "jdbc:mysql://localhost:3306/micro_finance?user=root&password=password";
    private String createloanMasterQueary = "select * from micro_finance.loan_masters where group_master_id=1 and amt_ln_balance > 0;";
    private String createInterestUpdateQuery="update micro_finance.loan_masters set amt_ln_balance=amt_ln_balance+1, amt_int_accr = amt_int_accr + 1where group_master_id=1 and id=1;";

    public List<LoanMaster> getActiveLoamAccounts(int groupMasterId) {
        System.out.println("active loan accounts taken");

        List<LoanMaster> loanAccountList = new ArrayList<>();
        //TODO
        //Get Results from Query , create LoanMaster object.. set values.. add to the list
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method complete");

        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement prestmt = conn.createStatement();
            ResultSet rs = prestmt.executeQuery(createloanMasterQueary);

            while (rs.next()) {
                LoanMaster loanMaster = new LoanMaster();
                //loanMaster.setAmountLoanBalance();
                loanAccountList.add(loanMaster);
            }
            conn.close();
        } catch (SQLException sqlExcp) {
            System.out.println("error:" + sqlExcp.getMessage());

        }
        return loanAccountList;
    }





    public void updateLoanMaster(LoanMaster loanMaster){
        System.out.println("update interest in loan master");
        //TODO

        Connection conn=null;
        Statement statement=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method complete");

        try {
            conn=DriverManager.getConnection(DB_URL);
            System.out.println("input taken");
            statement=conn.createStatement();
            ResultSet rs=statement.executeQuery(createInterestUpdateQuery);
            System.out.println("data updated successfully");
        }catch (SQLException sqlExcp){
            System.out.println("error:"+sqlExcp.getMessage());
        }finally {
            try {conn.close();
            }catch (Exception e){
                System.out.println("exception error message:"+e.getMessage());
            }
        }

    }
}
