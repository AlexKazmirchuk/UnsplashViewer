package com.alexkaz.pictureviewer.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.utills.Constants;
import com.alexkaz.pictureviewer.view.AuthActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAuthorization();
    }

    private void checkAuthorization(){
        SharedPreferences preferences = getSharedPreferences(Constants.APP_PREFS,MODE_PRIVATE);
        boolean isAuthenticated = preferences.getBoolean(Constants.AUTHENTICATED,false);
        if (!isAuthenticated){
            // todo call AuthActivity for result maybe
            Intent authIntent  = new Intent(this, AuthActivity.class);
            startActivity(authIntent);
        }
    }
}
