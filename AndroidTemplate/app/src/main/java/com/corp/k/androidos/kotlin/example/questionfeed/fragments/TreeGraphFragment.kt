package com.corp.k.androidos.kotlin.example.questionfeed.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.corp.k.androidos.R
import com.corp.k.androidos.kotlin.example.questionfeed.adapters.TreeGraphAdapter
import de.blox.graphview.Graph
import de.blox.graphview.GraphView
import de.blox.graphview.Node
import de.blox.graphview.tree.BuchheimWalkerAlgorithm
import de.blox.graphview.tree.BuchheimWalkerConfiguration
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.os.Build
import android.support.design.widget.FloatingActionButton
import android.widget.Toast
import com.corp.k.androidos.kotlin.example.questionfeed.HomeActivity


class TreeGraphFragment : Fragment(), TreeGraphAdapter.NodeItemClick{


    private var nodeCounter = 0

    companion object {
        fun onNewInstance(params : Bundle?) : TreeGraphFragment {
            val fragment = TreeGraphFragment()
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
        val view = inflater.inflate(R.layout.fgm_tree_grap, null)
        val graphView : GraphView = view.findViewById<GraphView>(R.id.tree_graph)
        buildTheTree(graphView)

        val fltBtnAddNew : FloatingActionButton = view.findViewById(R.id.flt_btn_add_node)
        loadAnimation(fltBtnAddNew)
        return view
    }

    private fun buildTheTree(graphView : GraphView){
        val graph  = Graph()
        val node1 = Node(getNodeCount())
        val node2 = Node(getNodeCount())
        val node3 = Node(getNodeCount())
        val node4 = Node(getNodeCount())
        val node5 = Node(getNodeCount())
        val node6 = Node(getNodeCount())
        val node7 = Node(getNodeCount())
        val node8 = Node(getNodeCount())
        val node9 = Node(getNodeCount())
        val node10 = Node(getNodeCount())
        val node11 = Node(getNodeCount())
        val node12 = Node(getNodeCount())

        graph.addEdge(node1, node2)
        graph.addEdge(node1, node3)
        graph.addEdge(node1, node4)
        graph.addEdge(node2, node5)
        graph.addEdge(node2, node6)
        graph.addEdge(node6, node7)
        graph.addEdge(node6, node8)
        graph.addEdge(node4, node9)
        graph.addEdge(node4, node10)
        graph.addEdge(node4, node11)
        graph.addEdge(node11, node12)

        val treeAdapter = TreeGraphAdapter(context!!, R.layout.node_tree, graph,
            { itemObjectClick ->
                Toast.makeText(context, "Check here", Toast.LENGTH_SHORT).show()
            })
        val configuration = BuchheimWalkerConfiguration.Builder()
            .setSiblingSeparation(100)
            .setLevelSeparation(300)
            .setSubtreeSeparation(300)
            .setOrientation(BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM)
            .build()
        treeAdapter.setAlgorithm(BuchheimWalkerAlgorithm(configuration))
        graphView.adapter = treeAdapter
    }

    private fun getNodeCount() : String{
        return "Node" + nodeCounter++
    }

    fun loadAnimation(view: View) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            view.clearAnimation()
            view.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(context, R.anim.show_floating_animation)
            view.startAnimation(animation)
        } else {
            view.visibility = View.VISIBLE
        }
    }

    override fun onClick(data: Any?) {
        Toast.makeText(context, "Check here", Toast.LENGTH_SHORT).show()
    }

}