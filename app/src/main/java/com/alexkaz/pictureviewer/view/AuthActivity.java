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
import com.alexkaz.pictureviewer.presenter.AuthPresenter;
import com.alexkaz.pictureviewer.presenter.AuthPresenterImpl;
import com.alexkaz.pictureviewer.utills.Constants;

public class AuthActivity extends AppCompatActivity implements AuthView {

    public static final String TAG = "tag";

    private WebView webView;
    private ProgressBar progressBar;
    private SharedPreferences prefs;
    private AuthPresenter presenter;

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
        presenter.doFullAuth(code);
    }

    @Override
    public void onSuccesfull() {
        // todo return to main activity;
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onFail() {
        // todo show some warning info
        setResult(RESULT_CANCELED);
        finish();
    }

}
