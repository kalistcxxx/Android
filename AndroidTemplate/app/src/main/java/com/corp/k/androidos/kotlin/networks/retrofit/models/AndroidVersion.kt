package com.corp.k.androidos.kotlin.networks.retrofit.models

import com.google.gson.annotations.SerializedName

data class AndroidVersion(
    @SerializedName("name")
    val name : String,
    @SerializedName("ver")
    val ver : String,
    @SerializedName("api")
    val api : String)