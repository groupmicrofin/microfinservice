package com.gmf.services.repository;

import com.gmf.services.model.MicroBankMember;
import org.springframework.stereotype.Component;

import java.sql.*;

import static com.gmf.services.common.MicroBankConfig.DB_URL;

@Component
public class MicroBankMemberDaoServiceImpl implements MicroBankMemberDaoService {

    private String createMicroBankMemberQuery = "insert into group_members(group_master_id,name,mail,password,mobile,birth_date , kyc_doc_type,kyc_id,member_status,share_balance,audit_created_dttm,audit_updated_dttm)VALUES(?,?,?,?,?,?,?,?,'A',?,sysdate(),sysdate())";
    private String fetchTotalActiveMemberSQL = "select count(1) active_members from group_members where group_master_id=? and member_status='A';";
    private String fetchShareBalance = "select share_balance FROM group_members where group_master_id=1;";
    private String updatingShareCollectionForAll = "update group_members set share_balance=share_balance+?,calender_id=? where group_master_id=? ;";
    private String fetchTotalShareBalanceSQL = "select sum(share_balance) AS totalShareBalance from group_members where member_status=? and group_master_id=?;";
    private String updatingShareCollectionForIndividual = "update group_members set share_balance=share_balance+?,calender_id=? where group_master_id=? and id=?;";

    @Override
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
            prest.setInt(9, microBankMember.getShareBalance());

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

    @Override
    public int getTotalActiveMember(int groupMasterId) {
        System.out.println("####MBMDaoService:getTotalActiveMembers:Group:" + groupMasterId);
        int totalActiveLoanCount = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found...." + cnf.getMessage());
        }
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement prestmt = conn.prepareStatement(fetchTotalActiveMemberSQL);
            prestmt.setInt(1, groupMasterId);

            ResultSet rs = prestmt.executeQuery();

            if (rs.next()) {
                totalActiveLoanCount = rs.getInt("active_members");
            }

        } catch (Exception excp) {
            System.out.println("Error " + excp.getMessage());
        }
        System.out.println("####MBMDaoService:getTotalActiveMembers:TotalActive Members " + totalActiveLoanCount + " for group " + groupMasterId);
        return totalActiveLoanCount;
    }

    //get share balance
    @Override
    public int getShareBalance(MicroBankMember microBankMember) {
        System.out.println("getting share balance");
        int ShareBalance = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found...." + cnf.getMessage());
        }
        System.out.println("first try and catch method ");

        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(fetchShareBalance);
            preparedStatement.setInt(1, microBankMember.getGroupMasterID());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                ShareBalance = rs.getInt("share_balance");
            }
        } catch (Exception excp) {
            System.out.println("error:" + excp.getMessage());
        }
        return ShareBalance;
    }

    @Override
    public void addMeetingSharebalanceForAllMember(int shareFaceValue, int currentCalenderID, int groupMasteId) {
        System.out.println("updating share balance in meeting for all group member");
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found...." + cnf.getMessage());
        }
        System.out.println("first try and catch method completed");

        try {
            conn = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(updatingShareCollectionForAll);
            preparedStatement.setInt(1, shareFaceValue);
            preparedStatement.setInt(2, currentCalenderID);
            preparedStatement.setInt(3, groupMasteId);

            int returnValue = preparedStatement.executeUpdate();
            System.out.println("return value:" + returnValue);
        } catch (SQLException sqlExcp) {
            System.out.println("SQL  error:" + sqlExcp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("error:" + e.getMessage());
            }

        }
    }

    //get share balance
    @Override
    public int getTotalShareBalance(int groupMasterId, String memberStatus) {
        System.out.println("getting share balance");
        Connection conn = null;
        int totalShareBalance = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found...." + cnf.getMessage());
        }
        System.out.println("first try and catch method ");

        try {
            conn = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(fetchTotalShareBalanceSQL);
            preparedStatement.setString(1, memberStatus);
            preparedStatement.setInt(2, groupMasterId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                totalShareBalance = rs.getInt("totalShareBalance");
            }
        } catch (SQLException excp) {
            System.out.println("SQL error:" + excp.getMessage());
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
        return totalShareBalance;
    }

    //for share collection for individual member


    @Override
    public void addMeetingSharBalanceForIndividual(int shareFaceValue, int currentCalenderID, int groupMasteId, int id) {
        System.out.println("updating share balance in meeting for all group member");
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found...." + cnf.getMessage());
        }
        System.out.println("first try and catch method completed");

        try {
            conn = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(updatingShareCollectionForIndividual);
            preparedStatement.setInt(1, shareFaceValue);
            preparedStatement.setInt(2, currentCalenderID);
            preparedStatement.setInt(3, groupMasteId);
            preparedStatement.setInt(4, id);

            int returnValue = preparedStatement.executeUpdate();
            System.out.println("return value:" + returnValue);
        } catch (SQLException sqlExcp) {
            System.out.println("SQL  error:" + sqlExcp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("error:" + e.getMessage());
            }
        }
    }
}
