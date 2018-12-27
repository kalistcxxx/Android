package com.corp.k.androidos.kotlin.example.questionfeed.models

import java.io.Serializable

open class Item(val mName : String, val mAuthor :String, val mFileName :String) : Serializable {
    private val LARGE_BASE_URL =
        "http://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/large/"
    private val THUMB_BASE_URL =
        "http://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/thumbs/"

    fun getThumbnailUrl() : String{
        return THUMB_BASE_URL  + mFileName
    }

    fun getPhotiUrl() : String{
        return LARGE_BASE_URL + mFileName
    }

    fun getId(): Int {
        return mName.hashCode() + mFileName.hashCode()
    }
}