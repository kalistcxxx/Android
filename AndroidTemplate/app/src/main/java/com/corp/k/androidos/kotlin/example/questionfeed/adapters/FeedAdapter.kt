package com.corp.k.androidos.kotlin.example.questionfeed.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.corp.k.androidos.R
import com.corp.k.androidos.android.view.customviews.CircleImageView
import com.corp.k.androidos.kotlin.TempApplication
import com.corp.k.androidos.kotlin.example.questionfeed.custom.loadUrl
import com.corp.k.androidos.kotlin.example.questionfeed.models.Feed

/**
 * Created by hoangtuan on 8/15/17.
 */
open class FeedAdapter(var listObject : MutableList<Feed>, val ctmClick: (Feed)->Unit, val btnSheetClick: (Any)->Unit):
        RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qf_item_feed_adapter, parent, false)
        return FeedViewHolder(
            itemView = view,
            ctmClick = ctmClick,
            btnSheetClick = btnSheetClick
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bindView(listObject[position])
    }

    override fun getItemCount(): Int {
        return this.listObject.count()
    }

    open fun renewListObject(listObject : MutableList<Feed>){
        this.listObject = listObject
        notifyItemInserted(0)
    }

    class FeedViewHolder(itemView: View, val ctmClick: (Feed) -> Unit, val btnSheetClick: (Any) -> Unit) : RecyclerView.ViewHolder(itemView){
        private val txtUserName = itemView.findViewById(R.id.txtUserName) as TextView
        private val txtJobPosition = itemView.findViewById(R.id.txtJobPosition) as TextView
        private val txtCreatedDate = itemView.findViewById(R.id.txtCreatedDate) as TextView
        private val txtFullContent = itemView.findViewById(R.id.txtFullContent) as TextView
        private val txtNumberLike = itemView.findViewById(R.id.txtNumberLike) as TextView
        private val txtNumberComt = itemView.findViewById(R.id.txtNumberComt) as TextView
        private val imgThumbnail = itemView.findViewById(R.id.imgThumbnail) as CircleImageView
        private val mainCardView = itemView.findViewById(R.id.mainCardView) as CardView
        private val layoutComment = itemView.findViewById(R.id.layoutComment) as LinearLayout
        private val showBottomSheet = itemView.findViewById(R.id.showBottomSheet) as ImageView

        fun bindView(obj: Feed){
            layoutComment.setOnClickListener { view -> ctmClick(obj) }
            showBottomSheet.setOnClickListener{ view -> btnSheetClick(obj)}
            imgThumbnail.loadUrl("https://cdn0.iconfinder.com/data/icons/user-pictures/100/matureman1-512.png")
            txtUserName.text = obj.getUserName()
            txtCreatedDate.text = obj.getCreatedDate()
            if(obj.getFullContent().length>200){
                var fullContent = obj.getFullContent()
                fullContent = fullContent.substring(0, 200);
                val udata = fullContent+  " See more..."
                val content = SpannableString(udata)
                content.setSpan(UnderlineSpan(), 200, udata.length, 0)
                content.setSpan(ForegroundColorSpan(ContextCompat.getColor(TempApplication.applicationContext(), R.color.classify1)), 200, udata.length, 0)
                txtFullContent.text = content
            }else{
                txtFullContent.text = obj.getFullContent()
            }
            txtJobPosition.text = obj.getUserPosition()
            txtNumberLike.text = obj.getNumberOfLike().toString()
            txtNumberComt.text = obj.getNumberOfComment().toString() + " Comments"
        }

    }
}