package com.alexkaz.pictureviewer.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.utills.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final int AUTH_ACTIVITY_REQ_CODE = 1;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAuthorization();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void checkAuthorization(){
        SharedPreferences preferences = getSharedPreferences(Constants.APP_PREFS,MODE_PRIVATE);
        boolean isAuthenticated = preferences.getBoolean(Constants.AUTHENTICATED,false);
        if (!isAuthenticated){
            // todo call AuthActivity for result maybe
            Intent authIntent  = new Intent(this, AuthActivity.class);
            startActivity(authIntent);
            startActivityForResult(authIntent, AUTH_ACTIVITY_REQ_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AUTH_ACTIVITY_REQ_CODE){
            //TextView textView = (TextView) findViewById(R.id.mainTextView);
            if (resultCode == RESULT_OK){
               // textView.setTextSize(20);
                //textView.setText("You are authenticted succesfull!! " + getSharedPreferences(Constants.APP_PREFS,MODE_PRIVATE).getBoolean(Constants.AUTHENTICATED,false));
            } else {
                //textView.setTextSize(20);
                //textView.setText("Something it's wrong, try again");
            }

        } else {
            // todo make finish() temporary
        }
    }

    @Override
    public void showPhotos(List<String> photos) {

    }

    @Override
    public void showErrorMessage(String message) {

    }
}
