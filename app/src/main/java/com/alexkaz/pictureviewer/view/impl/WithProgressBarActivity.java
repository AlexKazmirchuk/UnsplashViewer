package com.alexkaz.pictureviewer.view.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.alexkaz.pictureviewer.R;

public abstract class WithProgressBarActivity extends WithAppIconActivity {

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        hideProgressBar();
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
