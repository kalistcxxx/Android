package com.corp.k.androidos.kotlin.networks.retrofit

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BaseAPIServiceInterface{

    @GET("{api_url}")
    fun getAndroidVersions(@Path(Constants.PATH_TO_API) String: Any): Observable<List<Any?>>

    @POST("{api_url}")
    @FormUrlEncoded
    fun getAndroidVersions2(@Path(Constants.PATH_TO_API) String: Any): Observable<List<Any?>>

}