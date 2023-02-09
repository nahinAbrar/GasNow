package com.isd.gasnow.Database;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
public class SessionManager {
    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;
    public static final String USER_SESSION = "userLoginSession";
    public static final String USER_REMEMBER_ME_SESSION = "userRememberMeSession";


    private static final String IS_LOGIN = "isLoggedIn";

    public static final String KEY_FULL_NAME = "fullName";
    public static final String KEY_USERNAME = "userName";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "passWord";
    public static final String KEY_PHONE_NUMBER = "phoneNUmber";
    public static final String KEY_AREA = "area";
    public static final String KEY_ADDRESS = "address";



    private static final String IS_USER_REMEMBER_ME = "isRememberMe";
    public static final String KEY_REMEMBER_ME_PASSWORD = "passWord";
    public static final String KEY_REMEMBER_ME_PHONE_NUMBER = "phoneNUmber";


    public SessionManager(Context _context, String sessionName) {
        context = _context;
        userSession = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = userSession.edit();
    }

    public void createLoginSession(String fullName, String userName, String email, String passWord, String area, String address, String phoneNumber) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_FULL_NAME, fullName);
        editor.putString(KEY_USERNAME, userName);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, passWord);
        editor.putString(KEY_AREA, area);
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_PHONE_NUMBER, phoneNumber);

        editor.commit();

    }

    public HashMap<String, String> getUserDetailFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_FULL_NAME, userSession.getString(KEY_FULL_NAME, null));
        userData.put(KEY_USERNAME, userSession.getString(KEY_USERNAME, null));
        userData.put(KEY_EMAIL, userSession.getString(KEY_EMAIL, null));
        userData.put(KEY_PASSWORD, userSession.getString(KEY_PASSWORD, null));
        userData.put(KEY_AREA, userSession.getString(KEY_AREA, null));
        userData.put(KEY_ADDRESS, userSession.getString(KEY_ADDRESS, null));
        userData.put(KEY_PHONE_NUMBER, userSession.getString(KEY_PHONE_NUMBER, null));

        return userData;
    }

    public boolean checkLogin() {
        return userSession.getBoolean(IS_LOGIN, false);
    }

    public void logoutUserFromSession() {
        editor.clear();
        editor.commit();
    }


    public void createRememberMeSession(String phoneNumber, String passWord) {
        editor.putBoolean(IS_USER_REMEMBER_ME, true);

        editor.putString(KEY_REMEMBER_ME_PHONE_NUMBER, phoneNumber);
        editor.putString(KEY_REMEMBER_ME_PASSWORD, passWord);

        editor.commit();

    }

    public HashMap<String, String> getRememberMeDetailFromSession() {
        HashMap<String, String> userRememberMe = new HashMap<String, String>();

        userRememberMe.put(KEY_REMEMBER_ME_PHONE_NUMBER, userSession.getString(KEY_REMEMBER_ME_PHONE_NUMBER, null));
        userRememberMe.put(KEY_REMEMBER_ME_PASSWORD, userSession.getString(KEY_REMEMBER_ME_PASSWORD, null));

        return userRememberMe;
    }

    public boolean checkRememberMe() {
        return userSession.getBoolean(IS_USER_REMEMBER_ME, false);
    }

}
