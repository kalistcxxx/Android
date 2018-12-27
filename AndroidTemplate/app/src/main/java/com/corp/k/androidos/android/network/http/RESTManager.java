package com.corp.k.androidos.android.network.http;

import android.app.Activity;
import com.corp.k.androidos.android.models.ServerInfo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;


/**
 * Provides APIs for working with server
 */
public class RESTManager {

    private static RESTManager sInstance;
    private static ServerInfo sServerInfo;
    private Activity mContext;

    private RESTManager(Activity context, ServerInfo server) {
        this.mContext = context;
        sServerInfo = server;
    }

    private RESTManager(Activity context) {
        this.mContext = context;
    }

    /**
     * Getting a instance of {@link RESTManager} class
     * *
     */
    public static RESTManager getInstance(Activity context, ServerInfo server) {
        if (sInstance == null) {
            sInstance = new RESTManager(context);
        }

        sServerInfo = server;

        return sInstance;
    }

    private static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public Activity getmContext() {
        return mContext;
    }

    public void setmContext(Activity mContext) {
        this.mContext = mContext;
    }

    public ServerInfo getmServerInfo() {
        return sServerInfo;
    }

    //================================LOCAL USER REQUEST ===========================================

    public void setmServerInfo(ServerInfo mServerInfo) {
        RESTManager.sServerInfo = mServerInfo;
    }

