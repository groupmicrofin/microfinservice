package com.gmf.services.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class MicroBankMember {


    private int id;
    private int groupMasterID;
    private String name;
    private String mail;
    private String password;
    private int mobile;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthdate;
    private String kycDcType;
    private String kycID;
    private String memberStatus;
    private LocalDate closeDate;
    private int shareBalance;
    private int calenderID;
    private LocalDate auditCreatedDate;
    private LocalDate auditUpdatedDate;

    public MicroBankMember() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupMasterID() {
        return groupMasterID;
    }

    public void setGroupMasterID(int groupMasterID) {
        this.groupMasterID = groupMasterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getKycDcType() {
        return kycDcType;
    }

    public void setKycDcType(String kycDcType) {
        this.kycDcType = kycDcType;
    }

    public String getKycID() {
        return kycID;
    }

    public void setKycID(String kycID) {
        this.kycID = kycID;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    public int getShareBalance() {
        return shareBalance;
    }

    public void setShareBalance(int shareBalance) {
        this.shareBalance = shareBalance;
    }

    public int getCalenderID() {
        return calenderID;
    }

    public void setCalenderID(int calenderID) {
        this.calenderID = calenderID;
    }

    public LocalDate getAuditCreatedDate() {
        return auditCreatedDate;
    }

    public void setAuditCreatedDate(LocalDate auditCreatedDate) {
        this.auditCreatedDate = auditCreatedDate;
    }

    public LocalDate getAuditUpdatedDate() {
        return auditUpdatedDate;
    }

    public void setAuditUpdatedDate(LocalDate auditUpdatedDate) {
        this.auditUpdatedDate = auditUpdatedDate;
    }

    @Override
    public String toString() {
        return "MicroBankMember{" +
                "id=" + id +
                ", groupMasterID=" + groupMasterID +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", mobile=" + mobile +
                ", birthdate=" + birthdate +
                ", kycDcType='" + kycDcType + '\'' +
                ", kycID='" + kycID + '\'' +
                ", memberStatus='" + memberStatus + '\'' +
                ", closeDate=" + closeDate +
                ", shareBalance=" + shareBalance +
                ", calenderID=" + calenderID +
                ", auditCreatedDate=" + auditCreatedDate +
                ", auditUpdatedDate=" + auditUpdatedDate +
                '}';
    }
}
