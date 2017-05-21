package com.alexkaz.pictureviewer;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAuthorization();
    }

    private void checkAuthorization(){
        SharedPreferences preferences = getSharedPreferences("app_prefs",MODE_PRIVATE);
        boolean isAuthenticated = preferences.getBoolean("authenticated",false);
        if (isAuthenticated){
            // todo call AuthActivity for result maybe
        }
    }
}
