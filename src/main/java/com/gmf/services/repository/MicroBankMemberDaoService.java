package com.gmf.services.repository;

import com.gmf.services.model.MicroBankMember;

import java.sql.*;

public class MicroBankMemberDaoService {

    private String DB_URL = "jdbc:mysql://localhost:3306/micro_finance?user=root&password=password";
    private String createMicroBankMemberQuery = "insert into group_members(group_master_id,name,mail,password,mobile,birth_date , kyc_doc_type,kyc_id,member_status,share_balance,audit_created_dttm,audit_updated_dttm)VALUES(?,?,?,?,?,?,?,?,'A',?,sysdate(),sysdate())";

    public void createMicroBankMembers(MicroBankMember microBankMember) {
        System.out.println("dao method started");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method complete");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("input taken");

            PreparedStatement prest = conn.prepareStatement(createMicroBankMemberQuery, Statement.RETURN_GENERATED_KEYS);

            prest.setInt(1, microBankMember.getGroupMasterID());
            prest.setString(2, microBankMember.getName());
            prest.setString(3, microBankMember.getMail());
            prest.setString(4, microBankMember.getPassword());
            prest.setInt(5, microBankMember.getMobile());
            prest.setDate(6, Date.valueOf(microBankMember.getBirthdate()));
            prest.setString(7, microBankMember.getKycDcType());
            prest.setString(8, microBankMember.getKycID());
            prest.setInt(9,microBankMember.getShareBalance());

            System.out.println("input taken 1 ");
            int result = prest.executeUpdate();

            ResultSet rs = prest.getGeneratedKeys();
            if (rs.next()) {
                int grpMemberID = rs.getInt(1);
                microBankMember.setId(grpMemberID);
            }
        } catch (SQLException sqlException) {
            System.out.println("error in connection:" + sqlException.getMessage());
        } catch (Exception exc) {
            System.out.println("Exception:" + exc.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlExcpetion) {
                    System.out.println("error in closing:" + sqlExcpetion.getMessage());
                }
            }
        }
        System.out.println("Second try and catch method complete");
    }

    public int getTotalActiveMember(int groupMasterId){
        System.out.println("current total active member");
        int totalActiveLoanCount = 0;
        //TODO
        return totalActiveLoanCount;
    }
}