package com.corp.k.androidos.android.models.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hoangtuan on 12/2/16.
 */
public enum ResultCode {
    @SerializedName("1")
    Successfull(1),

    @SerializedName("2")
    Failed(2),

    @SerializedName("100")
    AuthenticationError(100),

    @SerializedName("110")
    SessionTimeout(110),

    @SerializedName("200")
    ValidationError(200),

    @SerializedName("400")
    DatabaseError(400),

    @SerializedName("410")
    DatabaseTimeout(410),

    @SerializedName("900")
    SystemError(900);

    private int value;

    ResultCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ResultCode fromInteger(int id){
        ResultCode[] values = ResultCode.values();
        for (ResultCode v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }

//    @Override
//    public String toString() {
//        if (value > 0)
//            return CommonUtils.getResourceStringByKey(AppConstants.HTTP_CODE_PREFIX + value);
//        else
//            return CommonUtils.getResourceStringByKey(AppConstants.HTTP_CODE_PREFIX + ResultCode.Failed.getValue());
//    }

}
