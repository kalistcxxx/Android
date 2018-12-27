package com.corp.k.androidos.kotlin.example.questionfeed.models

/**
 * Created by hoangtuan on 8/17/17.
 */
open class FeedComment(val comtUser: User?, val listSubComment: MutableList<FeedComment>, val mainContent: Content?){

    open fun getUserName() = comtUser?.UserName
    open fun getUserJobPosition() = comtUser?.JobPosition
    open fun getImageAvatar() = comtUser?.ImageUrl
    open fun getCreatedDate() = mainContent?.CreatedDate
    open fun getCommentContent() = mainContent?.FullContent
}