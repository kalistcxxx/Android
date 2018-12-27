package com.corp.k.androidos.kotlin.example.questionfeed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.corp.k.androidos.R
import com.corp.k.androidos.android.view.customviews.CircleImageView
import com.corp.k.androidos.kotlin.example.questionfeed.adapters.FeedCommentAdapter
import com.corp.k.androidos.kotlin.example.questionfeed.custom.loadUrl
import com.corp.k.androidos.kotlin.example.questionfeed.models.Content
import com.corp.k.androidos.kotlin.example.questionfeed.models.Feed
import com.corp.k.androidos.kotlin.example.questionfeed.models.FeedComment
import com.corp.k.androidos.kotlin.example.questionfeed.models.User
import java.io.Serializable

/**
 * Created by hoangtuan on 8/16/17.
 */
open class DetailFeedFragment : Fragment() {

    companion object {
        fun newInstance(feed: Feed?): DetailFeedFragment {
            val detailFeedFragment = DetailFeedFragment()
            val bundle = Bundle();
            bundle.putSerializable("FeedItem", feed as Serializable)
            detailFeedFragment.arguments = bundle
            return detailFeedFragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(savedInstanceState!=null){

        }
        val view = inflater?.inflate(R.layout.qf_detail_feed_fragment, container, false)
        initView(view)
        return view
    }

    fun initView(view: View?){
        if(this.arguments!=null){
            val mainFeed = arguments?.getSerializable("FeedItem") as Feed
            val txtUserName = view?.findViewById(R.id.txtUserName) as TextView
            val txtJobPosition = view.findViewById(R.id.txtJobPosition) as TextView
            val txtCreatedDate = view.findViewById(R.id.txtCreatedDate) as TextView
            val txtFullContent = view.findViewById(R.id.txtFullContent) as TextView
            val txtNumberLike = view.findViewById(R.id.txtNumberLike) as TextView
            val txtNumberComt = view.findViewById(R.id.txtNumberComt) as TextView
            val imgThumbnail = view.findViewById(R.id.imgThumbnail) as CircleImageView
            val listComment = view.findViewById(R.id.listComment) as RecyclerView

            imgThumbnail.loadUrl("https://cdn0.iconfinder.com/data/icons/user-pictures/100/matureman1-512.png")
            txtUserName.text = mainFeed.getUserName()
            txtCreatedDate.text = mainFeed.getCreatedDate()
            txtFullContent.text = mainFeed.getFullContent()
            txtNumberLike.text = mainFeed.getNumberOfLike().toString()
            txtNumberComt.text = mainFeed.getNumberOfComment().toString() + " Comments"

            listComment.layoutManager = LinearLayoutManager(activity)
            listComment.isNestedScrollingEnabled = false
            val listItem: MutableList<FeedComment> = mutableListOf<FeedComment>()
            listItem.add(
                FeedComment(
                    User(
                        "Quinn Arnfinnr",
                        "Android Devveloper",
                        "3"
                    ),
                    mutableListOf<FeedComment>(),
                    Content(
                        "I am new to using Firebase storage and facing trouble displaying images in RecyclerView. I have used FirebaseRecyclerAdapter to display my data. The data and image URL is stored in Firebase database. The issue is that only the image is not being displayed in RecyclerView.\n" +
                                "\n" +
                                "Any help you can provide me to get this to work would be great. Thanks.",
                        "8:30 20/08/2017, AM",
                        9,
                        15
                    )
                )
            )
            listItem.add(
                FeedComment(
                    User(
                        "Charles Leonid",
                        "Android Devveloper",
                        "1"
                    ),
                    mutableListOf<FeedComment>(),
                    Content(
                        "Hi! I am experiencing troubles using Glide to load images from web with Kotlin.\n" +
                                "glide:3.7.0\n" +
                                "No extra libraries\n" +
                                "Sony Xpreia Z2, Android 6.0\n" +
                                "\n" +
                                "I use Glide as it shown in most basic guides, except that I use Kotlin, not Java:",
                        "8:30 20/08/2017, AM",
                        30,
                        50
                    )
                )
            )
            listItem.add(
                FeedComment(
                    User(
                        "Otieno Dubhghall",
                        "Android Devveloper",
                        "6"
                    ),
                    mutableListOf<FeedComment>(),
                    Content(
                        "Try https://github.com/bumptech/glide/wiki/Debugging-and-Error-Handling#requestlistener\n" +
                                "\n" +
                                "Tip: don't overuse the elvis operator: there's no point in null-safe access when you know it won't ever be null. It's useful when you would otherwise have to do a lot of null checks, which is not the case with ViewHolders.",
                        "8:30 20/08/2017, AM",
                        10,
                        8
                    )
                )
            )
            listItem.add(
                FeedComment(
                    User(
                        "Bima Kreios",
                        "Android Devveloper",
                        "4"
                    ),
                    mutableListOf<FeedComment>(),
                    Content(
                        "HI,\n" +
                                "I'm trying to load list of images from server in to a recycler view, some images are not loading. but when I try to load it in an ImageView that is not in a RecyclerView it load's perfectly. one effected image I uploaded here.",
                        "8:30 20/08/2017, AM",
                        3,
                        1
                    )
                )
            )
            listItem.add(
                FeedComment(
                    User(
                        "Tafari Völundr",
                        "Project Leader",
                        "9"
                    ),
                    mutableListOf<FeedComment>(),
                    Content(
                        "I have a similar problem when using .fit() image doesn't loaded into ImageView...\n" +
                                "When I using only .load() and .into() image is showing.\n" +
                                "mPicasso.load(file).into(holder.imageView) - works\n" +
                                "mPicasso.load(file).fit().centerInside().into(holder.imageView) - dosn't work\n" +
                                "\n" +
                                "Edit: resize() also works", "8:30 20/08/2017, AM", 15, 7
                    )
                )
            )
            listItem.add(
                FeedComment(
                    User(
                        "Ronald Tate",
                        "Mobile Developer",
                        "7"
                    ),
                    mutableListOf<FeedComment>(),
                    Content(
                        "I’ve been talking a lot about Kotlin in this blog, but now that Google is also talking about Kotlin, and that Kotlin 1.0 RC has been released, there’s no doubt that Kotlin is much more than just an alternative for Android. Kotlin is here to stay, and I recommend you to start learning about it.",
                        "8:30 20/08/2017, AM",
                        20,
                        2
                    )
                )
            )
            val feedComtAdapter =
                FeedCommentAdapter(listItem) { itemObject ->
                    //
                }
            listComment.adapter = feedComtAdapter
        }
    }
}