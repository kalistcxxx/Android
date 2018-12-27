package com.corp.k.androidos.kotlin.example.questionfeed.models

import java.io.Serializable

/**
 * Created by hoangtuan on 8/15/17.
 */
open class Content(var FullContent: String, var CreatedDate: String, var NumberOfLikes: Int, var NumberOfComments: Int) : Serializable{

//    constructor(fullContent: String, createdDate: String, nol: Int, noc: Int) : super(){
//        this.FullContent = fullContent
//        this.CreatedDate = createdDate
//        this.NumberOfLikes = nol
//        this.NumberOfComments = noc
//    }

//    var FullContent: String
//        get() = FullContent
//        set(value) {
//            //checkNotNull(value)
//            FullContent = checkCharacter(value)
//        }
//
//    var CreatedDate: String
//        get() = CreatedDate
//        set(value) {
//            CreatedDate = value
//        }
//
//    var NumberOfLikes: Int
//        get() = NumberOfLikes
//        set(value){
//            NumberOfLikes = value
//        }
//
//    var NumberOfComments : Int
//        get() = NumberOfComments
//        set(value) {
//            NumberOfComments = value
//        }

    fun checkCharacter(value: String) = value
}