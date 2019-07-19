package com.gmf.services.repository;

import com.gmf.services.common.MicroBankConfig;
import com.gmf.services.model.MicroBnak;

import java.sql.*;

public class MicroBankDaoService {

    private String createMicroBankQuery = "INSERT INTO group_masters (name,login_id,email,password,audit_created_dttm,audit_updated_dttm) VALUES (?,?,?,?,sysdate(),sysdate())";
    private String microMicroBankBalanceSql = "INSERT INTO group_balances (group_master_id,amt_share_fac_bal,amt_share_fac_bal_others,amt_misc_dr,audit_created_dttm) VALUES (?, 0, 0, 0, sysdate())";

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
}
