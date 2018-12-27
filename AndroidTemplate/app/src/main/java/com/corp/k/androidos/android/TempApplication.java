package com.corp.k.androidos.android;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;

public class TempApplication extends Application {
    private String TAG = TempApplication.class.getSimpleName();
    private static Point screenSize;
    private static TempApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    public static TempApplication getInstance(){
        return sInstance;
    }

    public static void setScreenSize(Point size){
        screenSize = size;
    }
    public static Point getScreenSize(){
        return screenSize;
    }
}
