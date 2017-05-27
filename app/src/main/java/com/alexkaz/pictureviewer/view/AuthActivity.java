package com.alexkaz.pictureviewer.view;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.presenter.AuthPresenter;
import com.alexkaz.pictureviewer.presenter.AuthPresenterImpl;
import com.alexkaz.pictureviewer.utills.Constants;

public class AuthActivity extends AppCompatActivity implements AuthView {

    public static final String TAG = "tag";

    private WebView webView;
    private ProgressBar progressBar;
    private SharedPreferences prefs;
    private AuthPresenter presenter;
    boolean authComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        configureWebView();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        presenter = new AuthPresenterImpl(this);
    }

    private void configureWebView(){
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Constants.OAUTH2_URL);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                interceptUrls(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideProgressBar();
            }
        });
    }

    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void interceptUrls(String url){
        if (url.contains("authorize/") && !authComplete){
            Uri uri = Uri.parse(url);
            authComplete = true;
            handleAuthCode(uri.getLastPathSegment());
        }
        showProgressBar();
        if (url.contains("facebook")){
            webView.stopLoading();
            hideProgressBar();
            Toast.makeText(AuthActivity.this, "Login with facebook not supported yet", Toast.LENGTH_SHORT).show();
        }
        if (url.contains("https://unsplash.com/users/password/new") || url.contains("https://unsplash.com/join") || url.equals("https://unsplash.com/")){
            webView.stopLoading();
            hideProgressBar();
        }
    }

    public void handleAuthCode(String code){
        Log.d(TAG,code);  // todo delete in future
        Toast.makeText(this,code,Toast.LENGTH_SHORT).show(); // todo delete in future
        showProgressBar();
        webView.setVisibility(View.INVISIBLE);
        presenter.doFullAuth(code);
    }

    @Override
    public void onSuccesfull() {
        // todo return to main activity;
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
