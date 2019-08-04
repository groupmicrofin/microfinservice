package com.gmf.services.repository;

import com.gmf.services.common.MicroBankConfig;
import com.gmf.services.exception.MicroBankAppException;
import com.gmf.services.model.MicroBankParam;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;

import static com.gmf.services.common.MicroBankConfig.DB_URL;

@Component
public class MicrobankParamDaoServiceImpl implements MicrobankParamDaoService {

    private String createMicroBankParamQuery = "INSERT INTO group_params (group_master_id,group_start_date,meeting_frequency,meeting_schedule,share_face_value,loan_interest_rate,loan_interest_base,loan_disb_amt_max_lim_percent,loan_gauranters_count,audit_created_date,audit_updated_date)VALUES (?,?,?,?,?,?,?,?,?,sysdate(),sysdate())";
    private String fetchGroupParamsSQl = "select id,group_start_date,meeting_frequency,meeting_schedule,share_face_value,loan_interest_rate,loan_interest_base, loan_disb_amt_max_lim_percent,loan_gauranters_count from group_params where group_master_id=?;";

    @Override
    public void create(MicroBankParam microBankParam) {
        System.out.println("dao method created");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and cath complete");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("input taken...1");
            PreparedStatement prestmt = conn.prepareStatement(createMicroBankParamQuery, Statement.RETURN_GENERATED_KEYS);
            System.out.println("input taken...2");
            //prestmt.setInt(1, microBankParam.getId());
            prestmt.setInt(1, microBankParam.getGroupMasterId());
            prestmt.setDate(2, Date.valueOf(microBankParam.getGroupStartDate()));
            prestmt.setInt(3, microBankParam.getMeetingFrequency());
            prestmt.setString(4, microBankParam.getMeetingSchedule());
            prestmt.setInt(5, microBankParam.getShareFaceValue());
            prestmt.setFloat(6, microBankParam.getLoanInterestRate());
            prestmt.setFloat(7, microBankParam.getLoanInterestBase());
            prestmt.setInt(8, microBankParam.getLnDisbAmountMaxLimitPercent());
            prestmt.setInt(9, microBankParam.getLoanGaurantersCount());

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
            System.out.println("Error Param Creation:" + sqlException.getMessage());
            MicroBankAppException appException = new MicroBankAppException("Error Creating Group Params");
            throw appException;
        } catch (Exception excep) {
            System.out.println("Exception:" + excep.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlExce) {
                    System.out.println("error in closing connection:" + sqlExce.getMessage());
                }
            }
        }
        System.out.println("second cath method complete");
    }

    @Override
    public MicroBankParam findById(int groupMasterId) {
        System.out.println("group param taken");
        MicroBankParam microBankParam = new MicroBankParam();
        //TODO

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method complete");
        //id,group_start_date,meeting_frequency,meeting_schedule,share_face_value,loan_interest_rate, loan_disb_amt_max_lim_percent,loan_gauranters_count
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement preparedStatement = conn.prepareStatement(fetchGroupParamsSQl);) {
            preparedStatement.setInt(1, groupMasterId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("id");
                microBankParam.setId(id);

                LocalDate groupStartDate = rs.getDate("group_start_date").toLocalDate();
                microBankParam.setGroupStartDate(groupStartDate);

                int meetingFrequency = rs.getInt("meeting_frequency");
                microBankParam.setMeetingFrequency(meetingFrequency);

                String meetingSchdule = rs.getString("meeting_schedule");
                microBankParam.setMeetingSchedule(meetingSchdule);

                int shareFaceValue = rs.getInt("share_face_value");
                microBankParam.setShareFaceValue(shareFaceValue);

                int loanInterestRate = rs.getInt("loan_interest_rate");
                microBankParam.setLoanInterestRate(loanInterestRate);

                int loanInterestbaserate = rs.getInt("loan_interest_base");
                microBankParam.setLoanInterestBase(loanInterestbaserate);

                int loanDisbMaxAmount = rs.getInt("loan_disb_amt_max_lim_percent");
                microBankParam.setLnDisbAmountMaxLimitPercent(loanDisbMaxAmount);

                int loanGuarnterCount = rs.getInt("loan_gauranters_count");
                microBankParam.setLoanGaurantersCount(loanGuarnterCount);

            }
        } catch (SQLException sqlExcp) {
            System.out.println("error:" + sqlExcp.getMessage());
        }
        System.out.println("### findById: " + microBankParam);
        return microBankParam;
    }

    private String fetchLoanInterestRate="select loan_interest_rate/12 AS monthly_int_rate from group_params where group_master_id=?;";
    @Override
    public float fetchLanInterestRate(int groupMasterId){
        float losnInterestRate=0;
        System.out.println("getting interest rate");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver Class Not Found....");
        }
        System.out.println("first try and catch method complete");

        try(Connection conn=DriverManager.getConnection(DB_URL)) {
            PreparedStatement preparedStatement=conn.prepareStatement(fetchLoanInterestRate);
            preparedStatement.setInt(1,groupMasterId);
            ResultSet rs=preparedStatement.executeQuery();

            if (rs.next()){
                losnInterestRate =rs.getFloat("monthly_int_rate");
            }

        }catch (SQLException sqlExcp){
            System.out.println("sql error:"+sqlExcp.getMessage());
        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return losnInterestRate;
    }
}
