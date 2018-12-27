package com.corp.k.androidos.android.network.http;

import com.corp.k.androidos.android.utils.LogUtils;
import com.corp.k.androidos.android.view.dialogs.DialogHandler;

/**
 * Callback interface for delivering request responses
 */
public class RequestListener<T> {
    private final String TAG = getClass().getSimpleName();
    private HTTPRequestOption mOptional;
//    private OnResponseListener onResponseListener  = null;

    RequestListener() {
        this.mOptional = new HTTPRequestOption();
        if (mOptional.isShowLoading()) {
            DialogHandler.getInstance().displayWaitDialog();
        }
    }

    public RequestListener(HTTPRequestOption optional) {
        this.mOptional = optional;
        if (optional == null) {
            this.mOptional = new HTTPRequestOption();
        }
        if (mOptional.isShowLoading()) {
            DialogHandler.getInstance().displayWaitDialog();
        }
    }

//    public void setOnResponseListener(OnResponseListener onRespons){
//        this.onResponseListener = onRespons;
//    }


    public void onCompleted(T data) {
        LogUtils.d(TAG, "onCompleted");
//        if(onResponseListener!=null){
//            onResponseListener.onSuccess();
//        }
        if (mOptional.isShowLoading()) {
            DialogHandler.getInstance().dismissDialog(DialogHandler.ID_WAIT_DIALOG);
        }
    }

    public void onFailed(int errorCode, String error) {
        LogUtils.d(TAG, "onFailed");
        if (mOptional.isShowLoading()) {
            DialogHandler.getInstance().dismissDialog(DialogHandler.ID_WAIT_DIALOG);
        }
        if (mOptional.isShowError()) {
            LogUtils.d(TAG, "mOptional.isShowError errorCode " + errorCode + " error " + error);
            DialogHandler.getInstance().displayMessage(errorCode, error, mOptional.getFeatureID());
        }
    }

    public HTTPRequestOption getOptional() {
        if (mOptional == null) {
            mOptional = new HTTPRequestOption();
        }
        return mOptional;
    }

//    public interface OnResponseListener{
//        public void onSuccess();
//        public void onFailed();
//    }

}
