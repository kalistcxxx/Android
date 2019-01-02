package com.corp.k.androidos.android.utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by hoangtuan on 9/15/16.
 */
public class ReplaceFragment {

    private static ReplaceFragment instance = new ReplaceFragment();

    private ReplaceFragment(){}

    public static ReplaceFragment getInstance(){
        if(instance==null){
            instance = new ReplaceFragment();
        }
        return instance;
    }

    public void replace(FragmentManager manager, Fragment fragmentClazz, int layoutId) {
        String backStateName = fragmentClazz.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.addToBackStack(backStateName);
            transaction.replace(layoutId, fragmentClazz);
            transaction.commit();
        }
    }

    public void replaceWithAnimation(FragmentManager manager, Fragment fragmentClazz, int layoutId, int idAnimIn, int idAnimOut, int idAnimPopIn, int idAnimPopout) {
        String backStateName = fragmentClazz.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(idAnimIn, idAnimOut, idAnimPopIn, idAnimPopout);
            transaction.addToBackStack(backStateName);
            transaction.replace(layoutId, fragmentClazz);
            transaction.commit();
        }
    }

    public static void replaceFragment(FragmentManager fm, Fragment fragment, @Nullable Bundle bundle, boolean popBackStack, boolean findInStack, int idContainer) {
        FragmentTransaction ft = fm.beginTransaction();
        String tag = fragment.getClass().getName();
        Fragment parentFragment;
        if (findInStack && fm.findFragmentByTag(tag) != null) {
            parentFragment = fm.findFragmentByTag(tag);
        } else {
            parentFragment = fragment;
        }
        // if user passes the @bundle in not null, then can be added to the fragment
        if(parentFragment!=null) {
            if (bundle != null)
                parentFragment.setArguments(bundle);
            else parentFragment.setArguments(null);
            // this is for the very first fragment not to be added into the back stack.
            if (popBackStack) {
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            } else {
                ft.addToBackStack(parentFragment.getClass().getName() + "");
            }
            ft.replace(idContainer, parentFragment, tag);
            ft.commit();
            fm.executePendingTransactions();
        }
    }
}
