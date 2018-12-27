package com.corp.k.androidos.kotlin.example.questionfeed.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.corp.k.androidos.R
import com.corp.k.androidos.kotlin.example.questionfeed.*
import com.corp.k.androidos.kotlin.example.questionfeed.adapters.FeedAdapter
import com.corp.k.androidos.kotlin.example.questionfeed.models.Content
import com.corp.k.androidos.kotlin.example.questionfeed.models.Feed
import com.corp.k.androidos.kotlin.example.questionfeed.models.User

open class ListViewFragment : Fragment(){

    companion object {
        fun onNewInstance(params : Bundle?) : ListViewFragment {
            val fragment = ListViewFragment()
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
        val view = inflater.inflate(R.layout.fgm_list_feed_item, null)
        val recyclerView = view?.findViewById(R.id.listFeed) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(container?.context)

        val listItem: MutableList<Feed> = mutableListOf<Feed>()
        listItem.add(
            Feed(
                User(
                    "Quinn Arnfinnr",
                    "Android Developer",
                    "3"
                ),
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
            Feed(
                User("Charles Leonid", "Java Developer", "1"),
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
            Feed(
                User("Otieno Dubhghall", "PHP Developer", "6"),
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
            Feed(
                User("Bima Kreios", "Project Manager", "4"),
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
            Feed(
                User("Tafari Völundr", "Q&A", "9"),
                Content(
                    "I have a similar problem when using .fit() image doesn't loaded into ImageView...\n" +
                            "When I using only .load() and .into() image is showing.\n" +
                            "mPicasso.load(file).into(holder.imageView) - works\n" +
                            "mPicasso.load(file).fit().centerInside().into(holder.imageView) - doesn't work\n" +
                            "\n" +
                            "Edit: resize() also works", "8:30 20/08/2017, AM", 15, 7
                )
            )
        )
        listItem.add(
            Feed(
                User(
                    "Ronald Tate",
                    "Tester",
                    "7"
                ),
                Content(
                    "I’ve been talking a lot about Kotlin in this blog, but now that Google is also talking about Kotlin, and that Kotlin 1.0 RC has been released, there’s no doubt that Kotlin is much more than just an alternative for Android. Kotlin is here to stay, and I recommend you to start learning about it.",
                    "8:30 20/08/2017, AM",
                    20,
                    2
                )
            )
        )
        val mainAdapter = FeedAdapter(listItem,
            { itemObjectClick ->
                (activity as HomeActivity).goToDetail(itemObjectClick)
            })
        { bottomSheetClick ->
            //            when (bottomSheetBehavior.state){
//                BottomSheetBehavior.STATE_EXPANDED -> {
//                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
//                }
//                BottomSheetBehavior.STATE_HIDDEN -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//                BottomSheetBehavior.STATE_COLLAPSED -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//                else -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
//            }

        }
        recyclerView.setAdapter(mainAdapter)

        val refreshLayout = view.findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        refreshLayout.setOnRefreshListener {
            refreshLayout.isRefreshing = true;
            listItem.add(0, Feed(
                User(
                    "Quinn Arnfinnr",
                    "Android Developer",
                    "3"
                ),
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
            mainAdapter.renewListObject(listItem)
            recyclerView.post{
                recyclerView.smoothScrollToPosition(0)
            }
            refreshLayout.isRefreshing = false
        }
        return view
    }
}