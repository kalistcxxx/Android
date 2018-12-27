package com.corp.k.androidos.databinding

import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.corp.k.androidos.R

open class TestActivty : AppCompatActivity(){

    private var binding : ActTestBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.act_test)
        binding = DataBindingUtil.setContentView(this, R.layout.act_test)
        binding!!.user = UserBinding("Test", "User")

        binding!!.mMain = this

        binding!!.listBindingItem.layoutManager = LinearLayoutManager(this)
        binding!!.listBindingItem.adapter = BindingAdapter()
    }





    //--------------------------------------------------------------------------------------------
    private class AsynctaskTest(val binding: ActTestBinding) :  AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            var count = 1;
            while (true){
                count++
                binding.user = UserBinding("Test" + count, "User" + count)
                Thread.sleep(1000)
                Log.i("Async call", "here")
                break;
            }
            return null
        }

        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

        }
    }

    open fun doSomeWork(){
        val thread = Thread(Runnable {
            var count = 1;
            while (true){
                count++
                binding!!.user = UserBinding("Test" + count, "User" + count)
                Thread.sleep(1000)
                Log.i("Thread call", "here")
            }
        });
        thread.start()

        val asyn: AsynctaskTest =
            AsynctaskTest(binding!!)
        asyn.execute()
    }
}