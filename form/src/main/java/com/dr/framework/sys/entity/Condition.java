package com.dr.framework.sys.entity;

public class Condition {
    private String organiseId;
    private String userName;
    private String userCode;
    private String personType;
    private String status;

    public String getOrganiseId() {
        return organiseId;
    }

    public void setOrganiseId(String organiseId) {
        this.organiseId = organiseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
