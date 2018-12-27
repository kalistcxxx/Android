package com.corp.k.androidos.kotlin.example.questionfeed.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.corp.k.androidos.R
import com.corp.k.androidos.kotlin.example.questionfeed.adapters.GridViewAdapter
import com.corp.k.androidos.kotlin.example.questionfeed.HomeActivity
import com.corp.k.androidos.kotlin.example.questionfeed.models.Item

open class GridViewFragment : Fragment(){

    var ITEMS = mutableListOf(
        Item(
            "Flying in the Light",
            "Romain Guy",
            "flying_in_the_light.jpg"
        ),
        Item("Caterpillar", "Romain Guy", "caterpillar.jpg"),
        Item(
            "Look Me in the Eye",
            "Romain Guy",
            "look_me_in_the_eye.jpg"
        ),
        Item("Flamingo", "Romain Guy", "flamingo.jpg"),
        Item("Rainbow", "Romain Guy", "rainbow.jpg"),
        Item("Over there", "Romain Guy", "over_there.jpg"),
        Item("Jelly Fish 2", "Romain Guy", "jelly_fish_2.jpg"),
        Item(
            "Lone Pine Sunset",
            "Romain Guy",
            "lone_pine_sunset.jpg"
        )
    )

    companion object {
        fun onNewInstance(params : Bundle?) : GridViewFragment {
            val fragment = GridViewFragment()
            fragment.arguments = params;
            return fragment;
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainView = inflater.inflate(R.layout.fgm_grid_view, null);
        val recyclerView = mainView?.findViewById(R.id.gridView) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(container?.context, 2)
        recyclerView.adapter = GridViewAdapter(ITEMS,
            { itemObjectClick, view: View ->
                (activity as HomeActivity).goToDetailGrid(
                    itemObjectClick,
                    view
                )
            })
        return mainView
    }

}
