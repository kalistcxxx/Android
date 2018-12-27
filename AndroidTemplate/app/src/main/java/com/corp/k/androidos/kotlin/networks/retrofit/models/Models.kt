package com.corp.k.androidos.kotlin.networks.retrofit.models

object Models {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}
