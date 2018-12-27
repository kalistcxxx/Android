package com.corp.k.androidos.android.view.customviews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.corp.k.androidos.android.models.interfaces.IOnFragmentActions;
import com.corp.k.androidos.android.view.fragments.BaseFragment;


/**
 * Created by hoangtuan on 12/7/16.
 */
public class BackPressImpl implements IOnFragmentActions {
    private Fragment parentFragment;

    public BackPressImpl(Fragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @Override
    public boolean onBackPressed() {
        if (parentFragment == null) return false;
        int childCount = parentFragment.getChildFragmentManager().getBackStackEntryCount();
        if (childCount == 0) {
            return false;

        } else {
            FragmentManager childFragmentManager = parentFragment.getChildFragmentManager();
            IOnFragmentActions childFragment = (IOnFragmentActions) childFragmentManager.getFragments().get(0);
            if(childFragment!=null) {
                if (!childFragment.onBackPressed()) {
                    childFragmentManager.popBackStackImmediate();
                    ((BaseFragment) parentFragment).updateView();
                }
            }
            return true;
        }
    }

    @Override
    public void onTabChanged() {
        if (parentFragment == null) return;
        int childCount = parentFragment.getChildFragmentManager().getBackStackEntryCount();
        if (childCount == 0) {
            ((BaseFragment)parentFragment).updateView();
        } else {
            FragmentManager childFragmentManager = parentFragment.getChildFragmentManager();
            IOnFragmentActions childFragment = (IOnFragmentActions) childFragmentManager.getFragments().get(3);
            ((BaseFragment)childFragment).updateView();
        }
    }


    public void clearParentFragment() {
        this.parentFragment = null;
    }
}
