package com.corp.k.androidos.kotlin

import android.app.Application
import android.content.Context

/**
 * Created by hoangtuan on 8/14/17.
 */
open class TempApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: TempApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context = applicationContext()
    }
}