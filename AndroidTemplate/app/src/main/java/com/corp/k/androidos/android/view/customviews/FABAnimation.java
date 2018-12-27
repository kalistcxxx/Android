package com.corp.k.androidos.android.view.customviews;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import com.corp.k.androidos.R;
/**
 * Created by hoangtuan on 10/20/16.
 */
public class FABAnimation {
    private Context mContext;
    public FABAnimation(Context mContext){
        this.mContext = mContext;
    }

    public void hideAnimationFAB(final View floatingActionButton){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ScaleAnimation shrink = new ScaleAnimation(1f, 0.2f, 1f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            shrink.setDuration(150);     // animation duration in milliseconds
            shrink.setInterpolator(new DecelerateInterpolator());
            shrink.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    floatingActionButton.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            floatingActionButton.startAnimation(shrink);
        }else{
            floatingActionButton.setVisibility(View.GONE);
        }
    }

    public void showAniamtionFAB(final View floatingActionButton){
        ScaleAnimation expand = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        expand.setDuration(100);     // animation duration in milliseconds
        expand.setInterpolator(new AccelerateInterpolator());
        expand.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                floatingActionButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        floatingActionButton.startAnimation(expand);
    }

    public void loadAnimation(final View view){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            view.clearAnimation();
            view.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.show_floating_animation);
            view.startAnimation(animation);
        }else{
            view.setVisibility(View.VISIBLE);
        }
    }

    public void hideAnimation(final View view){
        view.clearAnimation();
        view.setVisibility(View.GONE);
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.hide_floating_animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }


}
