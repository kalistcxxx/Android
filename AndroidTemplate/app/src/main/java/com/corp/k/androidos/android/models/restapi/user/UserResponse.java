package com.corp.k.androidos.android.models.restapi.user;


import com.corp.k.androidos.android.models.restapi.SimpleResponse;

/**
 */

public class UserResponse extends SimpleResponse {
    private String userID;
    private String token;

    public UserResponse(String userID, String token) {
        this.userID = userID;
        this.token = token;
    }

    public UserResponse() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
