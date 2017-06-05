package com.alexkaz.pictureviewer.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.presenter.AuthPresenter;
import com.alexkaz.pictureviewer.presenter.AuthPresenterImpl;
import com.alexkaz.pictureviewer.utills.Constants;

public class AuthActivity extends BaseActivity implements AuthView {

    public static final String FORGOT_PASS_URL = "https://unsplash.com/users/password/new";
    public static final String JOIN_URL = "https://unsplash.com/join";
    public static final String MAIN_PAGE_URL = "https://unsplash.com/";

    private WebView webView;
    private AuthPresenter presenter;
    boolean authComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        configureWebView();
        presenter = new AuthPresenterImpl(this);
        if (!isOnline()){
            showAlertMessage();
            hideWebView();
        }
    }

    private void configureWebView(){
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Constants.OAUTH2_URL);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (isOnline()){
                    handleUrls(url);
                } else {
                    showAlertMessage();
                    hideWebView();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideProgressBar();
                if (url.contains("oauth/login")){
                    showWebView();
                }
            }
        });
    }

    private void showWebView(){
        webView.setVisibility(View.VISIBLE);
    }

    private void hideWebView(){
        webView.setVisibility(View.INVISIBLE);
    }

    private void handleUrls(String url){
        if (url.contains("authorize/") && !authComplete){
            Uri uri = Uri.parse(url);
            authComplete = true;
            handleAuthCode(uri.getLastPathSegment());
        }
        showProgressBar();
        if (url.contains("facebook")){
            webView.stopLoading();
            hideProgressBar();
            Toast.makeText(AuthActivity.this, R.string.fb_auth_not_supported_message, Toast.LENGTH_SHORT).show();
        }
        if (url.contains(FORGOT_PASS_URL) || url.contains(JOIN_URL) || url.equals(MAIN_PAGE_URL)){
            webView.stopLoading();
            hideProgressBar();
        }
    }

    public void handleAuthCode(String code){
        showProgressBar();
        hideWebView();
        presenter.doFullAuth(code);
    }

    @Override
    public void onSuccessfull() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.auth_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh){
            if (isOnline()){
                webView.loadUrl(Constants.OAUTH2_URL);
                hideAlertMessage();
                addAnimToActionRefreshItem(item);
            }else {
                hideWebView();
                showAlertMessage();
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