    public void stopAllRequest() {
        HTTPExecutor.getInstance(mContext).stopAll();
    }

//    public void authenticate(String username, String password, String apiKey, FutureRequest<UserResponse> listener) {
//        //1. Validate parameters
//        if (listener == null) {
//            throw new NullPointerException("listener MUST be not NULL");
//        }
//
//        if (!isInitialized()) {
//            listener.onFailed(PBError.ERR_APP_INIT_ERR.value(), PBError.ERR_APP_INIT_ERR.getReasonPhrase());
//            return;
//        }
//        StringBuffer url = getURL();
//
//        if (username == null) {
//            listener.onFailed(PBError.ERR_APP_INIT_ERR.value(), PBError.ERR_APP_INIT_ERR.getReasonPhrase());
//            return;
//        }
//        if (password == null) {
//            listener.onFailed(PBError.ERR_APP_INIT_ERR.value(), PBError.ERR_APP_INIT_ERR.getReasonPhrase());
//            return;
//        }
//        if (apiKey == null) {
//            listener.onFailed(PBError.ERR_APP_INIT_ERR.value(), PBError.ERR_APP_INIT_ERR.getReasonPhrase());
//            return;
//        }
//
//        url.append(HTTPS_AUTHENTICATE);
//
//        //2. Create HTTP header params
//        Map<String, Object> params = createDefautlHeaderParam();
//        //Make basic authentication token
//        String creds = username + ":" + password;
//        creds = Base64.encodeToString(creds.getBytes(), 0);
//        params.put(HEADER_PARAM_AUTHENTICATION, "Basic " + creds);
//        params.put(HEADER_PARAM_APIKEY, apiKey);
//
//        //3. Send request
//        HTTPExecutor.getInstance(mContext).get(url.toString(), params,
//                new HTTPResponseListener(HTTPResponseListener.ResponseType.USER_SIGNIN, listener));
//    }
//
//    /**
//     * Get the public informationn
//     *
//     * @param accessToken the token obtained from authenticate function
//     * @param listener    A callback to be invoked when request completed.This value MUST be not null
//     *                    throws exception in case there is any NULL input param or this class is not initialized correctly
//     */
//    public void getPublicEventInfo(@NonNull String accessToken,
//                                   @NonNull final RequestListener<EventInfoResponse> listener) throws Exception {
//        //1. Validate parameters
//        if (listener == null) {
//            throw new NullPointerException("listener MUST be not NULL");
//        }
//
//        if (!isInitialized()) {
//            listener.onFailed(PBError.ERR_APP_INIT_ERR.value(), PBError.ERR_APP_INIT_ERR.getReasonPhrase());
//            return;
//        }
//        StringBuffer url = getURL();
//
//        if (accessToken == null) {
//            listener.onFailed(PBError.UNAUTHORIZED.value(), PBError.UNAUTHORIZED.getReasonPhrase());
//            return;
//        }
//
//        url.append(HTTPS_GET_PUBLIC_INFO);
//
//        //2. Create HTTP header params
//        Map<String, Object> params = createDefautlHeaderParam();
//        params.put(HEADER_PARAM_ACCESSTOKEN, accessToken);
//
//        //3. Send request
//        HTTPExecutor.getInstance(mContext).get(url.toString(), params,
//                new HTTPResponseListener(HTTPResponseListener.ResponseType.GET_PUBLIC_EVENT_INFO, listener));
//    }
//
//    /**
//     * Get convenient information
//     *
//     * @param accessToken the token obtained from authenticate function
//     * @param listener    A callback to be invoked when request completed.This value MUST be not null
//     *                    throws exception in case there is any NULL input param or this class is not initialized correctly
//     */
//    public void getConvenientInfo(@NonNull String accessToken,
//                                  @NonNull final RequestListener<EventInfoResponse> listener) throws Exception {
//        //1. Validate parameters
//        if (listener == null) {
//            throw new NullPointerException("listener MUST be not NULL");
//        }
//
//        if (!isInitialized()) {
//            listener.onFailed(PBError.ERR_APP_INIT_ERR.value(), PBError.ERR_APP_INIT_ERR.getReasonPhrase());
//            return;
//        }
//        StringBuffer url = getURL();
//
//        if (accessToken == null) {
//            listener.onFailed(PBError.UNAUTHORIZED.value(), PBError.UNAUTHORIZED.getReasonPhrase());
//            return;
//        }
//
//        url.append(HTTPS_GET_CONVENIENT_INFO);
//
//        //2. Create HTTP header params
//        Map<String, Object> params = createDefautlHeaderParam();
//        params.put(HEADER_PARAM_ACCESSTOKEN, accessToken);
//
//        //3. Send request
//        HTTPExecutor.getInstance(mContext).get(url.toString(), params,
//                new HTTPResponseListener(HTTPResponseListener.ResponseType.GET_CONVENIENT_INFO, listener));
//    }
//
//    /**
//     * Get personal information
//     *
//     * @param accessToken the token obtained from authenticate function
//     * @param listener    A callback to be invoked when request completed.This value MUST be not null
//     *                    throws exception in case there is any NULL input param or this class is not initialized correctly
//     */
//    public void getPersonalInfo(@NonNull String accessToken, String userId,
//                                @NonNull final RequestListener<EventInfoResponse> listener) throws Exception {
//        //1. Validate parameters
//        if (listener == null) {
//            throw new NullPointerException("listener MUST be not NULL");
//        }
//
//        if (!isInitialized()) {
//            listener.onFailed(PBError.ERR_APP_INIT_ERR.value(), PBError.ERR_APP_INIT_ERR.getReasonPhrase());
//            return;
//        }
//        StringBuffer url = getURL();
//
//        if (accessToken == null) {
//            listener.onFailed(PBError.UNAUTHORIZED.value(), PBError.UNAUTHORIZED.getReasonPhrase());
//            return;
//        }
//
//        url.append(HTTPS_GET_PERSONAL_INFO);
//
//        //2. Create HTTP header params
//        Map<String, Object> params = createDefautlHeaderParam();
//        params.put(HEADER_PARAM_ACCESSTOKEN, accessToken);
//        params.put(HEADER_PARAM_USER_ID, userId);
//
//        //3. Send request
//        HTTPExecutor.getInstance(mContext).get(url.toString(), params,
//                new HTTPResponseListener(HTTPResponseListener.ResponseType.GET_PERSONAL_INFO, listener));
//    }
//
//
//    /**
//     * Get personal information
//     *
//     * @param accessToken the token obtained from authenticate function
//     * @param listener    A callback to be invoked when request completed.This value MUST be not null
//     *                    throws exception in case there is any NULL input param or this class is not initialized correctly
//     */
//    public void getMeetingInfo(@NonNull String accessToken, String userId,
//                               @NonNull final RequestListener<EventInfoResponse> listener) throws Exception {
//        //1. Validate parameters
//        if (listener == null) {
//            throw new NullPointerException("listener MUST be not NULL");
//        }
//
//        if (!isInitialized()) {
//            listener.onFailed(PBError.ERR_APP_INIT_ERR.value(), PBError.ERR_APP_INIT_ERR.getReasonPhrase());
//            return;
//        }
//        StringBuffer url = getURL();
//
//        if (accessToken == null) {
//            listener.onFailed(PBError.UNAUTHORIZED.value(), PBError.UNAUTHORIZED.getReasonPhrase());
//            return;
//        }
//
//        url.append(HTTPS_GET_MEETING_INFO);
//
//        //2. Create HTTP header params
//        Map<String, Object> params = createDefautlHeaderParam();
//        params.put(HEADER_PARAM_ACCESSTOKEN, accessToken);
//        params.put(HEADER_PARAM_USER_ID, userId);
//
//        //3. Send request
//        HTTPExecutor.getInstance(mContext).get(url.toString(), params,
//                new HTTPResponseListener(HTTPResponseListener.ResponseType.GET_MEETING_INFO, listener));
//    }
//
//    /**
//     * Register user
//     *
//     * @param accessToken the token obtained from authenticate function
//     * @param listener    A callback to be invoked when request completed.This value MUST be not null
//     *                    throws exception in case there is any NULL input param or this class is not initialized correctly
//     */
//    public void registerUser(@NonNull String accessToken, String userId, String password, String name, String nationality, String gender, String position,
//                             String company, String professional, @NonNull final RequestListener<EventInfoResponse> listener) throws Exception {
//        //1. Validate parameters
//        if (listener == null) {
//            throw new NullPointerException("listener MUST be not NULL");
//        }
//
//        if (!isInitialized()) {
//            listener.onFailed(PBError.ERR_APP_INIT_ERR.value(), PBError.ERR_APP_INIT_ERR.getReasonPhrase());
//            return;
//        }
//        StringBuffer url = getURL();
//
//        if (accessToken == null) {
//            listener.onFailed(PBError.UNAUTHORIZED.value(), PBError.UNAUTHORIZED.getReasonPhrase());
//            return;
//        }
//
//        url.append(HTTPS_POST_REGISTER_USER);
//
//        //2. Create HTTP header params
//        Map<String, Object> params = createDefautlHeaderParam();
//        params.put(HEADER_PARAM_ACCESSTOKEN, accessToken);
//        params.put(HEADER_PARAM_USER_ID, userId);
//        params.put(HEADER_PARAM_PASSWORD, password);
//        params.put(HEADER_PARAM_NATIONALITY, nationality);
//        params.put(HEADER_PARAM_POSITION, position);
//        params.put(HEADER_PARAM_GENDER, gender);
//        params.put(HEADER_PARAM_COMPANY, company);
//        params.put(HEADER_PARAM_PROFESSIONAL, professional);
//
//        //3. register user
//        HTTPExecutor.getInstance(mContext).post(url.toString(), params,
//                new HTTPResponseListener(HTTPResponseListener.ResponseType.REGISTER_USER, listener));
//    }
//
//    /**
//     * Update personal information
//     *
//     * @param accessToken the token obtained from authenticate function
//     * @param listener    A callback to be invoked when request completed.This value MUST be not null
//     *                    throws exception in case there is any NULL input param or this class is not initialized correctly
//     */
//    public void updatePersonalInfo(@NonNull String accessToken, String name, String nationality, String gender, String position,
//                                   String company, String professional, @NonNull final RequestListener<EventInfoResponse> listener) throws Exception {
//        //1. Validate parameters
//        if (listener == null) {
//            throw new NullPointerException("listener MUST be not NULL");
//        }
//
//        if (!isInitialized()) {
//            listener.onFailed(PBError.ERR_APP_INIT_ERR.value(), PBError.ERR_APP_INIT_ERR.getReasonPhrase());
//            return;
//        }
//        StringBuffer url = getURL();
//
//        if (accessToken == null) {
//            listener.onFailed(PBError.UNAUTHORIZED.value(), PBError.UNAUTHORIZED.getReasonPhrase());
//            return;
//        }
//
//        url.append(HTTPS_POST_UPDATE_PERSONAL_INFO);
//
//        //2. Create HTTP header params
//        Map<String, Object> params = createDefautlHeaderParam();
//        params.put(HEADER_PARAM_ACCESSTOKEN, accessToken);
//        params.put(HEADER_PARAM_NAME, name);
//        params.put(HEADER_PARAM_NATIONALITY, nationality);
//        params.put(HEADER_PARAM_POSITION, position);
//        params.put(HEADER_PARAM_GENDER, gender);
//        params.put(HEADER_PARAM_COMPANY, company);
//        params.put(HEADER_PARAM_PROFESSIONAL, professional);
//
//        //3. Send request
//        HTTPExecutor.getInstance(mContext).post(url.toString(), params,
//                new HTTPResponseListener(HTTPResponseListener.ResponseType.UPDATE_PERSONAL_INFO, listener));
//    }

    /**
     * Function is used to check this class is initialzed or not
     * Before execute any REST request --> should check this first
     *
     * @return true if this class is initialized
     * false otherwise
     */
    private boolean isInitialized() {
        boolean check = true;

        if ((this.mContext == null)
                || (sServerInfo == null)) {
            check = false;
        }

        return check;
    }

//    /**
//     * @return
//     */
//    private Map<String, Object> createDefautlHeaderParam() {
//        //add header param
//        Map<String, Object> headerParams = new HashMap<>();
//        headerParams.put(HEADER_PARAM_CONTENT_TYPE, HEADER_PARAM_CONTENT_TYPE_JSON);
//        headerParams.put(HEADER_PARAM_CHARSET, HEADER_PARAM_CHARSET_UTF8);
//
//        return headerParams;
//    }
//
//    /**
//     * Function is used to get URL for used in HTTP request from device IP and port
//     *
//     * @return url for input category
//     * null if the device is not initialized
//     */
//    private StringBuffer getURL() {
//        StringBuffer url;
//        url = new StringBuffer();
//        String targetServer = sServerInfo.getmAddress();
//        url.append(HTTP_URL).append(targetServer).append(HTTPS_ROOT_URL);
//        return url;
//    }
}
