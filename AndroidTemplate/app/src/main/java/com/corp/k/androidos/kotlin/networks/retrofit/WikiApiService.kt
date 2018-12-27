package com.corp.k.androidos.kotlin.networks.retrofit

import com.corp.k.androidos.kotlin.networks.retrofit.models.Models
import com.corp.k.androidos.kotlin.networks.retrofit.models.PostTest
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST




interface WikiApiService {

    @GET("api get")
    fun hitCountCheck(@Query("action") action: String,
                       @Query("format") format: String,
                       @Query("list") list: String,
                       @Query("srsearch") srsearch: String): Observable<Models.Result>

    @POST("api post")
    fun postCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String): Observable<Models.Result>

    @POST("/posts")
    @FormUrlEncoded
    fun savePost(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("userId") userId: Long
    ): Call<PostTest>

    @POST("/posts")
    @FormUrlEncoded
    fun savePost(@Body post: PostTest): Call<PostTest>

    companion object {
        fun create(): WikiApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(POST_BASE_URL)
                    .build()

            return retrofit.create(WikiApiService::class.java)
        }

        const val WIKI_BASE_URL : String = "https://en.wikipedia.org/w/"
        const val POST_BASE_URL : String = "http://jsonplaceholder.typicode.com/"
    }

}