package com.alexkaz.pictureviewer.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.auth.MyWebViewClient;

public class AuthActivity extends AppCompatActivity implements AuthView {

    public static final String TAG = "tag";

    private static final String CLIENT_ID = "3d3a8c1d059e839a230409c18bfef3e9cd51e378dc7abadc53e469162db1343e";
    private static final String REDIRECT_URI="urn:ietf:wg:oauth:2.0:oob";
    private static final String RESPONSE_TYPE="code";
    private static final String SCOPE="public";

    private static final String OAUTH2_URL="https://unsplash.com/oauth/authorize?" +
            "client_id=" + CLIENT_ID +
            "&redirect_uri=" + REDIRECT_URI +
            "&response_type=" + RESPONSE_TYPE +
            "&scope=" + SCOPE;

    private static final String CLIENT_SECRET = "03e3b6093ef6b18b021cb44308b83ea7a7168594055616cad98a04a8a4526613";
    private static final String GRANT_TYPE="authorization_code";

    private WebView webView;
    private ProgressBar progressBar;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        configureWebView();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void configureWebView(){
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(OAUTH2_URL);
        webView.setWebViewClient(new MyWebViewClient(this));
    }

    private void showProgress(){
        webView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        //todo impl message showing
    }

    public void handleAuthCode(String code){
        Log.d(TAG,code);
        Toast.makeText(this,code,Toast.LENGTH_SHORT).show();
        showProgress();
    }

    @Override
    public void onSuccesfull() {

    }

    @Override
    public void onFail() {

    }

}
