package com.corp.k.androidos.android.models.restapi;

import java.util.List;

/**
 */

public class ErrorResponse extends SimpleResponse {
    private List<HTTPMessage> response;

    public List<HTTPMessage> getResult() {
        return response;
    }

    public void setResult(List<HTTPMessage> response) {
        this.response = response;
    }
}
