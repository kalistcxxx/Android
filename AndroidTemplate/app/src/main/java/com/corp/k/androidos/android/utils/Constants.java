package com.corp.k.androidos.android.utils;

/**
 * Created by prdcv172 on 8/15/18.
 */

public class Constants {
    public static final String MY_PREF = "MY_PREF";
    public static final String PATTERM_ACCEPTED_USERNAME = "[^a-zA-Z0-9]+$";
    public static final String PATTERM_ACCEPTED_PASSWORD = "(?=.*[0-9])(?=.*[A-Z])[a-zA-Z0-9]+$";
    public static final int MIN_CHARACTER_USERNAME = 4;
    public static final int MIN_CHARACTER_PASSWORD = 6;
    public static final int MAX_CHARACTER_USERNAME = 128;
    public static final int MAX_CHARACTER_PASSWORD = 128;

    public static final String DEFAULT_USER_ID = "#123456";
    public static final String DEFAULT_USER_ID_2 = "1A0DC9A8";
    public static final String IS_SHOW_REGISTERD_MEETING = "IS_SHOW_REGISTERD_MEETING";
    public static final String KEY_SEND_MEETING_SELECTED = "KEY_SEND_MEETING_SELECTED";

    public static final String KEY_CHOOSE_RESTAURANT = "KEY_CHOOSE_RESTAURANT";
    public static final String KEY_CHOOSE_STORE = "KEY_CHOOSE_STORE";
    public static final String KEY_CHOOSE_ATM = "KEY_CHOOSE_ATM";
    public static final String KEY_CHOOSE_CONVENIENT_INFO = "KEY_CHOOSE_CONVENIENT_INFO";

    public static final String KEY_SAVE_FULLNAME = "KEY_SAVE_FULLNAME";
    public static final String KEY_SAVE_NATIONAL = "KEY_SAVE_NATIONAL";
    public static final String KEY_SAVE_GENDER = "KEY_SAVE_GENDER";
    public static final String KEY_SAVE_POSITION = "KEY_SAVE_POSITION";
    public static final String KEY_SAVE_COMPANY = "KEY_SAVE_COMPANY";
    public static final String KEY_SAVE_PROFESSIONAL = "KEY_SAVE_PROFESSIONAL";
    public static final String KEY_OPEN_FROM_REGISTER_SCREEN = "KEY_OPEN_FROM_REGISTER_SCREEN";
    public static final String KEY_SEND_COVENIENT_STORE_SELECTED = "KEY_SEND_COVENIENT_STORE_SELECTED";
    public static final String IP = "";
    public static final int PORT = 8080;
    public static final String KEY_SAVE_CURRENT_USER = "KEY_SAVE_CURRENT_USER";

    public static final long LONG_3000 = 3000;
    public static final int VOLLEY_TIMEOUT = 15000;
    public static final int VOLLEY_MAX_NUM_RETRIES = 1;
}
