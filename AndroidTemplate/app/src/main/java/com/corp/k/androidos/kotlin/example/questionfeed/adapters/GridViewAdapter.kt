package com.corp.k.androidos.kotlin.example.questionfeed.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.corp.k.androidos.R
import com.corp.k.androidos.kotlin.example.questionfeed.custom.loadUrl
import com.corp.k.androidos.kotlin.example.questionfeed.models.Item

open class GridViewAdapter(var listItem : MutableList<Item>, val ctmClick: (Item, View)->Unit) : RecyclerView.Adapter<GridViewAdapter.GridViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qf_grid_item, parent, false)
        val height = parent.measuredHeight / 4
        view.findViewById<ImageView>(R.id.imageview_item).setMinimumHeight(height)
        return GridViewHolder(
            itemView = view,
            ctmClick = ctmClick
        )
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bindView(listItem[position])
    }

    class GridViewHolder(itemView: View, val ctmClick: (Item, View) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val imgView = itemView.findViewById(R.id.imageview_item) as ImageView
        private val txtImageName = itemView.findViewById(R.id.textview_name) as TextView

        fun bindView( obj : Item){
            imgView.setOnClickListener { view -> ctmClick(obj, itemView) }
            imgView.loadUrl(obj.getThumbnailUrl())
            txtImageName.text = obj.mName
        }
    }

}