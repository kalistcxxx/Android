package com.corp.k.androidos.android.models.restapi.user;

import com.corp.k.androidos.android.models.restapi.SimpleResponse;

/**
 */

public class UpdatePersonalInfoResponse extends SimpleResponse {
    private String status;
    private int count;
    private String userID;

    public UpdatePersonalInfoResponse() {
    }

    public UpdatePersonalInfoResponse(String status, int count, String userID) {
        this.status = status;
        this.count = count;
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
