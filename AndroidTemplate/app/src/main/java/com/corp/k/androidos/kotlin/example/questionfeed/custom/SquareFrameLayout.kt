package com.corp.k.androidos.kotlin.example.questionfeed.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

open class SquareFrameLayout(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs) {


//    fun SquareFrameLayout(context: Context, attrs: AttributeSet, defStyleAttr: Int): ??? {
//        super(context, attrs, defStyleAttr)
//    }
//
//    fun SquareFrameLayout(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): FrameLayout {
//        this.
//    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if (widthSize == 0 && heightSize == 0) {
            // If there are no constraints on size, let FrameLayout measure
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)

            // Now use the smallest of the measured dimensions for both dimensions
            val minSize = Math.min(measuredWidth, measuredHeight)
            setMeasuredDimension(minSize, minSize)
            return
        }

        val size: Int
        if (widthSize == 0 || heightSize == 0) {
            // If one of the dimensions has no restriction on size, set both dimensions to be the
            // on that does
            size = Math.max(widthSize, heightSize)
        } else {
            // Both dimensions have restrictions on size, set both dimensions to be the
            // smallest of the two
            size = Math.min(widthSize, heightSize)
        }

        val newMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
        super.onMeasure(newMeasureSpec, newMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}