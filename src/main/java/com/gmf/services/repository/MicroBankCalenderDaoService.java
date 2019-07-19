package com.gmf.services.repository;

import com.gmf.services.model.MeetingCalender;

import java.sql.*;

public class MicroBankCalenderDaoService {

    private String DB_URL = "jdbc:mysql://localhost:3306/micro_finance?user=root&password=password";
    private String fetchNextMeetingCycleNo = "select IFNULL(max(cycle_no),0)+1 cycle_no from micro_finance.meeting_calender where group_master_id=?;";
    private String createMeetingCalenderQuery = "insert into micro_finance.meeting_calender (group_master_id,cycle_no,share_amount,meeting_start_date,meeting_end_date,total_active_members,status)VALUES(?,?,?,sysdate(),sysdate(),?,?);";

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
            prestmt.setInt(1,groupMasterId);
            ResultSet rs = prestmt.executeQuery();
            if(rs.next()) {
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

    public void createMeetingCalender(MeetingCalender meetingCalender) {
        System.out.println("meeting calender created");

        //TODO

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
            prest.setDate(4, Date.valueOf(meetingCalender.getMeetingStartDate()));
            prest.setDate(5, Date.valueOf(meetingCalender.getMeetingEndDate()));
            prest.setInt(6, meetingCalender.getTotalActiveMembers());
            prest.setString(7, meetingCalender.getStatus());

            System.out.println("input taken 1");
            int result = prest.executeUpdate();

            ResultSet rs = prest.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                meetingCalender.setId(1);
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

}
