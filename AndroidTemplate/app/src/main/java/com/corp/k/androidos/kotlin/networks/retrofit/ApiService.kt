package com.corp.k.androidos.kotlin.networks.retrofit

import com.corp.k.androidos.kotlin.networks.retrofit.models.PostDetail
import com.corp.k.androidos.kotlin.networks.retrofit.models.PostList
import com.corp.k.androidos.kotlin.networks.retrofit.models.User
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import io.reactivex.Observable

const val BASE_URL = "https://YOUR.BASE.URL/API"
const val LOGIN: String = "login"
const val GET_POST_LIST: String = "posts"
const val GET_POST_DETAIL: String = "posts/{post_id}"
const val ATTACH_POST_DATA: String = "posts/{post_id}/upload"

interface ApiService {


    @POST(LOGIN)
    @FormUrlEncoded
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,
        @Field("username") userName: String,
        @Field("password") password: String
    ): Observable<User>

    @GET(GET_POST_LIST)
    fun getPosts(
        @Header("Authorization") token: String
    ) : Observable<PostList>

    @GET(GET_POST_DETAIL)
    fun getPostDetail(
        @Header("Authorization") token: String,
        @Path("Post_id") postId: Int
    ) : Observable<PostDetail>

    @Multipart
    @POST(ATTACH_POST_DATA)
    fun uploadPostData(
        @Header("Authorization") token: String,
        @Part("post_id") postId: Int,
        @Part file: MultipartBody.Part
    ): Observable<PostDetail>

//    companion object {
//        private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//        private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
//
//        fun create(): ApiService {
//            val retrofit = Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .client(client)
//                .build()
//
//            return retrofit.create(ApiService::class.java)
//        }
//    }
}