package com.corp.k.androidos.mvp.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by hoangtuan on 7/18/17.
 */

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameId, fragment);
        fragmentTransaction.commit();
    }

    public static <T> T checkNotNull(T reference){
        if(reference == null){
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, String warningMessage){
        if(reference == null){
            throw new NullPointerException(warningMessage);
        }
        return reference;
    }

    private Map<String, String> checkParams(Map<String, String> map){
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                if(pairs.getValue()==null){
                    map.put(pairs.getKey(), "");
                }
            }
            return map;             
        }
}
