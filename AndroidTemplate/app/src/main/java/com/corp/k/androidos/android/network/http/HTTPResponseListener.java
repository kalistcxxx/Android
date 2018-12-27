package com.corp.k.androidos.android.network.http;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.corp.k.androidos.android.models.restapi.HTTPRequestError;
import com.corp.k.androidos.android.models.restapi.PBError;
import com.corp.k.androidos.android.models.restapi.SimpleResponse;
import com.corp.k.androidos.android.utils.LogUtils;


public class HTTPResponseListener implements Response.Listener<SimpleResponse>, Response.ErrorListener {
    private String TAG = this.getClass().getSimpleName();
    private ResponseType mType;
    private RequestListener mRequestListener;

    public HTTPResponseListener(ResponseType type, RequestListener listener) {
        this.mType = type;
        this.mRequestListener = listener;
    }

    public ResponseType getmType() {
        return mType;
    }

    public void setmType(ResponseType mType) {
        this.mType = mType;
    }

    public RequestListener getmRequestListener() {
        return mRequestListener;
    }

    public void setmIRequestListener(RequestListener mIRequestListener) {
        this.mRequestListener = mIRequestListener;
    }

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        LogUtils.d(TAG, "onErrorResponse");
        HTTPRequestError httpRequestError = (HTTPRequestError) error;
        if (httpRequestError != null) {
            mRequestListener.onFailed(httpRequestError.getErrorCode(), httpRequestError.getErrorMessage());
        } else {
            mRequestListener.onFailed(PBError.ERR_HTTP_UNKNOWN.value(), PBError.ERR_HTTP_UNKNOWN.name());
        }
    }

    /**
     * Called when a response is received.
     *
     * @param response
     */
    @Override
    public void onResponse(SimpleResponse response) {
        LogUtils.d(TAG, "onResponse");
        mRequestListener.onCompleted(response);
    }


    // Need check re-signin for request with 401 error
    public enum ResponseType {
        USER_SIGNIN,
        GET_PUBLIC_EVENT_INFO,
        GET_CONVENIENT_INFO,
        GET_PERSONAL_INFO,
        GET_MEETING_INFO,
        REGISTER_USER,
        UPDATE_PERSONAL_INFO,
        ERROR_RESPONSE
    }

}
