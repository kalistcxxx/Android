package com.corp.k.androidos.android.models.restapi;

import org.json.JSONObject;

/**
 *
 */
public class SimpleResponse {
    private int retCode;

    /**
     * Parsing response
     */
    public void parse(JSONObject jsonObject) {
        if (jsonObject == null) {
            throw new NullPointerException("Parsed json object is NULL");
        }
    }

    public int getRetCode() {
        return retCode;
    }

    public void setHttpCode(int code) {
        this.retCode = code;
    }
}
