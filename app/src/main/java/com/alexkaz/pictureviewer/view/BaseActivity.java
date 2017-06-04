package com.alexkaz.pictureviewer.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.*;

import com.alexkaz.pictureviewer.R;

public class BaseActivity extends AppCompatActivity {

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
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
}
