package com.corp.k.androidos.android.models.enums;


/**
 * Created by hoangtuan on 12/8/16.
 */
public enum OrderStatus {
    InProgress(1),
    OrderBeingCreated(2),
    AwaitingApproval(3),
    WaitingApprovalContract(4),
    UnderConstruction(5),
    PendingRequests(6),
    AwaitingCompletionApproval(7),
    CompletedApproved(8),
    None(9);

    private int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderStatus fromInteger(int id){
        OrderStatus[] values = OrderStatus.values();
        for (OrderStatus v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }

//    @Override
//    public String toString() {
//        if (value > 0)
//            return CommonUtils.getResourceStringByKey(AppConstants.ORDER_STATUS_PREFIX + value);
//        else
//            return CommonUtils.getResourceStringByKey(AppConstants.ORDER_STATUS_PREFIX + OrderStatus.None.getValue());
//    }

}
