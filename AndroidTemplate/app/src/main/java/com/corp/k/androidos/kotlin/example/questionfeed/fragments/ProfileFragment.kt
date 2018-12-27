package com.corp.k.androidos.kotlin.example.questionfeed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.corp.k.androidos.R

open class ProfileFragment : Fragment(){

    companion object {
        fun onNewInstance(params : Bundle?) : ProfileFragment {
            val fragment = ProfileFragment()
            fragment.arguments = params;
            return fragment;
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fgm_profile, null)

        return view
    }
}