package com.gmf.services.repository;

import com.gmf.services.common.MicroBankConfig;
import com.gmf.services.exception.MicroBankAppException;
import com.gmf.services.model.MeetingCalender;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;

import static com.gmf.services.common.MicroBankConfig.DB_URL;

@Component
public class MicroBankCalenderDaoServiceImpl implements MicroBankCalenderDaoService {

    private String fetchNextMeetingCycleNo = "select IFNULL(max(cycle_no),0)+1 cycle_no from meeting_calender where group_master_id=?;";
    private String createMeetingCalenderQuery = "insert into meeting_calender (group_master_id,cycle_no,share_amount,meeting_start_date,meeting_end_date,total_active_members,status)VALUES(?,?,?,sysdate(),sysdate(),?,?);";
    private String fetchCalenderSQL = "SELECT id,group_master_id,cycle_no,share_amount,meeting_start_date,meeting_end_date,total_active_members,status FROM meeting_calender WHERE group_master_id=? AND STATUS=?;";
    private String updateMeetingCalenderAtEndSQL = "update meeting_calender set status='C',meeting_end_date=sysdate() where group_master_id=? and status=?;";
    private String fetchCalenderId = "select id AS calender_id from meeting_calender where group_master_id=? and status=?;";


    @Override
    public int fetchNextMeetingCycleNo(int groupMasterId) {
        System.out.println("max current cycle number taken");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method complete");

        Connection conn = null;
        PreparedStatement prestmt = null;
        int maxCycleNo = 0;


        try {
            conn = DriverManager.getConnection(DB_URL);
            prestmt = conn.prepareStatement(fetchNextMeetingCycleNo);
            prestmt.setInt(1, groupMasterId);
            ResultSet rs = prestmt.executeQuery();
            if (rs.next()) {
                maxCycleNo = rs.getInt("cycle_no");
                System.out.println("maxCycleNo=" + maxCycleNo);
            }
            rs.close();
            return maxCycleNo;
        } catch (SQLException sqlException) {
            System.out.println("error faced:" + sqlException.getMessage());
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
        return maxCycleNo;
    }

    @Override
    public void createMeetingCalender(MeetingCalender meetingCalender) {
        System.out.println("meeting calender created");

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

            PreparedStatement prest = conn.prepareStatement(createMeetingCalenderQuery, Statement.RETURN_GENERATED_KEYS);

            prest.setInt(1, meetingCalender.getGroupMasterId());
            prest.setInt(2, meetingCalender.getCycleNo());
            prest.setInt(3, meetingCalender.getShareAmount());
            prest.setInt(4, meetingCalender.getTotalActiveMembers());
            prest.setString(5, meetingCalender.getStatus());

            System.out.println("input taken 1");
            int result = prest.executeUpdate();

            ResultSet rs = prest.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                meetingCalender.setId(1);
            }
        } catch (SQLException sqlException) {
            MicroBankAppException microBankAppException = new MicroBankAppException("sql exception in cretaing meeting calender:" + sqlException.getMessage());
            System.out.println("error in connection:" + sqlException.getMessage());
            throw microBankAppException;

        } catch (Exception exc) {
            MicroBankAppException microBankAppException = new MicroBankAppException("create meeting calender failed");
            System.out.println("Exception:" + exc.getMessage());
            throw microBankAppException;

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
    public MeetingCalender findByGroupMasterIdAndStatus(int groupMasterId, String status) {
        System.out.println("get calender process started ");
        MeetingCalender meetingCalender = new MeetingCalender();
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method complete");

        try {
            conn = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(fetchCalenderSQL);
            preparedStatement.setInt(1, groupMasterId);
            preparedStatement.setString(2, status);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int grpMasterId = rs.getInt("group_master_id");
                meetingCalender.setGroupMasterId(grpMasterId);

                int id = rs.getInt("id");
                meetingCalender.setId(id);

                int cycleNo = rs.getInt("cycle_no");
                meetingCalender.setCycleNo(cycleNo);

                int shareAmount = rs.getInt("share_amount");
                meetingCalender.setShareAmount(shareAmount);

                Date meetingStartDate = rs.getDate("meeting_start_date");
                meetingCalender.setMeetingStartDate(meetingStartDate);

                Date meetingEndDate = rs.getDate("meeting_end_date");
                meetingCalender.setMeetingEndDate(meetingEndDate);

                int totalActiveMember = rs.getInt("total_active_members");
                meetingCalender.setTotalActiveMembers(totalActiveMember);

                String status1 = rs.getString("status");
                meetingCalender.setStatus(status1);
            }
        } catch (SQLException excp) {
            System.out.println("sql error:" + excp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException selexcp) {
                System.out.println("SQL error:" + selexcp.getMessage());
            }
        }
        System.out.println("finally message printed");
        return meetingCalender;
    }


    //private String updateMeetingCalenderAtEndSQL="update micro_finance.meeting_calender set status='C',meeting_end_date=sysdate() where group_master_id=? and status=?;";
    @Override
    public void endMeeting(int groupmasterId, String status) {
        System.out.println("dao service started");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("driver class not found....");
        }
        System.out.println("first try and catch method taken");
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = conn.prepareStatement(updateMeetingCalenderAtEndSQL);
            preparedStatement.setInt(1, groupmasterId);
            preparedStatement.setString(2, status);
            int rs = preparedStatement.executeUpdate();

            System.out.println("effected rows :" + rs);
            System.out.println("data updated successufuly");

        } catch (SQLException sqlExcp) {
            System.out.println("sql error:" + sqlExcp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
    }

    @Override
    public int getCalenderId(int groupMasterId, String status) {
        System.out.println("getting calender id");
        int calenderId = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("driver class not found....");
        }
        System.out.println("first try and catch method taken");

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement preparedStatement = conn.prepareStatement(fetchCalenderId);
            preparedStatement.setInt(1, groupMasterId);
            preparedStatement.setString(2, status);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                calenderId = rs.getInt("id");
            }
        } catch (SQLException sqlExcp) {
            System.out.println("Sql error:" + sqlExcp.getMessage());
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return calenderId;
    }
}
