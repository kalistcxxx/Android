package com.corp.k.androidos.android.network.http.volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.corp.k.androidos.android.utils.Constants;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hoangtuan on 8/23/17.
 */
public class CustomJsonRequest extends StringRequest {

    private Map<String, Object> _params;

    public CustomJsonRequest(int method, Map<String, Object> params, final String url, final IResponseStringCallback callback) {
        super(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response.replace(",\"ErrorList\":[]", ""));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String responseData = error.getMessage() == null ? "" : error.getMessage();
                Log.i("Error Call:", url + " ~ " + responseData + " ~ ");
                callback.onError(responseData);

            }
        });
        _params = params;
        setRetryPolicy(new DefaultRetryPolicy(Constants.VOLLEY_TIMEOUT, Constants.VOLLEY_MAX_NUM_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

//    public CustomJsonRequest(boolean logout, int method,final String url, final IResponseStringCallback callback) {
//        super(method, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                callback.onSuccess(response.replace(",\"ErrorList\":[]", ""));
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                String responseData = error.getMessage() == null ? "" : error.getMessage();
//                Log.i("Error Call:", url + " ~ " + responseData);
//                callback.onError(responseData);
//            }
//        });
//        setRetryPolicy(new DefaultRetryPolicy(AppConstants.VOLLEY_TIMEOUT, AppConstants.VOLLEY_MAX_NUM_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//    }


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
//        headers.put("Content-Type", "application/json");
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
    public String getBodyContentType()
    {
        return "application/json";
    }
    @Override
    public byte[] getBody() throws AuthFailureError {
        String jsonBody = new Gson().toJson(_params);
        return jsonBody.getBytes();
    }


    public interface IResponseStringCallback {
        void onSuccess(String response);
        void onError(String errors);
    }
}
