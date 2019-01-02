package com.corp.k.androidos.kotlin.databases

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

class KTempRealmObject(): RealmObject(), Serializable{

    @PrimaryKey
    private val _id: String? = null

    @SerializedName("UserName")
    private val Name: String? = null
    @SerializedName("UserAge")
    private val Age: Int = 0
}