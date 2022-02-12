package com.example.pasarmalam.Database;

public class UserHelperClass {
                String _fullName, _username, _email, _phoneNo,_password;

     public UserHelperClass(){

     }

    public UserHelperClass(String fullName, String username, String email, String phoneNo, String password, String date, String gender){
        this._fullName = fullName;
        this._username = username;
        this._email = email;
        this._phoneNo = phoneNo;
        this._password = password;

    }

    public String getFullName() {
        return _fullName;
    }

    public void setFullName(String fullName) {
        this._fullName = fullName;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        this._username = username;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    public String getPhoneNo() {
        return _phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this._phoneNo = phoneNo;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

}

