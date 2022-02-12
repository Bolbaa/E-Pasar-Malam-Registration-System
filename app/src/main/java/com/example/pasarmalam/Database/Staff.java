package com.example.pasarmalam.Database;

public class Staff {
            String staffName, staffUsername, staffEmail, staffPhoneNum, staffPassword, staffRetypePass;

    public Staff() {

    }

    public Staff(String staffName,String staffUsername, String staffEmail, String staffPassword, String staffRetypePass, String staffPhoneNum) {
        this.staffName = staffName;
        this.staffUsername = staffUsername;
        this.staffEmail = staffEmail;
        this.staffPassword = staffPassword;
        this.staffRetypePass = staffRetypePass;
        this.staffPhoneNum = staffPhoneNum;
    }

    public String getStaffName() {
        return staffName;
    }


    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffUsername() {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffPhoneNum() {
        return staffPhoneNum;
    }

    public void setStaffPhoneNum(String staffPhoneNum) {
        this.staffPhoneNum = staffPhoneNum;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffRetypePass() {
        return staffRetypePass;
    }

    public void setStaffRetypePass(String staffRetypePass) {
        this.staffRetypePass = staffRetypePass;
    }
}
