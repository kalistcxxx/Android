package com.corp.k.androidos.android.models;

/**
 * Created by prdcv170 on 10/13/17.
 */

public class ServerInfo {
    private String mAddress;
    private int mPort;

    public ServerInfo(String address, int port) {
        setmAddress(address);
        setmPort(port);
    }

    public String getmAddress() {
        return mAddress;
    }

    private void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public int getmPort() {
        return mPort;
    }

    private void setmPort(int mPort) {
        this.mPort = mPort;
    }
}
