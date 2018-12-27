package com.corp.k.androidos.android.models.restapi;

/**
 * Created by prdcv172 on 11/9/17.
 */

public class HTTPMessage {
    private int status;
    private String message;

    public HTTPMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
