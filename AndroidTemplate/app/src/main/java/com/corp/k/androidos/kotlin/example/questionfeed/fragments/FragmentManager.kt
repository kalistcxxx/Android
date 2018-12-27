package com.corp.k.androidos.kotlin.example.questionfeed.fragments

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import com.corp.k.androidos.kotlin.example.questionfeed.adapters.GridViewAdapter


fun addFragment( manager: FragmentManager, fragment : Fragment, frameId : Int){
    val fragmentTransaction = manager.beginTransaction()
    fragmentTransaction.add(frameId, fragment)
    fragmentTransaction.commit()
}

fun repalceFragment( manager: FragmentManager, fragment : Fragment, frameId : Int){
    val fragmentTransaction = manager.beginTransaction()
    fragmentTransaction.replace(frameId, fragment)
    fragmentTransaction.commit()
}

inline fun FragmentManager.inTransaction(func : FragmentTransaction.() -> Unit){
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

inline fun GridViewAdapter.doSomeThing(func: GridViewAdapter.() -> Unit){
    val count = getItemCount()
    Log.i("KEY_TAG", count.toString())
}

interface Key : Parcelable{
    fun newFragment()
    fun getKeyFragment() : String
}

abstract class BaseKey : Key {
    val KEY_TAG = "KEY_TAG"
    override fun newFragment() {
        val fragment : Fragment = createFragment()
        var bundle = fragment.arguments
        if (bundle == null) {
            bundle = Bundle()
        }
        bundle.putParcelable(KEY_TAG, this)
        fragment.arguments = bundle
    }

    override fun getKeyFragment(): String {
        return toString()
    }

    abstract fun createFragment() : Fragment
}

open class StateChange{
    fun getPreviousState(){

    }

    fun getNewState(){

    }
}

//open class FragmentStateChanger(val fragmentManager: FragmentManager, val containerId : Int){
//    fun handleStateChange(stateChange: StateChange) {
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.disallowAddToBackStack()
//
//        // here you could animate based on direction
//        val previousState = stateChange.getPreviousState()
//        val newState = stateChange.getNewState()
//        for (oldKey in previousState) {
//            val fragment = fragmentManager.findFragmentByTag(oldKey.getFragmentTag())
//            if (fragment != null) {
//                if (!newState.contains(oldKey)) {
//                    fragmentTransaction.remove(fragment)
//                } else if (!fragment.isDetached) {
//                    fragmentTransaction.detach(fragment)
//                }
//            }
//        }
//        for (newKey in newState) {
//            var fragment = fragmentManager.findFragmentByTag(newKey.getFragmentTag())
//            if (newKey == stateChange.topNewState()) {
//                if (fragment != null) {
//                    if (fragment.isDetached) {
//                        fragmentTransaction.attach(fragment)
//                    }
//                } else {
//                    fragment = newKey.createFragment()
//                    fragmentTransaction.add(containerId, fragment!!, newKey.getFragmentTag())
//                }
//            } else {
//                if (fragment != null && !fragment.isDetached) {
//                    fragmentTransaction.detach(fragment)
//                }
//            }
//        }
//        fragmentTransaction.commitNow() // or commitAllowingStateLoss();
//    }
//}
