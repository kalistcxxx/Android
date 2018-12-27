package com.corp.k.androidos.kotlin.example.questionfeed.models

import java.io.Serializable

/**
 * Created by hoangtuan on 8/15/17.
 */
open class User(val UserName: String, val JobPosition: String, val ImageUrl: String) : Serializable {

    var Tag: String
        get() = Tag
        set(value) {
            Tag = value
        }

    fun getUserInformation() = UserName + " ~ " + JobPosition + " ~ " + ImageUrl
}