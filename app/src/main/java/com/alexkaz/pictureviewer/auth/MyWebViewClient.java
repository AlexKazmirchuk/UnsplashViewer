package com.alexkaz.pictureviewer.auth;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alexkaz.pictureviewer.ui.AuthActivity;

public class MyWebViewClient extends WebViewClient {

    private AuthActivity authActivity;
    boolean authComplete = false;

    public MyWebViewClient(AuthActivity authActivity) {
        this.authActivity = authActivity;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (url.contains("authorize/") && authComplete !=true){
            Uri uri = Uri.parse(url);
            authComplete = true;
            authActivity.handleAuthCode(uri.getLastPathSegment());
        } else if (false){
            //todo write handling if user logins with facebook
        } else {
            // todo handle if some errors occured
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

}
