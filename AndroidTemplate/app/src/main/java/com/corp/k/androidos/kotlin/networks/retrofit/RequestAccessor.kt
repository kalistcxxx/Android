package com.corp.k.androidos.kotlin.networks.retrofit

import android.util.Log
import android.widget.Toast
import com.corp.k.androidos.R.id.txt_search_result
import com.corp.k.androidos.kotlin.networks.retrofit.models.AndroidVersion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main_retrofit.*
import okhttp3.internal.Internal.instance
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RequestAccessor : AccessorInterface{

    val BASE_PROJECT_URL = "https://api.learn2crack.com/"

    val header: String
        get() = "Bearer abcdefghijklmnopqrstuvwxyz"

    private val instanceApiServe by lazy {
        create()
    }

    init {

    }

    private var disposable: Disposable? = null

    private fun create(): BaseAPIServiceInterface {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_PROJECT_URL)
            .build()

        return retrofit.create(BaseAPIServiceInterface::class.java)
    }


    fun<T> doGetWithCustomObject(customClass : Class<T>, url : String, listener: RequestListenerInterface) {

        disposable = instanceApiServe.getAndroidVersions(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { result ->
//                            customClass.
                            val obj :MutableList<Class<T>> = Gson().fromJson(
                                Gson().toJson(result),
                                object : TypeToken<List<Class<T>>>() {

                                }.type)
                            Log.i("Data", "${obj.get(0).toString()} result found")
                            listener.onSuccess(obj.get(0))

                        },
                        { error ->
                            Log.i("Error", error.message)
        //                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                        })
    }

//    private fun beginSearch(searchString: String) {
//        com.corp.k.androidos.kotlin.networks.retrofit.models.disposable = baseApiServe.getAndroidVersions("android/jsonarray/")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->
//                    val obj :MutableList<AndroidVersion> = Gson().fromJson(
//                        Gson().toJson(result),
//                        object : TypeToken<List<AndroidVersion>>() {
//
//                        }.type)
//                    txt_search_result.text = "${obj.get(0).name} result found"
//
//                },
//                { error ->
//                    Log.i("Error", error.message)
//                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
//                }
//            )
//    }

    override fun doGet() {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun doGets() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun doPost() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun doMultiPartPost() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDispose() {
        disposable?.dispose()
    }

}