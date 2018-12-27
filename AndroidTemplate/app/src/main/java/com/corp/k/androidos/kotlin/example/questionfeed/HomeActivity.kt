package com.corp.k.androidos.kotlin.example.questionfeed

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewPager
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.corp.k.androidos.R
import com.corp.k.androidos.android.view.customviews.CircleImageView
import com.corp.k.androidos.kotlin.example.questionfeed.adapters.ViewPagerAdapter
import com.corp.k.androidos.kotlin.example.questionfeed.custom.loadUrl
import com.corp.k.androidos.kotlin.example.questionfeed.fragments.DetailFeedFragment
import com.corp.k.androidos.kotlin.example.questionfeed.fragments.FragmentsUtils
import com.corp.k.androidos.kotlin.example.questionfeed.fragments.ProfileFragment
import com.corp.k.androidos.kotlin.example.questionfeed.fragments.inTransaction
import com.corp.k.androidos.kotlin.example.questionfeed.models.Feed
import com.corp.k.androidos.kotlin.example.questionfeed.models.Item

class HomeActivity : AppCompatActivity() {
    var count :Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        setContentView(R.layout.qf_activity_home)
        if(savedInstanceState!=null) {
            //Restored state of activity here
        }
        /**
         * Bottom sheet view
         */
        val mLayoutBottomSheet = findViewById(R.id.linear_layout_bottom_sheet) as NestedScrollView
        val bottomSheetBehavior = BottomSheetBehavior.from(mLayoutBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState){
                    BottomSheetBehavior.STATE_EXPANDED -> {
//                        Toast.makeText(applicationContext, "Show", Toast.LENGTH_SHORT).show()
                    }
                    BottomSheetBehavior.STATE_SETTLING ->{
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    }
                    else ->{
//                        Toast.makeText(applicationContext, "Hide", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter =
                ViewPagerAdapter(supportFragmentManager);
        val avatarUser = findViewById(R.id.avatarUser) as CircleImageView
        avatarUser.loadUrl("https://cdn0.iconfinder.com/data/icons/user-pictures/100/matureman1-512.png")
        avatarUser.setOnClickListener{ view ->
            supportFragmentManager.inTransaction {
//                count++
//                Log.i("Key", count.toString())
//                val profileFragment = ProfileFragment.onNewInstance(null)
//                add(R.id.viewContainer, profileFragment)
                val fragmentDetail = ProfileFragment.onNewInstance(null)
                val fragmentReplace = FragmentsUtils()
                fragmentReplace.replaceFragment(supportFragmentManager, fragmentDetail, R.id.viewContainer)
            }
        }
        /**
         * Searching View
         */
        val searchingButton = findViewById(R.id.imgSearching) as ImageView
        val layoutSearching = findViewById(R.id.layoutSearching) as LinearLayout
        searchingButton.setOnClickListener{ view ->
            if(layoutSearching.visibility == View.GONE) {
                expandView(layoutSearching)
            }else{
                collapse(layoutSearching)
            }
        }

    }

    fun goToDetail(itemObjectClick : Feed){
        val fragmentDetail = DetailFeedFragment.newInstance(itemObjectClick)
        val fragmentReplace = FragmentsUtils()
        fragmentReplace.replaceFragment(supportFragmentManager, fragmentDetail, R.id.fragmentContainer)
    }

    fun goToDetailGrid(itemObjectClick: Item, view : View) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_PARAM_ID, itemObjectClick.getId())
        intent.putExtra(DetailActivity.EXTRA_PARAM_ITEM, itemObjectClick)

        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair(
                view.findViewById(R.id.imageview_item), DetailActivity.VIEW_NAME_HEADER_IMAGE
            ),
            Pair(
                view.findViewById(R.id.textview_name), DetailActivity.VIEW_NAME_HEADER_TITLE
            )
        )

        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle())
    }

    override fun onPause() {
        super.onPause()
        this.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean("SavingState", true)
        super.onSaveInstanceState(outState)
    }

    /**
     * Exand a view with animation.
     */
    private fun expandView(view: View?){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1){
            view?.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val targetHeight = view?.measuredHeight
            // Older versions of android (pre API 21) cancel animations for views with a height of 0.
            view?.layoutParams?.height = 1
            view?.visibility = View.VISIBLE
            val animationExpand = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                    view?.getLayoutParams()?.height = if (interpolatedTime == 1f)
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    else
                        (targetHeight!! * interpolatedTime).toInt()
                    view?.requestLayout()
                }

                override fun willChangeBounds() = true
            }
            animationExpand.duration = ((targetHeight!!.toInt()) / view.getContext().getResources().getDisplayMetrics().density).toLong()
            view.startAnimation(animationExpand)
        }else{
            view?.visibility = View.VISIBLE
        }
    }

    /**
     * Collapse a view with animation.
     */
    private fun collapse(view : View?){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val finalHeight = view?.height
            val collapseAnimation = object : Animation(){
                override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                    if (interpolatedTime == 1f) {
                        view?.setVisibility(View.GONE)
                    } else {
                        view?.getLayoutParams()?.height = finalHeight!! - (finalHeight * interpolatedTime).toInt()
                        view.requestLayout()
                    }
                }

                override fun willChangeBounds() = true
            }
            collapseAnimation.duration = ((finalHeight!!.toInt()) / view.getContext().getResources().getDisplayMetrics().density).toLong()
            view.startAnimation(collapseAnimation)
        }else{
            view?.setVisibility(View.GONE);
        }
    }

    /**
     * Create a slide Animator on view.
     */
    private fun slideAnimator(start : Int, end : Int, view : View?) : ValueAnimator {
        val animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener{ object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(valueAnimator: ValueAnimator) {
                //Update Height
                val value = valueAnimator.animatedValue as Int
                val layoutParams = view?.layoutParams
                layoutParams?.height = value;
                view?.layoutParams = layoutParams;
            }
        }}
        return animator;
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN){
            val view = this.currentFocus
            if (view is EditText){
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())){
                    view.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }


}
