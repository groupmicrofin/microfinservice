package com.gmf.services.repository;

import com.gmf.services.common.MicroBankConfig;
import com.gmf.services.model.EvenetLog;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class MicroBankEventLogDaoServiceImpl implements MicroBankEventLogDaoService {

    private String insertEventLogSQL = "insert into group_event_log(group_master_id,group_member_id,txn_code,txn_amount,txn_date,remark,calender_id) values(?,?,?,?,sysdate(),?,?);";


    public boolean insertingGroupEventLog(EvenetLog evenetLog) {
        System.out.println("insert event log " + evenetLog.getRemark());
        boolean result = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("driver class not find");
        }
        System.out.println("first try and catch method taklen");

        try (Connection conn = DriverManager.getConnection(MicroBankConfig.DB_URL);) {
            PreparedStatement preparedStatement = conn.prepareStatement(insertEventLogSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, evenetLog.getGroupMasterId());
            preparedStatement.setInt(2, evenetLog.getGroupMemberId());
            preparedStatement.setString(3, evenetLog.getTxnCode());
            preparedStatement.setDouble(4, evenetLog.getTxnAmount());
            preparedStatement.setString(5, evenetLog.getRemark());
            preparedStatement.setInt(6, evenetLog.getCalenderId());

            int affectedRows = preparedStatement.executeUpdate();
            result = true;
            System.out.println("affected rows:" + affectedRows);
            System.out.println("data taken for event log");

            if (affectedRows == 0) {
                throw new SQLException("inserting event log data failed , no row affected");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    System.out.println("id is:" + id);
                } else {
                    throw new Exception("inserting id failed , no id obtained");
                }
            } catch (Exception e) {
                System.out.println("error for genereted keys:" + e.getMessage());
            }

        } catch (SQLException sqlExcp) {
            System.out.println("sql error:" + sqlExcp.getMessage());
        }
        return result;
    }

}
