package com.corp.k.androidos.kotlin.example.questionfeed

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import android.widget.ImageView
import android.widget.TextView
import com.corp.k.androidos.R
import com.corp.k.androidos.kotlin.example.questionfeed.models.Item
import com.squareup.picasso.Picasso


class DetailActivity : AppCompatActivity(){

    companion object {
        public val EXTRA_PARAM_ID = "detail:_id"
        public val EXTRA_PARAM_ITEM = "detail:_item"
        public val VIEW_NAME_HEADER_IMAGE = "detail:header:image"
        public val VIEW_NAME_HEADER_TITLE = "detail:header:title"
    }
    private var mHeaderImageView: ImageView? = null
    private var mHeaderTitle: TextView? = null

    private var mItem: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qf_activity_detail)

        mItem = intent.getSerializableExtra(EXTRA_PARAM_ITEM) as Item
        mHeaderImageView = findViewById(R.id.imageview_header) as ImageView
        mHeaderTitle = findViewById(R.id.textview_title) as TextView
        val height = getWidthScreen();
        mHeaderImageView?.setMinimumHeight(height)
        ViewCompat.setTransitionName(mHeaderImageView!!, VIEW_NAME_HEADER_IMAGE)
        ViewCompat.setTransitionName(mHeaderTitle!!, VIEW_NAME_HEADER_TITLE)
        loadItem()
    }

    private fun loadItem() {
        mHeaderTitle?.setText(getString(R.string.image_header, mItem!!.mName, mItem!!.mAuthor))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && addTransitionListener()) {
            loadThumbnail()
        } else {
            loadFullSizeImage()
        }
    }

    private fun getWidthScreen() : Int{
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
//        val width = size.x
//        val height = size.y
        return size.x / 2
    }

    private fun loadThumbnail() {
        Picasso.with(mHeaderImageView?.getContext())
            .load(mItem?.getThumbnailUrl())
            .noFade()
            .into(mHeaderImageView)
    }

    /**
     * Load the item's full-size image into our [ImageView].
     */
    private fun loadFullSizeImage() {
        Picasso.with(mHeaderImageView?.getContext())
            .load(mItem?.getPhotiUrl())
            .noFade()
            .noPlaceholder()
            .into(mHeaderImageView)
    }

    /**
     * Try and add a [Transition.TransitionListener] to the entering shared element
     * [Transition]. We do this so that we can load the full-size image after the transition
     * has completed.
     *
     * @return true if we were successful in adding a listener to the enter transition
     */
    private fun addTransitionListener(): Boolean {
        val transition = window.sharedElementEnterTransition

        if (transition != null) {
            // There is an entering shared element transition so add a listener to it
            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition) {
                    // As the transition has ended, we can now load the full-size image
                    loadFullSizeImage()

                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionStart(transition: Transition) {
                    // No-op
                }

                override fun onTransitionCancel(transition: Transition) {
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionPause(transition: Transition) {
                    // No-op
                }

                override fun onTransitionResume(transition: Transition) {
                    // No-op
                }
            })
            return true
        }

        // If we reach here then we have not added a listener
        return false
    }
}