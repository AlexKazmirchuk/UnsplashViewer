package com.alexkaz.pictureviewer.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alexkaz.pictureviewer.R;

public abstract class WithAppIconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureActionBar();
    }

    private void configureActionBar(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.drawable.app_ic);
        }
    }
}
