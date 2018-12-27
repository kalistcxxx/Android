package com.corp.k.androidos.kotlin.example.questionfeed.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.TextView
import com.corp.k.androidos.R
import com.corp.k.androidos.kotlin.example.questionfeed.fragments.TreeGraphFragment
import com.corp.k.androidos.kotlin.example.questionfeed.models.Feed
import de.blox.graphview.BaseGraphAdapter
import de.blox.graphview.Graph

class TreeGraphAdapter(context: Context, layoutRes: Int, graph: Graph, val ctmClick: (Any?)->Unit) :
    BaseGraphAdapter<TreeGraphAdapter.TreeNodeHolder>(context, layoutRes, graph) {

    override fun onCreateViewHolder(view: View?): TreeNodeHolder {
        return TreeNodeHolder(view, ctmClick)
    }

    override fun onBindViewHolder(viewHolder: TreeNodeHolder?, data: Any?, position: Int) {
        viewHolder?.bindView(data, position)
    }

    class TreeNodeHolder(itemView : View?, val ctmClick: (Any?) -> Unit){
        private val nameNode = itemView?.findViewById(R.id.nodeTreeName) as TextView
        private val itemMainView = itemView

        fun bindView(data: Any?, pos : Int){
            nameNode.setText(data.toString())
            itemMainView?.setOnClickListener { view: View? ->
                Log.i("Log Node", pos.toString())
                ctmClick(data)
            }
        }
    }

    interface NodeItemClick{
        fun onClick(data : Any?)
    }
}