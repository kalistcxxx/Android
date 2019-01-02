package com.corp.k.androidos.android.databases;

import com.google.gson.annotations.SerializedName;
import io.realm.annotations.PrimaryKey;
import io.realm.RealmObject;
import java.io.Serializable;

public class TempRealmObject extends RealmObject implements Serializable {
    @PrimaryKey
    private String _id;

    @SerializedName("UserName")
    private String Name;
    @SerializedName("UserAge")
    private int Age;
}
