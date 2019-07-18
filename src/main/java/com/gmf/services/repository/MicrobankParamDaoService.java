package com.gmf.services.repository;

import com.gmf.services.model.MicroBankParam;

import java.sql.*;

public class MicrobankParamDaoService {

    private String DB_URL = "jdbc:mysql://localhost:3306/micro_finance?user=root&password=password";
    //private String createMicroBankParamQuery = "INSERT INTO group_params (id,group_master_id,group_start_date,meeting_frequency,meeting_schedule,share_face_value,loan_interest_rate,loan_interest_base,loan_disb_amt_max_lim_percent,loan_gaurnters_count,audit_created_date,audit_update_date) VALUES (?,?,sysdate(),1,'last sunday',100,12,1,200,2,sysdate(),sysdate())";

    //private String createMicroBankParamQuery ="INSERT INTO group_params (group_master_id,group_start_date,meeting_frequency,meeting_schedule,share_face_value,loan_interest_rate,loan_interest_base,loan_disb_amt_max_lim_percent,loan_gaurnters_count,audit_created_date,audit_update_date)VALUES (?,sysdate(),1,'last sunday',100,12,1,200,2,sysdate(),sysdate())";
    private String createMicroBankParamQuery ="INSERT INTO group_params (group_master_id,group_start_date,meeting_frequency,meeting_schedule,share_face_value,loan_interest_rate,loan_interest_base,loan_disb_amt_max_lim_percent,loan_gauranters_count,audit_created_date,audit_updated_date)VALUES (?,?,?,?,?,?,?,?,?,sysdate(),sysdate())";

    public void create(MicroBankParam microBankParam) {
        System.out.println("dao method created");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and cath complete");
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("input taken...1");
            PreparedStatement prestmt = conn.prepareStatement(createMicroBankParamQuery, Statement.RETURN_GENERATED_KEYS);
            System.out.println("input taken...2");
            //prestmt.setInt(1, microBankParam.getId());
            prestmt.setInt(1,microBankParam.getGroupMasterId());
            prestmt.setDate(2,Date.valueOf(microBankParam.getGroupStartDate()));
            prestmt.setInt(3,microBankParam.getMeetingFrequency());
            prestmt.setString(4,microBankParam.getMeetingSchedule());
            prestmt.setInt(5,microBankParam.getShareFaceValue());
            prestmt.setFloat(6,microBankParam.getLoanInterestRate());
            prestmt.setFloat(7,microBankParam.getLoanInterestBase());
            prestmt.setInt(8,microBankParam.getLnDisbAmountMaxLimitPercent());
            prestmt.setInt(9,microBankParam.getLoanGaurantersCount());

            System.out.println("input taken");

            int result = prestmt.executeUpdate();
            System.out.println("result printed");

            ResultSet rs = prestmt.getGeneratedKeys();
            if (rs.next()) {
                int grpID = rs.getInt(1);
                microBankParam.setId(grpID);
            }

            System.out.println("second try method complete");

        } catch (SQLException sqlException) {
            System.out.println("error in connection" + sqlException.getMessage());
        }catch (Exception excep){
            System.out.println("Exception:"+excep.getMessage());
        }finally {
            if(conn!=null) {
                try {
                    conn.close();
                }catch (SQLException sqlExce){
                    System.out.println("error in closing connection:"+sqlExce.getMessage());
                }

            }
        }
        System.out.println("second cath method complete");
    }

    public MicroBankParam findById(int groupMasterId){
        System.out.println("group param taken");
        MicroBankParam microBankParam = new MicroBankParam();
        //TODO
        return microBankParam;
    }
}
