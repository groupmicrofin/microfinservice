package com.gmf.services.model;

import java.time.LocalDate;

public class MicroBnak {

    private int id;
    private String name;
    private String loginId;
    private String email;
    private String passphrase;
    private LocalDate auditCreatedDate;
    private LocalDate auditUpdatedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
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
        return "MicroBnak{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loginId='" + loginId + '\'' +
                ", email='" + email + '\'' +
                ", auditCreatedDate=" + auditCreatedDate +
                ", auditUpdatedDate=" + auditUpdatedDate +
                '}';
    }
}
