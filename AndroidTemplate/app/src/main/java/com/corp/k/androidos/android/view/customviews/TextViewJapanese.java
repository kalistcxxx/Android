package com.corp.k.androidos.android.view.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by hoangtuan on 8/1/16.
 */
public class TextViewJapanese extends TextView {

    /*
     * Caches typefaces based on their file path and name, so that they don't have to be created every time when they are referenced.
     */
    public static Typeface mTypeface;

    public TextViewJapanese(final Context context) {
        this(context, null);
    }

    public TextViewJapanese(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewJapanese(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/DroidSansJapanese.ttf");
        }
        setTypeface(mTypeface);
    }

}