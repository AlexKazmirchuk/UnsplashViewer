package com.alexkaz.pictureviewer.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.alexkaz.pictureviewer.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    protected void showProgressBar(){
        findViewById(R.id.progressBar).setVisibility(android.view.View.VISIBLE);
    }

    protected void hideProgressBar(){
        findViewById(R.id.progressBar).setVisibility(android.view.View.INVISIBLE);
    }

    protected void showAlertMessage(){
        findViewById(R.id.noConnectionView).setVisibility(android.view.View.VISIBLE);
    }

    protected void hideAlertMessage(){
        findViewById(R.id.noConnectionView).setVisibility(android.view.View.INVISIBLE);
    }

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
