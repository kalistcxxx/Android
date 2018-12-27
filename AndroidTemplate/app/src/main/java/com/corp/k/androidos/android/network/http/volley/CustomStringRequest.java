package com.corp.k.androidos.android.network.http.volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import com.corp.k.androidos.android.utils.Constants;

/**
 * Created by hoangtuan on 10/27/16.
 */
public class CustomStringRequest extends StringRequest {

//    private Map<String, String> _params;

    public CustomStringRequest(int method,final String url, final IResponseStringCallback callback) {
        super(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response.replace(",\"ErrorList\":[]", ""));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String responseData = error.getMessage() == null ? "" : error.getMessage();
                Log.i("Error Call:", url + " ~ " + responseData + " ~ " );
                callback.onError(responseData);

            }
        });
        setRetryPolicy(new DefaultRetryPolicy(Constants.VOLLEY_TIMEOUT, Constants.VOLLEY_MAX_NUM_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public CustomStringRequest(boolean logout, int method,final String url, final IResponseStringCallback callback) {
        super(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response.replace(",\"ErrorList\":[]", ""));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String responseData = error.getMessage() == null ? "" : error.getMessage();
                Log.i("Error Call:", url + " ~ " + responseData);
                callback.onError(responseData);
            }
        });
        setRetryPolicy(new DefaultRetryPolicy(Constants.VOLLEY_TIMEOUT, Constants.VOLLEY_MAX_NUM_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

//    @Override
//    protected Map<String, String> getParams() {
//        return _params;
//    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        // since we don't know which of the two underlying network vehicles
        // will Volley use, we have to handle and store session cookies manually
        //UchiNetApp.getInstance().checkSessionCookie(response.headers);
        return super.parseNetworkResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + "Cookie");
        return headers;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            volleyError = new VolleyError(new String(volleyError.networkResponse.data));
//            onErrorResponse(volleyError);
        }
        return volleyError;
    }

//    @Override
//    public byte[] getBody() throws AuthFailureError {
//        return super.getBody();
//    }


    public interface IResponseStringCallback {
        void onSuccess(String response);
        void onError(String errors);
    }
}

