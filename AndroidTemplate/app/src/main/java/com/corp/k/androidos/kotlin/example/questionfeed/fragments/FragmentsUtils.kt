package com.corp.k.androidos.kotlin.example.questionfeed.fragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.corp.k.androidos.R



/**
 * Created by hoangtuan on 8/16/17.
 */
open class FragmentsUtils {

    open fun replaceFragment(fragmentManager: FragmentManager, fragmentClazz: Fragment, layoutId : Int){
        val strBackStackName = fragmentClazz.javaClass.simpleName
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.hold, R.anim.hold, R.anim.exit_to_right);
        fragmentTransaction.add(layoutId, fragmentClazz);
        fragmentTransaction.addToBackStack(strBackStackName);
        fragmentTransaction.commit();
    }
}