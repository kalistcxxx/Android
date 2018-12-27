package com.corp.k.androidos.android.view.customviews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * Created by hoangtuan on 4/7/16.
 */
public class ViewPagerNoTouch extends ViewPager{
    public boolean isSwipe() {
        return isSwipe;
    }

    private boolean isSwipe = true;
    public ViewPagerNoTouch(Context context) {
        super(context);
    }
    public void setSwipingView(boolean isSwiping){
        this.isSwipe = isSwiping;
    }
    public ViewPagerNoTouch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.isSwipe) {
            return super.onInterceptTouchEvent(event);
        }
        return isSwipe;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        if (this.isSwipe) {
            return super.onTouchEvent(event);
        }
        return isSwipe;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }
}
