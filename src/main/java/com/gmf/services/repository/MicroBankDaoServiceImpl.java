package com.gmf.services.repository;

import com.gmf.services.common.MicroBankConfig;
import com.gmf.services.model.MicroBnak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class MicroBankDaoServiceImpl implements MicroBankDaoService {

    private String createMicroBankQuery = "INSERT INTO group_masters (name,login_id,email,password,audit_created_dttm,audit_updated_dttm) VALUES (?,?,?,?,sysdate(),sysdate())";
    private String microMicroBankBalanceSql = "INSERT INTO group_balances (group_master_id,amt_share_fac_bal,amt_share_fac_bal_others,amt_misc_dr,audit_created_dttm) VALUES (?, 0, 0, 0, sysdate())";
    private String fetchTotalOtherExpenssSQL = "SELECT amt_misc_dr AS totalOtherExpense FROM group_balances WHERE group_master_id=?;";
    private String fetchTotalMiscDrAmountSQL = "SELECT SUM(amt_misc_dr) as amountMisc  FROM group_balances WHERE group_master_id=?;";
    private String updateMiscDebitDataSQL = "UPDATE group_balances SET amt_misc_dr= amt_misc_dr + ? WHERE group_master_id=?;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public MicroBnak addMicroBank(MicroBnak microBnak) {
        try {
            Class.forName(MicroBankConfig.DB_DRIVER);
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }

        try (Connection conn = DriverManager.getConnection(MicroBankConfig.DB_URL);
             PreparedStatement prestmt = conn.prepareStatement(createMicroBankQuery, Statement.RETURN_GENERATED_KEYS);) {
            prestmt.setString(1, microBnak.getName());
            prestmt.setString(2, microBnak.getLoginId());
            prestmt.setString(3, microBnak.getEmail());
            prestmt.setString(4, microBnak.getPassphrase());

            int result = prestmt.executeUpdate();
            ResultSet rs = prestmt.getGeneratedKeys();
            if (rs.next()) {
                int grpId = rs.getInt(1);
                microBnak.setId(grpId);
            }
        } catch (SQLException sqlException) {
            System.out.println("Exception while connecting db" + sqlException.getErrorCode() + "" + sqlException.getMessage());
        }
        return microBnak;
    }

    @Override
    public boolean addMicroBankBalance(int groupId) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        try (Connection conn = DriverManager.getConnection(MicroBankConfig.DB_URL); PreparedStatement prestmt = conn.prepareStatement(microMicroBankBalanceSql);) {
            prestmt.setInt(1, groupId);
            int result = prestmt.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("Exception while connecting DB:" + sqlException.getErrorCode() + ":" + sqlException.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public int totalOtherExpense(int groupMasterId) {
        System.out.println("getting othee expense");
        int totalOtherExpense = 0;
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method taken");
        try {
            conn = DriverManager.getConnection(MicroBankConfig.DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(fetchTotalOtherExpenssSQL);
            preparedStatement.setInt(1, groupMasterId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                totalOtherExpense = rs.getInt("totalOtherExpense");
            }
        } catch (SQLException sqlExcp) {
            System.out.println("sqlExcp:" + sqlExcp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println("error:" + e.getMessage());
            }
        }
        return totalOtherExpense;
    }

    @Override
    public double totalMiscDr(int groupMasterId) {
        System.out.println("getting total misc dr amount");
        double totalMiscDrAmount = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Driver class not found....");
        }
        System.out.println("first try and catch method completed");

        try (Connection conn = DriverManager.getConnection(MicroBankConfig.DB_URL)) {
            PreparedStatement preparedStatement = conn.prepareStatement(fetchTotalMiscDrAmountSQL);
            preparedStatement.setInt(1, groupMasterId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                totalMiscDrAmount = rs.getInt("amountMisc");
            }
        } catch (SQLException sqlExcp) {
            System.out.println("error in sql:" + sqlExcp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return totalMiscDrAmount;
    }

    @Override
    public boolean miscelleniousDebitEventStart(int groupMasterId, double expenseAmount) {
        boolean result = false;
        System.out.println("geting misc debit ");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("driver class not found");
        }
        System.out.println("first try and catch method complete");
        try (Connection conn = DriverManager.getConnection(MicroBankConfig.DB_URL)) {
            PreparedStatement preparedStatement = conn.prepareStatement(updateMiscDebitDataSQL);
            preparedStatement.setDouble(1, expenseAmount);
            preparedStatement.setInt(2, groupMasterId);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("row Affected:" + rowsAffected);

            result = true;

        } catch (SQLException sqlExcp) {
            System.out.println("SQL error:" + sqlExcp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return result;

    }
}
