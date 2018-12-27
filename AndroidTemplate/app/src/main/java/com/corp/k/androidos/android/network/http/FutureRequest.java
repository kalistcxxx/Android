package com.corp.k.androidos.android.network.http;

import com.corp.k.androidos.android.models.restapi.PBError;
import com.corp.k.androidos.android.utils.LogUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureRequest<T> extends RequestListener<T> {

    private final Object lock = new Object();
    private PBError mError;
    private boolean mResultReceived = false;
    private T mResult;


    public FutureRequest(HTTPRequestOption optional) {
        super(optional);
    }

    public FutureRequest() {
        super();
    }

    public T get() {
        try {
            return doGet(null);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return doGet(TimeUnit.MILLISECONDS.convert(timeout, unit));
    }

    private T doGet(Long timeoutMs)
            throws InterruptedException, ExecutionException, TimeoutException {
        while (timeoutMs != null && (timeoutMs = timeoutMs - 100) > 0) {
            if (mResultReceived) {
                if (mError != null) {
                    this.onFailed(mError.value(), mError.getReasonPhrase());
                }
                return mResult;
            }
            synchronized (lock) {
                lock.wait(100);
            }
        }

        if (mError != null) {
            this.onFailed(mError.value(), mError.getReasonPhrase());
        }

        if (!mResultReceived) {
            this.onFailed(mError != null ? mError.value() : 0, mError != null ? mError.getReasonPhrase() : "");
        }

        return mResult;
    }

    @Override
    public void onCompleted(T data) {
        LogUtils.d("FutureRequest", "onCompleted");
        super.onCompleted(data);
        mResultReceived = true;
        mResult = data;
        mError = null;
        synchronized (lock) {
            lock.notify();
        }
    }

    @Override
    public void onFailed(int errorCode, String error) {
        LogUtils.d("FutureRequest", "onFailed");
        super.onFailed(errorCode, error);
        mResultReceived = true;
        mError = PBError.valueOf(errorCode);
        mResult = null;
        synchronized (lock) {
            lock.notify();
        }
    }

    public PBError getError() {
        return mError;
    }

    public void setError(PBError mError) {
        this.mError = mError;
    }
}
