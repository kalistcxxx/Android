package com.corp.k.androidos.android.models.restapi;

public class HTTPConstant {
    public final static int HTTP_TIMEOUT = 30000;
    public final static int HTTP_MAX_RETRIES = 2;

    public static final String HEADER_PARAM_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_PARAM_CONTENT_TYPE_JSON = "application/json";
    public static final String HEADER_PARAM_CONTENT_TYPE_URLENCODED = "application/x-www-form-urlencoded";
    public static final String HEADER_PARAM_CONTENT_TYPE_IMAGE = "image/jpeg";
    public static final String HEADER_PARAM_CHARSET = "charset";
    public static final String HEADER_PARAM_CHARSET_UTF8 = "utf-8";
    public static final String HEADER_PARAM_AUTHENTICATION = "Authorization";
    public static final String HEADER_PARAM_APIKEY = "ApiKey";
    public static final String HEADER_PARAM_ACCESSTOKEN = "AccessToken";
    public static final String HEADER_PARAM_USER_ID = "UserID";
    public static final String HEADER_PARAM_PASSWORD = "password";
    public static final String HEADER_PARAM_NAME = "name";
    public static final String HEADER_PARAM_NATIONALITY = "nationality";
    public static final String HEADER_PARAM_GENDER = "gender";
    public static final String HEADER_PARAM_POSITION = "position";
    public static final String HEADER_PARAM_COMPANY = "company";
    public static final String HEADER_PARAM_PROFESSIONAL = "professional";
    public static final String HEADER_PARAM_EMS_TOKEN = "X-Ems-Token";
    public final static int HTTP_SIGNIN_MAX_RETRIES = 3; //TODO: need change to resonable value

    public final static String HTTP_URL = "http://";
    public final static String HTTPS_URL = "https://";
    public final static String HTTPS_ROOT_URL = "/api";

    public final static String HTTPS_AUTHENTICATE = "/getAuthenticateToken";
    public final static String HTTPS_GET_PUBLIC_INFO = "/getPublicInfo";
    public final static String HTTPS_GET_CONVENIENT_INFO = "/getConvenientInfo";

    public final static String HTTPS_GET_PERSONAL_INFO = "/getPersonalInfo";
    public final static String HTTPS_GET_MEETING_INFO = "/getMeetingInfo";
    public final static String HTTPS_POST_REGISTER_USER = "/registerUser";
    public final static String HTTPS_POST_UPDATE_PERSONAL_INFO = "/updatePersonalInfo";

}
