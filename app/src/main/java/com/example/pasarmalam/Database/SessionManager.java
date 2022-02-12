package com.example.pasarmalam.Database;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    //variables
    SharedPreferences usersSessions;
    SharedPreferences.Editor editor;
    Context context;

    //session names
    public static final String SESSION_USERSESSION = "userLoginSession";
    public static final String SESSION_REMEMBERME= "rememberMe";

    //userSessionVariables
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_FULLNAME = "fullName";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONENUMBER = "phoneNumber";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DATE = "date";
    public static final String KEY_GENDER = "gender";

    //remember me variables
    private static final String IS_REMEMBERME = "IsRememberMe";
    public static final String KEY_SESSIONPHONENUMBER ="phoneNumber";
    public static final String KEY_SESSIONPASSWORD = "password";

    //constructor
    public SessionManager(Context _context, String sessionName) {
        context = _context;
        usersSessions = _context.getSharedPreferences("sessionName", Context.MODE_PRIVATE);
        editor = usersSessions.edit();

    }

    /*
    users login
    session
     */

    public void createLoginSession(String fullName, String username, String email, String phoneNo, String password, String age, String gender) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_FULLNAME, fullName);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONENUMBER, phoneNo);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_DATE, age);
        editor.putString(KEY_GENDER, gender);

        editor.commit();

    }

    public HashMap<String, String> getUsersDetailFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_FULLNAME, usersSessions.getString(KEY_FULLNAME, null));
        userData.put(KEY_FULLNAME, usersSessions.getString(KEY_USERNAME, null));
        userData.put(KEY_FULLNAME, usersSessions.getString(KEY_EMAIL, null));
        userData.put(KEY_FULLNAME, usersSessions.getString(KEY_PHONENUMBER, null));
        userData.put(KEY_FULLNAME, usersSessions.getString(KEY_PASSWORD, null));
        userData.put(KEY_FULLNAME, usersSessions.getString(KEY_DATE, null));
        userData.put(KEY_FULLNAME, usersSessions.getString(KEY_GENDER, null));

        return userData;
    }

    public boolean checkLogin() {
        if (usersSessions.getBoolean(IS_LOGIN, false)) {
            return true;
        } else
            return false;
    }

    public void logoutUserFromSession() {
        editor.clear();
        editor.commit();
    }


    /*
    Remember me
    session functions
     */

    public void createRememberMeSession( String phoneNo, String password) {

        editor.putBoolean(IS_REMEMBERME, true);
        editor.putString(KEY_SESSIONPHONENUMBER, phoneNo);
        editor.putString(KEY_SESSIONPASSWORD, password);

        editor.commit();

    }



    public HashMap<String, String> getRememberMeDetailsFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_SESSIONPHONENUMBER, usersSessions.getString(KEY_SESSIONPHONENUMBER, null));
        userData.put(KEY_SESSIONPASSWORD, usersSessions.getString(KEY_SESSIONPASSWORD, null));


        return userData;
    }

    public boolean checkRememberMe() {
        if (usersSessions.getBoolean(IS_REMEMBERME, false)) {
            return true;
        } else
            return false;
    }

}
