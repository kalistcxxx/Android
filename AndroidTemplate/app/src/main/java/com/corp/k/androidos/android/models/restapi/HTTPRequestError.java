package com.corp.k.androidos.android.models.restapi;

import com.android.volley.*;

/**
 * Created by 4000039 on 9/24/2015.
 */
public class HTTPRequestError extends VolleyError {
    private PBError mError;

    public HTTPRequestError(int errorCode) {
        super();
        this.mError = PBError.valueOf(errorCode);
    }

    /**
     * @param error An instance of {@link VolleyError}
     */
    public static PBError getError(VolleyError error) {
        PBError err = PBError.ERR_HTTP_UNKNOWN;
        if (error != null) {
            if (error instanceof HTTPRequestError) {
                HTTPRequestError customErr = (HTTPRequestError) error;
                if (customErr.mError != null) {
                    err = customErr.mError;
                }
            } else {
                if (error.networkResponse != null) {
                    err = PBError.valueOf(error.networkResponse.statusCode);
                    if (error instanceof TimeoutError) {
                        err = PBError.REQUEST_TIMEOUT;
                    } else if (error instanceof ServerError) {
                        err = PBError.INTERNAL_SERVER_ERROR;
                    } else if (error instanceof NetworkError || error instanceof NoConnectionError) {
                        err = PBError.ERR_HTTP_NO_NETWORKCONNECTION;
                    }
                }
            }
        }

        return err;
    }

    @Override
    public String getMessage() {
        return mError.getReasonPhrase();
    }

    public String getErrorMessage() {
        return mError.getReasonPhrase();
    }

    public int getErrorCode() {
        return mError.value();
    }

    public PBError getError() {
        return mError;
    }

    public void setError(PBError mError) {
        this.mError = mError;
    }
}
