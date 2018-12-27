package com.corp.k.androidos.kotlin.networks.retrofit

interface RequestListenerInterface{
    fun<T> onSuccess( obj : Class<T>)
}