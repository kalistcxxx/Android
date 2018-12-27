package com.corp.k.androidos.android.network.http;

public class HTTPRequestOption {
    private boolean isShowLoading = true;
    private boolean isShowError = true;
    private boolean isShowSMPConnectionError = true;
    private int mFeatureID = -1;

    public HTTPRequestOption(int featureID) {
        this.mFeatureID = featureID;
    }

    public HTTPRequestOption(boolean isShowError, boolean isShowLoading, int featureID) {
        this.isShowError = isShowError;
        this.isShowLoading = isShowLoading;
        this.mFeatureID = featureID;
    }

    public HTTPRequestOption(boolean isShowError, boolean isShowLoading) {
        this.isShowError = isShowError;
        this.isShowLoading = isShowLoading;
    }

    public HTTPRequestOption() {

    }

    public boolean isShowSMPConnectionError() {
        return isShowSMPConnectionError;
    }

    public void setShowSMPConnectionError(boolean showSMPConnectionError) {
        isShowSMPConnectionError = showSMPConnectionError;
    }

    public boolean isShowError() {
        return isShowError;
    }

    public boolean isShowLoading() {
        return isShowLoading;
    }

    public int getFeatureID() {
        return mFeatureID;
    }

    public void setFeatureID(int mFeatureID) {
        this.mFeatureID = mFeatureID;
    }

    public void setIsShowError(boolean isShowError) {
        this.isShowError = isShowError;
    }

    public void setIsShowLoading(boolean isShowLoading) {
        this.isShowLoading = isShowLoading;
    }
}
