package com.alexkaz.pictureviewer.view.impl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alexkaz.pictureviewer.R;

public abstract class WithRefreshIconAnimActivity extends WithProgressBarActivity {

    protected void addAnimToActionRefreshItem(final MenuItem item){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageView iv = (ImageView)inflater.inflate(R.layout.iv_refresh, null);
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotation);
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                item.getActionView().clearAnimation();
                item.setActionView(null);
            }
        });
        iv.startAnimation(rotation);
        item.setActionView(iv);
    }

}
