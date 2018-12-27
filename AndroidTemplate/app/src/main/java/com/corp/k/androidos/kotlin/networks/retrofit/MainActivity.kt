package com.corp.k.androidos.kotlin.networks.retrofit

import android.databinding.DataBindingUtil.setContentView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CpuUsageInfo
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.corp.k.androidos.R
import com.corp.k.androidos.R.id.btn_search
import com.corp.k.androidos.R.id.edit_search
import com.corp.k.androidos.kotlin.networks.retrofit.models.AndroidVersion
import com.corp.k.androidos.kotlin.networks.retrofit.models.CustomObservable
import com.corp.k.androidos.kotlin.networks.retrofit.models.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main_retrofit.*
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import org.reactivestreams.Subscriber




class MainActivity : AppCompatActivity(){

    var obj : RequestAccessor = RequestAccessor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_retrofit)

//        btn_search.setOnClickListener {
//            if (edit_search.text.toString().isNotEmpty()) {
//                beginSearch(edit_search.text.toString())
//            }
//        }

        val listItem = mutableListOf<CustomObservable>()
        listItem.add(CustomObservable())
        listItem.add(CustomObservable())
        listItem.add(CustomObservable())
        listItem.add(CustomObservable())
        listItem.add(CustomObservable())

        listItem.toObservable().subscribeBy(
            onComplete = {},
            onError = {},
            onNext = {}
        )
    }

    private fun beginSearch(searchString: String) {
        val listener: RequestListenerInterface = object : RequestListenerInterface {
            override fun <T> onSuccess(obj: Class<T>) {
                runOnUiThread({
                    val txtChange = findViewById(R.id.txt_search_result) as TextView
                    txtChange.text = (obj as AndroidVersion).name
                    Log.i("Go here", "Go here")
                })
            }

        }
        obj.doGetWithCustomObject(AndroidVersion::class.java, "android/jsonarray/", listener)
    }


    override fun onPause() {
        super.onPause()
        obj.onDispose()
    }
}
