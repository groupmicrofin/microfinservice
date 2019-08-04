package com.gmf.services.repository;

import com.gmf.services.common.MicroBankConfig;
import com.gmf.services.model.LoanMaster;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MicrobankLoanMasterDaoServiceImpl implements MicrobankLoanMasterDaoService {

    //SQl Query
    private String fetchActiveLoanMastersSQL = "select id,group_member_id,amt_ln_disb,amt_ln_balance,amt_int_accr,amt_int_paid,group_master_id from loan_masters where group_master_id=? and amt_ln_balance>0;";
    private String createInterestUpdateQuery = "UPDATE loan_masters SET amt_ln_balance=amt_ln_balance+ROUND(?,2) , amt_int_accr=amt_int_accr+ROUND(?,2) WHERE group_master_id=? AND group_member_id=? ;";
    private String fetchingTotalLoanDisb = "select ifnull(sum(amt_ln_disb),0) AS totalLoanDisbursed from loan_masters where group_master_id=?;";
    private String createLoanDisburesement = "insert into loan_masters(group_member_id,amt_ln_disb,amt_ln_balance,amt_int_accr,amt_int_paid,group_master_id)values(?,?,?,0,0,?);";
    private String fetchLoanMasterbyMemberId="select id,group_member_id,amt_ln_disb,amt_ln_balance,amt_int_accr,amt_int_paid,group_master_id from loan_masters where group_master_id=? and group_member_id=?;";
    private String updateLoanMaster="UPDATE loan_masters SET amt_ln_balance=amt_ln_balance+? , amt_ln_disb=amt_ln_disb+? WHERE group_master_id=? AND group_member_id=? ;";
    private String fetchTotalInterestEarned="SELECT SUM(amt_int_paid) AS totalInterestEarned FROM loan_masters WHERE group_master_id=?;";
    private String fetchloanAmount="SELECT id,group_member_id,amt_ln_disb,amt_ln_balance,amt_int_accr,amt_int_paid,group_master_id FROM loan_masters WHERE group_master_id=? AND amt_ln_balance > 0;";
    private String updateAmountIntPaid="update loan_masters set amt_int_paid=amt_int_paid+?,amt_int_accr=amt_int_accr-?,amt_ln_balance=amt_ln_balance-? where group_master_id=? and group_member_id=?;";


    @Override
    public List<LoanMaster> getActiveLoamAccounts(int groupMasterId ) {
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
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MicroBankConfig.DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(fetchActiveLoanMastersSQL);
            preparedStatement.setInt(1, groupMasterId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                LoanMaster loanMaster = new LoanMaster();

                int id = rs.getInt("id");
                loanMaster.setId(id);

                int groupMemberId = rs.getInt("group_member_id");
                loanMaster.setGroupMemeberId(groupMemberId);

                double amountLoanDisb = rs.getDouble("amt_ln_disb");
                loanMaster.setAmtLoanDisb(amountLoanDisb);

                double amountLoanBalance = rs.getDouble("amt_ln_balance");
                loanMaster.setAmountLoanBalance(amountLoanBalance);

                double amountIntAccur = rs.getDouble("amt_int_accr");
                loanMaster.setAmountIntAccur(amountIntAccur);

                double amountIntPaid = rs.getDouble("amt_int_paid");
                loanMaster.setAmountIntPaid(amountIntPaid);

                int groupMasterId1 = rs.getByte("group_master_id");
                loanMaster.setGroupMasterId(groupMasterId1);

                loanAccountList.add(loanMaster);
            }

        } catch (SQLException sqlExcp) {
            System.out.println("error:" + sqlExcp.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                System.out.println("Error in closing connection.." + sqlException.getMessage());
            }
        }
        return loanAccountList;
    }


    @Override
    public void updateLoanMaster(double intAmount,int groupMasterId,int groupMemberId) {
        System.out.println("update interest in loan master");
        //LoanMaster loanMaster1=new LoanMaster();
        //TODO

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method complete");

        try {
            conn = DriverManager.getConnection(MicroBankConfig.DB_URL);
            System.out.println("input taken");
            PreparedStatement preparedStatement=conn.prepareStatement(createInterestUpdateQuery);
            preparedStatement.setDouble(1,intAmount);
            preparedStatement.setDouble(2,intAmount);
            preparedStatement.setInt(3,groupMasterId);
            preparedStatement.setInt(4,groupMemberId);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("data updated successfully-rows affected:"+rowsAffected);
        } catch (SQLException sqlExcp) {
            System.out.println("error:" + sqlExcp.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("exception error message:" + e.getMessage());
            }
        }
    }

    //private String fetchingTotalLoanDisb="select ifnull(sum(amt_ln_disb),0) AS totalLoanDisbursed from loan_masters where group_master_id=?;";
    @Override
    public int getTotalLoanDisb(int groupMasterID) {
        System.out.println("getting total loan disb");
        int totalLoanDisb = 0;
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method complete");

        try {
            conn = DriverManager.getConnection(MicroBankConfig.DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(fetchingTotalLoanDisb);
            preparedStatement.setInt(1, groupMasterID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                totalLoanDisb = rs.getInt("totalLoanDisbursed");
            }
        } catch (SQLException sqlExcp) {
            System.out.println("sql error:" + sqlExcp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        } finally {
            try {
                if ((conn != null))
                    conn.close();
            } catch (Exception e) {
                System.out.println("error:" + e.getMessage());
            }

        }
        return totalLoanDisb;
    }

    //"insert into loan_masters(group_member_id,amt_ln_disb,amt_ln_balance,amt_int_accr,amt_int_paid,group_master_id)values(?,?,0,0,0,?);";
    @Override
    public void create(int groupMasterId, double amtLoanDisb, int groupMemberId) {
        System.out.println("loan disburesement taken groupMasterId"+groupMasterId+":amtLoanDisb:"+amtLoanDisb+":groupMemberId:"+groupMemberId);
        LoanMaster loanMaster = new LoanMaster();

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver class not found..");
        }
        System.out.println("first try and catch method taken");
        try {
            conn = DriverManager.getConnection(MicroBankConfig.DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(createLoanDisburesement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, groupMemberId);
            preparedStatement.setDouble(2, amtLoanDisb);
            preparedStatement.setDouble(3,amtLoanDisb);
            preparedStatement.setInt(4, groupMasterId);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("effected rows are:" + affectedRows);
            System.out.println("data taken");

            if (affectedRows == 0) {
                throw new SQLException(" inserting loan disb failed , no rows affected ");
            }
            try (ResultSet generetedKeys = preparedStatement.getGeneratedKeys()) {
                if (generetedKeys.next()) {
                    int id = generetedKeys.getInt(1);
                    System.out.println("id is:" + id);
                } else {
                    throw new Exception("inserting loan disb failed , no id obtained");
                }
            }
        } catch (SQLException sqlExcp) {
            System.out.println("SQL error:" + sqlExcp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println("Error while closing:" + e.getMessage());
            }
        }
    }

    //private String fetchLoanMasterbyMemberId="select id,group_member_id,amt_ln_disb,amt_ln_balance,amt_int_accr,amt_int_paid,group_master_id from loan_masters where group_master_id=? and group_member_id=?;";
    @Override
    public LoanMaster findByMemberId(int groupMasterId , int groupMemberId){
        LoanMaster loanMaster=new LoanMaster();
        System.out.println("find loan master by member id");
        Connection conn=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }
        System.out.println("first try and catch method taken");

        try {
            conn=DriverManager.getConnection(MicroBankConfig.DB_URL);
            PreparedStatement preparedStatement=conn.prepareStatement(fetchLoanMasterbyMemberId);
            preparedStatement.setInt(1,groupMasterId);
            preparedStatement.setInt(2,groupMemberId);
             ResultSet rs=preparedStatement.executeQuery();

             if (rs.next()){

                 int id=rs.getInt("id");
                 loanMaster.setId(id);

                 //groupMemberId=rs.getInt("group_member_id");
                 loanMaster.setGroupMemeberId(groupMemberId);

                 double amountLoanDisb1=rs.getDouble("amt_ln_disb");
                 loanMaster.setAmtLoanDisb(amountLoanDisb1);

                 double amountLoanBalance=rs.getDouble("amt_ln_balance");
                 loanMaster.setAmountLoanBalance(amountLoanBalance);

                 double amountIntAccu=rs.getDouble("amt_int_accr");
                 loanMaster.setAmountIntAccur(amountIntAccu);

                 double amountIntPaid=rs.getDouble("amt_int_accr");
                 loanMaster.setAmountIntPaid(amountIntPaid);

                 //groupMasterId=rs.getInt("group_master_id");
                 loanMaster.setGroupMasterId(groupMasterId);

                 System.out.println("data taken in loan master object");

             }
        }catch (SQLException sqlExcp){
            System.out.println("sql error:"+sqlExcp.getMessage());
        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }finally {
            try {
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                System.out.println("error in closing:"+e.getMessage());
            }
        }

        return  loanMaster;
    }


    @Override
    public boolean updateLoanMasterForNewLoan(int groupMasterid , int groupMemberId , double amtLoanDisb){
        boolean result=false;
        System.out.println("updating loan master for new loan");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            System.out.println("Driver class not find");
        }
        System.out.println("first try and catch method taken");
        try(Connection conn=DriverManager.getConnection(MicroBankConfig.DB_URL)) {
            PreparedStatement preparedStatement=conn.prepareStatement(updateLoanMaster);
            preparedStatement.setDouble(1,amtLoanDisb);
            preparedStatement.setDouble(2,amtLoanDisb);
            preparedStatement.setInt(3,groupMasterid);
            preparedStatement.setInt(4,groupMemberId);

            int rowsAffected=preparedStatement.executeUpdate();
            System.out.println("rows affected :"+rowsAffected);

            result=true;

        }catch (SQLException sqlExcp){
            System.out.println("Sql error:"+sqlExcp.getMessage());
        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return result;
    }

    @Override
    public double totalEarnedInterest(int groupMasterId){
        System.out.println("dao service started");
        double totalInterestEarned=0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cnf){
            System.out.println("driver class not found...");
        }
        System.out.println("first try and catch method taken");

        try(Connection conn=DriverManager.getConnection(MicroBankConfig.DB_URL)) {
            PreparedStatement preparedStatement=conn.prepareStatement(fetchTotalInterestEarned);
            preparedStatement.setInt(1,groupMasterId);
            ResultSet rs=preparedStatement.executeQuery();
            if (rs.next()){
                totalInterestEarned =rs.getInt("totalInterestEarned");
            }

        }catch (SQLException sqlExcp){
            System.out.println("sql error:"+sqlExcp.getMessage());
        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return totalInterestEarned;
    }

    @Override
    public LoanMaster fetchAmountLoanBalanceForInterestUpdate( int groupMasterId){
        LoanMaster loanMaster=new LoanMaster();
        System.out.println("getting loan amount for interest update");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cnf){
            System.out.println("driver class not found...");
        }
        System.out.println("first try and catch method taken");

        try(Connection conn=DriverManager.getConnection(MicroBankConfig.DB_URL)) {
            PreparedStatement preparedStatement=conn.prepareStatement(fetchloanAmount);
            preparedStatement.setInt(1,groupMasterId);
            ResultSet rs=preparedStatement.executeQuery();
            if (rs.next()){
                int id=rs.getInt("id");
                loanMaster.setId(id);

                int groupMemberId=rs.getInt("group_member_id");
                loanMaster.setGroupMemeberId(groupMemberId);

                int amountLoanDisb=rs.getInt("amt_ln_disb");
                loanMaster.setAmtLoanDisb(amountLoanDisb);

                double amountLoanBalance=rs.getDouble("amt_ln_balance");
                loanMaster.setAmountLoanBalance(amountLoanBalance);

                double amountIntAccr=rs.getDouble("amt_int_accr");
                loanMaster.setAmountIntAccur(amountIntAccr);

                double amountIntPaid=rs.getDouble("amt_int_paid");
                loanMaster.setAmountIntPaid(amountIntPaid);

                int groupMasterId1=rs.getInt("group_master_id");
                loanMaster.setGroupMasterId(groupMasterId1);
            }

        }catch (SQLException sqlExcp){
            System.out.println("sql error:"+sqlExcp.getMessage());
        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return loanMaster;
    }

    @Override
    public boolean updateAmountIntPaid( int groupMasterId ,int groupMemberId ,double installmentAmount,double interestPaidAmount){
        System.out.println("update DAO service");
        boolean result=false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cnf){
            System.out.println("driver class not found...");
        }
        System.out.println("first try and catch method taken");

        try {
            Connection conn=DriverManager.getConnection(MicroBankConfig.DB_URL);
            PreparedStatement preparedStatement=conn.prepareStatement(updateAmountIntPaid);
            preparedStatement.setDouble(1,interestPaidAmount);
            preparedStatement.setDouble(2,interestPaidAmount);
            preparedStatement.setDouble(3,installmentAmount);
            preparedStatement.setInt(4,groupMasterId);
            preparedStatement.setInt(5,groupMemberId);

            int rowsAffected=preparedStatement.executeUpdate();
            System.out.println("rows affected :"+rowsAffected);
            result= true;
        }catch (SQLException sqlExcp){
            System.out.println("sql error:"+sqlExcp.getMessage());
        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return result;
    }

}
