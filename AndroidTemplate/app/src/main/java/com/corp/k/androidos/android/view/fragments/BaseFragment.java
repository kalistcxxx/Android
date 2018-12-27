package com.corp.k.androidos.android.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.corp.k.androidos.android.TempApplication;
import com.corp.k.androidos.android.models.interfaces.IOnFragmentActions;
import com.corp.k.androidos.android.models.interfaces.IOnRefreshDataCallback;
import com.corp.k.androidos.android.view.customviews.BackPressImpl;


/**
 * Created by hoangtuan on 9/15/16.
 */
public abstract class BaseFragment extends Fragment implements IOnFragmentActions {

    protected View mView;
    protected Context mContext = TempApplication.getAppContext();
    protected SwipeRefreshLayout refreshLayout;
    protected BackPressImpl backPress = new BackPressImpl(this);

    protected abstract void initView();
    protected abstract void loadDataFromApi();
    public abstract void updateView();

    @Override
    public boolean onBackPressed() {
        return backPress.onBackPressed();
    }

    @Override
    public void onTabChanged() { backPress.onTabChanged(); }

    protected void refreshData(final IOnRefreshDataCallback callback){
        refreshLayout.setRefreshing(true);
//        if(Utils.hasInternet(getActivity())){
//            callback.onHasInternet();
////            Handler handler = new Handler();
////            handler.postDelayed(new Runnable() {
////                @Override
////                public void run() {
////                    callback.onHasInternet();
////                }
////            }, 2000);
//        }else {
//            CommonUtils.showDialogNoInternet(getActivity());
//            refreshLayout.setRefreshing(false);
//        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroyView() {
        if(refreshLayout!=null){
            refreshLayout.setOnRefreshListener(null);
        }
        mView = null;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if(backPress!=null) {
            backPress.clearParentFragment();
        }
        mContext = null;
        super.onDestroy();
        System.gc();
    }
}
