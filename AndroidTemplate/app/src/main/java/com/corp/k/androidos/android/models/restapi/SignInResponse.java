package com.corp.k.androidos.android.models.restapi;

class SignInResponse extends SimpleResponse {
    private String AccessToken;
    private String AuthorizedAt;

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        this.AccessToken = accessToken;
    }

    public String getAuthorizedAt() {
        return AuthorizedAt;
    }

    public void setAuthorizedAt(String authorizedAt) {
        this.AuthorizedAt = authorizedAt;
    }
}
