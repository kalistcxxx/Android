package com.corp.k.androidos.kotlin.example.questionfeed.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.corp.k.androidos.R
import com.corp.k.androidos.android.view.customviews.CircleImageView
import com.corp.k.androidos.kotlin.example.questionfeed.custom.loadUrl
import com.corp.k.androidos.kotlin.example.questionfeed.models.Feed
import com.corp.k.androidos.kotlin.example.questionfeed.models.FeedComment


/**
 * Created by hoangtuan on 8/17/17.
 */
open class FeedCommentAdapter(val listObject : MutableList<FeedComment>, val itemClick: (Feed)->Unit)
    : RecyclerView.Adapter<FeedCommentAdapter.FeedCommnetViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedCommnetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qf_item_comment_adapter, parent, false)
        return FeedCommnetViewHolder(
            itemView = view,
            itemClick = itemClick
        )
    }

    override fun onBindViewHolder(holder: FeedCommnetViewHolder, position: Int) {
        holder.bindView(listObject[position])
    }

    override fun getItemCount() = this.listObject.count()

    class FeedCommnetViewHolder(itemView: View, val itemClick: (Feed) -> Unit) : RecyclerView.ViewHolder(itemView){
        private val txtUserName = itemView.findViewById(R.id.txtUserName) as TextView
        private val txtJobPosition = itemView.findViewById(R.id.txtJobPosition) as TextView
        private val txtCreatedDate = itemView.findViewById(R.id.txtCreatedDate) as TextView
        private val txtFullContent = itemView.findViewById(R.id.txtFullContent) as TextView
        private val imgThumbnail = itemView.findViewById(R.id.imgThumbnail) as CircleImageView
//        private val mainCardView = itemView.findViewById(R.id.mainCardView) as CardView

        fun bindView(obj: FeedComment) {
            imgThumbnail.loadUrl("https://cdn0.iconfinder.com/data/icons/user-pictures/100/matureman1-512.png")
            txtUserName.text = obj.getUserName()
            txtJobPosition.text = obj.getUserJobPosition()
            txtCreatedDate.text = obj.getCreatedDate()
            txtFullContent.text = obj.getCommentContent()
        }
    }
}