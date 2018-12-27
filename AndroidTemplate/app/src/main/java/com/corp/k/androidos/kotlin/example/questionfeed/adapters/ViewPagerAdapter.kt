package com.corp.k.androidos.kotlin.example.questionfeed.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.corp.k.androidos.kotlin.example.questionfeed.fragments.GridViewFragment
import com.corp.k.androidos.kotlin.example.questionfeed.fragments.ListViewFragment
import com.corp.k.androidos.kotlin.example.questionfeed.fragments.TreeGraphFragment

open class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm){

    private var COUNT = 3;

    override fun getItem(p0: Int): Fragment {
        if(p0 == 1) return GridViewFragment.onNewInstance(null)
        else if(p0 == 2) return TreeGraphFragment.onNewInstance(null)
        return ListViewFragment.onNewInstance(null);
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab " + (position + 1)
    }
}