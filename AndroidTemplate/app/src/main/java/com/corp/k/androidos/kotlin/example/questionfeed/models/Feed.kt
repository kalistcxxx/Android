package com.corp.k.androidos.kotlin.example.questionfeed.models

import java.io.Serializable

/**
 * Created by hoangtuan on 8/15/17.
 */
open class Feed(val ByUser: User, val MainContent: Content) : Serializable{

    fun getUserName() = ByUser.UserName
    fun getUserPosition() = ByUser.JobPosition
    fun getUserImage() = ByUser.ImageUrl

    fun getFullContent() = MainContent.FullContent
    fun getCreatedDate() = MainContent.CreatedDate
    fun getNumberOfLike() = MainContent.NumberOfLikes
    fun getNumberOfComment() = MainContent.NumberOfComments
}