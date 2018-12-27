package com.corp.k.androidos.android.models.restapi.info;

/**
 * Created by prdcv172 on 8/20/18.
 */

public class StoreInfo {
    private String store;
    private String address;

    public StoreInfo() {
    }

    public StoreInfo(String store, String address) {
        this.store = store;
        this.address = address;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
