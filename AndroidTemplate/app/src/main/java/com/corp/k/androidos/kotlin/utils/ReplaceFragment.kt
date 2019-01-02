package com.corp.k.androidos.kotlin.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class ReplaceFragment(){
    fun replace(manager: FragmentManager, fragmentClazz: Fragment, layoutId: Int) {
        val backStateName = fragmentClazz.javaClass.name
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.addToBackStack(backStateName)
            transaction.replace(layoutId, fragmentClazz)
            transaction.commit()
        }
    }

    fun replaceWithAnimation(
        manager: FragmentManager,
        fragmentClazz: Fragment,
        layoutId: Int,
        idAnimIn: Int,
        idAnimOut: Int,
        idAnimPopIn: Int,
        idAnimPopout: Int
    ) {
        val backStateName = fragmentClazz.javaClass.name
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(idAnimIn, idAnimOut, idAnimPopIn, idAnimPopout)
            transaction.addToBackStack(backStateName)
            transaction.replace(layoutId, fragmentClazz)
            transaction.commit()
        }
    }

    fun replaceFragment(
        fm: FragmentManager,
        fragment: Fragment,
        bundle: Bundle?,
        popBackStack: Boolean,
        findInStack: Boolean,
        idContainer: Int
    ) {
        val ft = fm.beginTransaction()
        val tag = fragment.javaClass.name
        val parentFragment: Fragment?
        if (findInStack && fm.findFragmentByTag(tag) != null) {
            parentFragment = fm.findFragmentByTag(tag)
        } else {
            parentFragment = fragment
        }
        // if user passes the @bundle in not null, then can be added to the fragment
        if (parentFragment != null) {
            if (bundle != null)
                parentFragment.arguments = bundle
            else
                parentFragment.arguments = null
            // this is for the very first fragment not to be added into the back stack.
            if (popBackStack) {
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            } else {
                ft.addToBackStack(parentFragment.javaClass.name + "")
            }
            ft.replace(idContainer, parentFragment, tag)
            ft.commit()
            fm.executePendingTransactions()
        }
    }
}