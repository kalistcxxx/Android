package com.corp.k.androidos.android.network.http;

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.corp.k.androidos.R;
import com.corp.k.androidos.android.models.restapi.*;

import com.corp.k.androidos.android.models.restapi.user.UserResponse;
import com.corp.k.androidos.android.utils.LogUtils;
import com.corp.k.androidos.android.view.dialogs.DialogHandler;
import com.corp.k.androidos.android.models.restapi.HTTPConstant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A custom request is used for sending/receiving HTTP requests
 */
class HTTPRequest extends Request<SimpleResponse> {

    private final String TAG = getClass().getSimpleName();

    private HTTPResponseListener mResponseListener;

    private int mMethod;
    private String mURL;
    private Map<String, Object> mHeaders;
    private Map<String, Object> mParams;
    private byte[] mData;
    private Activity mAct;
    private HTTPRequest mPreviousReq;
    private int mRetry;
    private int mReSignin;

    /**
     * Create an instance of {@link HTTPRequest} class.
     *
     * @param method   one of values from {@link Method}
     * @param headers  Parameters to be used for sending request
     * @param url      The network address to use to send request
     * @param listener Listener to receive request response or error
     */
    public HTTPRequest(Activity context,
                       int method, String url,
                       Map<String, Object> headers,
                       Map<String, Object> params,
                       byte[] data,
                       HTTPResponseListener listener) {

        super(method, url, listener);

        setmRetry(0);
        setmReSignin(0);
        setmAct(context);
        setmMethod(method);
        setmURL(url);
        setmHeaders(headers);
        setmParams(params);
        setmData(data);
        setmResponseListener(listener);

        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy(
                HTTPConstant.HTTP_TIMEOUT,
                HTTPConstant.HTTP_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mPreviousReq = this;
    }

    private HTTPResponseListener getmResponseListener() {
        return mResponseListener;
    }

    private void setmResponseListener(HTTPResponseListener mResponseListener) {
        this.mResponseListener = mResponseListener;
    }

    private int getmMethod() {
        return mMethod;
    }

    private void setmMethod(int mMethod) {
        this.mMethod = mMethod;
    }

    private String getmURL() {
        return mURL;
    }

    private void setmURL(String mURL) {
        this.mURL = mURL;
    }

    private Map<String, Object> getmHeaders() {
        return mHeaders;
    }

    private void setmHeaders(Map<String, Object> mHeaders) {
        this.mHeaders = mHeaders;
    }

    private Map<String, Object> getmParams() {
        return mParams;
    }

    private void setmParams(Map<String, Object> mParams) {
        this.mParams = mParams;
    }

    private byte[] getmData() {
        return mData;
    }

    private void setmData(byte[] mData) {
        this.mData = mData;
    }

    private Activity getmAct() {
        return mAct;
    }

    private void setmAct(Activity mAct) {
        this.mAct = mAct;
    }

    private int getmRetry() {
        return mRetry;
    }

    private void setmRetry(int mRetry) {
        this.mRetry = mRetry;
    }

    private int getmReSignin() {
        return mReSignin;
    }

    private void setmReSignin(int mReSignin) {
        this.mReSignin = mReSignin;
    }

    @Override
    public String getBodyContentType() {
        String contentType = "";
        if (mHeaders != null) {
            if (mHeaders.containsKey(HTTPConstant.HEADER_PARAM_CONTENT_TYPE)) {
                contentType = mHeaders.get(HTTPConstant.HEADER_PARAM_CONTENT_TYPE).toString();
            }
            if (mHeaders.containsKey(HTTPConstant.HEADER_PARAM_CHARSET)) {
                if (!contentType.equals("")) {
                    contentType += "; ";
                }
                contentType += ("charset=" + mHeaders.get(HTTPConstant.HEADER_PARAM_CHARSET).toString());
            }
        } else {
            contentType = super.getBodyContentType();
        }
        return contentType;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers;
        if (mHeaders != null) {
            headers = new HashMap<>();
            for (String key : mHeaders.keySet()) {
                //Function getBodyContentType already handle 2 below header params
                if (key != HTTPConstant.HEADER_PARAM_CONTENT_TYPE && key != HTTPConstant.HEADER_PARAM_CHARSET) {
                    headers.put(key, String.valueOf(mHeaders.get(key)));
                }
            }
        } else {
            headers = super.getHeaders();
        }

        return headers;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        byte[] body = new byte[1024];
        return body;
    }

    @Override
    protected Response<SimpleResponse> parseNetworkResponse(NetworkResponse response) {
        LogUtils.d(TAG, "parseNetworkResponse " + response);
        Response<SimpleResponse> retVal;

        if (response == null) {
            return Response.error(new HTTPRequestError(PBError.ERR_HTTP_SERVER_RETURN_NULL.value()));
        }

        JSONObject jsonObject = new JSONObject();
        try {
            if (response.data == null) { // Return empty data
                SimpleResponse res = createResponseData(jsonObject);
                res.setHttpCode(response.statusCode);
                retVal = Response.success(res, HttpHeaderParser.parseCacheHeaders(response));
                return retVal;
            }

            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            if (!jsonString.isEmpty()) {
                jsonObject = new JSONObject(jsonString);
            }
            LogUtils.d(TAG, "jsonString: " + jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.d(TAG, "Exception e: " + e.toString());
        }

        ErrorResponse errorResponse = getResponseError(jsonObject);
        LogUtils.d(TAG, "errorResponse: " + errorResponse);
        if (errorResponse != null) {
            if (getmResponseListener().getmType() != HTTPResponseListener.ResponseType.USER_SIGNIN && errorResponse.getResult().get(0).getStatus() == PBError.UNAUTHORIZED.value()) {
                HTTPRequestError networkError = new HTTPRequestError(PBError.UNAUTHORIZED.value());
                int numberReSignin = mPreviousReq.getmReSignin();
                LogUtils.d(TAG, "numberReSignin: " + numberReSignin);
                if (numberReSignin < HTTPConstant.HTTP_SIGNIN_MAX_RETRIES) {
//                    resignin(mPreviousReq, networkError);
                } else {
                    //show dialog error and go login screen
                    DialogHandler.getInstance().displayDialog(mAct.getString(R.string.login_error));
                }
            } else {
                return Response.error(new HTTPRequestError(errorResponse.getResult().get(0).getStatus()));
            }
        }

        SimpleResponse res = createResponseData(jsonObject);
        if (res == null) {
            return Response.error(new HTTPRequestError(PBError.ERR_INVALID_DATA.value()));
        } else {
            res.setHttpCode(response.statusCode);
            retVal = Response.success(res, HttpHeaderParser.parseCacheHeaders(response));
            return retVal;
        }
    }

    private ErrorResponse getResponseError(JSONObject jsonObject) {
        Gson mJsonConverter = new GsonBuilder().disableHtmlEscaping().create();
        ErrorResponse errorResponse = mJsonConverter.fromJson(jsonObject.toString(), ErrorResponse.class);
        if (errorResponse == null) {
            return null;
        }
        if (errorResponse.getResult() == null) {
            return null;
        }

        if (errorResponse.getResult().size() <= 0) {
            return null;
        }

        if (errorResponse.getResult().get(0).getStatus() <= 0) {
            return null;
        }

        return errorResponse;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        LogUtils.d(TAG, "parseNetworkError " + volleyError);

        HTTPRequestError networkError;

        if (volleyError instanceof NoConnectionError) {
            String error = volleyError.getMessage();
            if (error.contains("No authentication challenges found")) {
                networkError = new HTTPRequestError(PBError.UNAUTHORIZED.value());
            } else {
                networkError = new HTTPRequestError(PBError.ERR_HTTP_NO_NETWORKCONNECTION.value());
            }
        } else if (volleyError instanceof TimeoutError) {
            networkError = new HTTPRequestError(PBError.REQUEST_TIMEOUT.value());
        } else if (volleyError instanceof NetworkError) {
            networkError = new HTTPRequestError(PBError.ERR_HTTP_NO_NETWORKCONNECTION.value());
        } else {
            if (volleyError.networkResponse == null) {
                networkError = new HTTPRequestError(PBError.ERR_HTTP_SERVER_RETURN_NULL.value());
            } else {
                networkError = new HTTPRequestError(volleyError.networkResponse.statusCode);
            }
        }

        if (networkError != null) {
            if (networkError.getErrorCode() == PBError.UNAUTHORIZED.value()) {
                int numberReSignin = mPreviousReq.getmReSignin();
                LogUtils.d(TAG, "numberReSignin: " + numberReSignin);
                if (numberReSignin < HTTPConstant.HTTP_SIGNIN_MAX_RETRIES) {
//                    resignin(mPreviousReq, networkError);
                } else {
                    //show dialog error and go login screen
                    DialogHandler.getInstance().displayDialog(mAct.getString(R.string.login_error));
                }
            }
        }

        return networkError;
    }

    @Override
    public void deliverError(VolleyError error) {
        LogUtils.d(TAG, "deliverError");
        //TODO: need check API and re-signin if need
        mResponseListener.onErrorResponse(error);
    }

    @Override
    protected void deliverResponse(SimpleResponse response) {
        LogUtils.d(TAG, "deliverResponse");
        mResponseListener.onResponse(response);
    }

    private SimpleResponse createResponseData(JSONObject response) {
        SimpleResponse responseData;
        Gson mJsonConverter = new GsonBuilder().disableHtmlEscaping().create();
        try {
            switch (mResponseListener.getmType()) {
                case USER_SIGNIN:
                    responseData = mJsonConverter.fromJson(response.toString(), UserResponse.class);
                    break;
                default:
                    responseData = mJsonConverter.fromJson(response.toString(), SimpleResponse.class);
                    break;
            }

        } catch (JsonSyntaxException exception) {
            LogUtils.d(TAG, exception.toString());
            responseData = null;
        }

        return responseData;

    }

    private void resendReq(HTTPRequest request) {
        setmRetry(getmRetry() + 1);
        LogUtils.d(TAG, "resendReq HTTP Request retry: " + getmRetry() + " - method: " + request.getmMethod() + " url: " + request.getmURL() + " headers: " + request.getmHeaders() + " params: " + request.getmParams() + " data: " + Arrays.toString(request.getmData()));
        HTTPExecutor.getInstance(getmAct()).send(request);
    }

}
