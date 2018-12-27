package com.corp.k.androidos.android.network.http;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.corp.k.androidos.android.models.restapi.HTTPRequestError;
import com.corp.k.androidos.android.models.restapi.PBError;
import com.corp.k.androidos.android.utils.CommonUtils;
import com.corp.k.androidos.android.utils.LogUtils;


import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;


/**
 * A helper class used for sending and receiving HTTP requests
 * This class should be implemented as singleton
 * <ref>https://developer.android.com/training/volley/requestqueue.html</>
 * <p/>
 */
public class HTTPExecutor {

    private static HTTPExecutor sInstance;
    private final String HTTPTag = getClass().getSimpleName();
    private Activity mContext;
    private RequestQueue mRequestQueue;

    /**
     * Create an instance of {@link HTTPExecutor} class
     *
     * @param context Context to use to create HTTP requests
     */
    private HTTPExecutor(@NonNull Activity context) {

        if (context == null) {
            throw new NullPointerException("Context param is NULL");
        }

        mContext = context;

        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            mRequestQueue = Volley.newRequestQueue(mContext);
        } catch (Exception e) {
            LogUtils.d(HTTPTag, "Exception: " + e.toString());
            mRequestQueue = null;
        }

    }

    /**
     * Singleton instance
     */
    public static HTTPExecutor getInstance(@NonNull Activity context) {
        if (sInstance == null) {
            sInstance = new HTTPExecutor(context);
        }

        return sInstance;
    }


    // APIs=========================================================================================

    /**
     * Send a request to server
     *
     * @param request Request to use to send
     */
    public void send(@NonNull Request request) {
        request.setTag(HTTPTag);
        mRequestQueue.add(request);
    }

    /**
     * Send a request which contains header and body to server
     *
     * @param method   one of values from {@link com.android.volley.Request.Method}
     * @param url      Requested URL
     * @param headers  Header of request
     * @param params   Body of request
     * @param listener Listener to receive request response and  error response
     */
    private void send(int method, String url,
                      Map<String, Object> headers,
                      Map<String, Object> params,
                      byte[] data,
                      HTTPResponseListener listener) {
        if (url == null || mRequestQueue == null) {
            if (listener != null) {
                HTTPRequestError networkError = new HTTPRequestError(PBError.ERR_HTTP_NO_NETWORKCONNECTION.value());
                listener.onErrorResponse(networkError);
            }
            return;
        }

        if (!CommonUtils.isConnectedNetwork(mContext)) {
            LogUtils.d(this.getClass().getSimpleName(), "NO CONNECTION ==> do not make HTTP request");
            HTTPRequestError networkError = new HTTPRequestError(PBError.ERR_HTTP_NO_INTERNET_CONNECTION.value());
            listener.onErrorResponse(networkError);
            return;
        }

        send(new HTTPRequest(mContext, method, url, headers, params, data, listener));
    }

    /**
     * Send a request which contains header and body to server
     *
     * @param method   one of values from {@link com.android.volley.Request.Method}
     * @param url      Requested URL
     * @param headers  Header of request
     * @param params   Body of request
     * @param listener Listener to receive request response and  error response
     */
    private void send(int method, String url,
                      Map<String, Object> headers,
                      Map<String, Object> params,
                      HTTPResponseListener listener) {
        send(method, url, headers, params, null, listener);
    }

    /**
     * Send a request which contains header only to server
     *
     * @param method   one of values from {@link com.android.volley.Request.Method}
     * @param url      Requested URL
     * @param headers  Header of request
     * @param listener Listener to receive request response and  error response
     */
    private void send(int method,
                      String url,
                      Map<String, Object> headers,
                      HTTPResponseListener listener) {
        send(method, url, headers, null, null, listener);
    }

    /**
     * Send a request without header and body to server
     *
     * @param method   one of values from {@link com.android.volley.Request.Method}
     * @param url      Requested URL
     * @param listener Listener to receive request response and  error response
     */
    private void send(int method,
                      String url,
                      HTTPResponseListener listener) {
        send(method, url, null, null, null, listener);
    }

    /**
     * Send a POST request to server
     *
     * @param url      Requested URL
     * @param listener Listener to receive request response and  error response
     */
    public void post(String url,
                     HTTPResponseListener listener) {
        send(Request.Method.POST, url, listener);
    }

    /**
     * Send a POST request to server
     *
     * @param url      Requested URL
     * @param listener Listener to receive request response and  error response
     */
    public void post(String url,
                     Map<String, Object> headers,
                     HTTPResponseListener listener) {
        send(Request.Method.POST, url, headers, listener);
    }

    /**
     * Send a POST request to server
     *
     * @param url      Requested URL
     * @param params   Body of request
     * @param listener Listener to receive request response and  error response
     */
    public void post(String url,
                     Map<String, Object> headers,
                     Map<String, Object> params,
                     HTTPResponseListener listener) {
        send(Request.Method.POST, url, headers, params, listener);
    }

    /**
     * Send a POST request to server
     *
     * @param url      Requested URL
     * @param params   Body of request
     * @param listener Listener to receive request response and  error response
     */
    public void post(String url,
                     Map<String, Object> headers,
                     Map<String, Object> params,
                     byte[] data,
                     HTTPResponseListener listener) {
        send(Request.Method.POST, url, headers, params, data, listener);
    }

    /**
     * Send a GET request to server
     *
     * @param url      Requested url
     * @param listener Listener to receive request response and  error response
     */
    public void get(String url,
                    HTTPResponseListener listener) {
        send(Request.Method.GET, url, listener);
    }

    /**
     * Send a GET request to server
     *
     * @param url      Requested url
     * @param listener Listener to receive request response and  error response
     */
    public void get(String url,
                    Map<String, Object> headers,
                    HTTPResponseListener listener) {
        send(Request.Method.GET, url, headers, listener);
    }

    /**
     * Send a PUT request to server
     *
     * @param url      Requested URL
     * @param listener Listener to receive request response and  error response
     */
    public void put(String url,
                    HTTPResponseListener listener) {
        send(Request.Method.PUT, url, listener);
    }

    /**
     * Send a PUT request to server
     *
     * @param url      Requested URL
     * @param headers  Header to request
     * @param listener Listener to receive request response and  error response
     */
    public void put(String url,
                    Map<String, Object> headers,
                    HTTPResponseListener listener) {
        send(Request.Method.PUT, url, headers, listener);
    }

    /**
     * Send a PUT request to server
     *
     * @param url      Requested URL
     * @param params   Body of request
     * @param listener Listener to receive request response and  error response
     */
    public void put(String url,
                    Map<String, Object> headers,
                    Map<String, Object> params,
                    HTTPResponseListener listener) {
        send(Request.Method.PUT, url, headers, params, null, listener);
    }

    /**
     * Send a DELETE request to server
     *
     * @param url      Requested URL
     * @param listener Listener to receive request response and  error response
     */
    public void delete(String url,
                       HTTPResponseListener listener) {
        send(Request.Method.DELETE, url, listener);
    }

    /**
     * Send a DELETE request to server
     *
     * @param url      Requested URL
     * @param listener Listener to receive request response and  error response
     */
    public void delete(String url,
                       Map<String, Object> headers,
                       HTTPResponseListener listener) {
        send(Request.Method.DELETE, url, headers, listener);
    }

    // =============================================================================================

    public void stopAll() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(HTTPTag);
        }
    }
}
