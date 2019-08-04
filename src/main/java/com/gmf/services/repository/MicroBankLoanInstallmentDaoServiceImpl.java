package com.gmf.services.repository;

import com.gmf.services.common.MicroBankConfig;
import com.gmf.services.model.LoanMaster;
import com.gmf.services.model.MeetingCalender;
import com.gmf.services.model.MicroBankLoanInstallments;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class MicroBankLoanInstallmentDaoServiceImpl implements MicroBankLoanInstallmentDaoService {

    private String fetchTotalLoanInstallmentAmountPaidSQL = "SELECT IFNULL(SUM(amt_int_paid+amt_princ_paid),0) AS totalInstallmentPaidAmount FROM micro_finance.ln_installments WHERE group_master_id=?;";
    //private String fetchAmountIntPaidAndAmountIntAcurr="select amt_ln_balance,amt_int_paid,amt_int_accr from loan_masters where group_master_id=? and group_member_id=?;";
    private String updateLoanInstallment = "insert into ln_installments(group_master_id,group_member_id,calender_id,amt_int_paid,dat_installments,amt_princ_paid)values(?,?,?,?,sysdate(),?)";

    @Override
    public int getTotalLoanInstallmentAmountPais(int groupMasterId) {
        System.out.println("getting total loan installment amount paid ");
        int totalLoanInstallmentAmountPaid = 0;
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method complete");

        try {
            conn = DriverManager.getConnection(MicroBankConfig.DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(fetchTotalLoanInstallmentAmountPaidSQL);
            preparedStatement.setInt(1, groupMasterId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                totalLoanInstallmentAmountPaid = rs.getInt("totalInstallmentPaidAmount");
            }
        } catch (SQLException sqlExcp) {
            System.out.println("SQL error:" + sqlExcp.getMessage());
        } catch (Exception Excp) {
            System.out.println("Error:" + Excp.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        return totalLoanInstallmentAmountPaid;

    }

    //private String updateLoanInstallment="insert into ln_installments(group_master_id,group_member_id,calender_id,amt_int_paid,dat_installments,amt_princ_paid)values(?,?,?,?,sysdate(),?)";
    @Override
    public MicroBankLoanInstallments insertLoanInstallments(int groupMasterId, int groupMemberId, int calenderId, double amountIntPaid, double principleAmountPaid) {
        System.out.println("iserting loan installments");
        MicroBankLoanInstallments microBankLoanInstallments = new MicroBankLoanInstallments();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("driver class not found...");
        }
        System.out.println("first try and catch method taken");

        try (Connection conn = DriverManager.getConnection(MicroBankConfig.DB_URL)) {
            PreparedStatement preparedStatement = conn.prepareStatement(updateLoanInstallment, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, groupMasterId);
            preparedStatement.setInt(2, groupMemberId);
            preparedStatement.setInt(3, calenderId);
            preparedStatement.setDouble(4, amountIntPaid);
            preparedStatement.setDouble(5, principleAmountPaid);

            int result = preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                microBankLoanInstallments.setId(id);
            }
        } catch (SQLException sqlExcp) {
            System.out.println("sql error:" + sqlExcp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return microBankLoanInstallments;
    }
}
