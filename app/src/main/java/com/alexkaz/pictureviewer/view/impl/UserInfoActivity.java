package com.alexkaz.pictureviewer.view.impl;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.model.entity.User;
import com.alexkaz.pictureviewer.utills.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class UserInfoActivity extends AppCompatActivity {

    private TextView userName;
    private ImageView bigUserImgView;
    private TextView userLikesCount;
    private TextView userPhotosCount;
    private TextView userCollectionsCount;
    private TextView userBio;
    private TextView userLocation;
    private TextView userPortfolio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initViews();
        Intent intent = getIntent();
        if (intent != null){
            User user = intent.getExtras().getParcelable("user");
            fillViews(user);
        }
    }

    private void initViews(){
        userName = (TextView) findViewById(R.id.userName);
        bigUserImgView = (ImageView) findViewById(R.id.bigUserPhotoImgView);

        userLikesCount = (TextView) findViewById(R.id.userLikesCount);
        userPhotosCount = (TextView) findViewById(R.id.userPhotosCount);
        userCollectionsCount = (TextView) findViewById(R.id.userCollectionsCount);

        userBio = (TextView) findViewById(R.id.userBio);
        userLocation = (TextView) findViewById(R.id.userLocation);
        userPortfolio = (TextView) findViewById(R.id.userPortfolioUrl);
    }

    private void fillViews(User user){
        Picasso.with(this).load(user.getProfileImage().getLarge()).transform(new CircleTransform()).into(bigUserImgView);
        userName.setText(user.getName());
        userLikesCount.setText(String.format(Locale.getDefault(),"%d", user.getTotalLikes()));
        userPhotosCount.setText(String.format(Locale.getDefault(),"%d", user.getTotalPhotos()));
        userCollectionsCount.setText(String.format(Locale.getDefault(),"%d", user.getTotalCollections()));

        userBio.setText(user.getBio());
        userLocation.setText(user.getLocation());
        userPortfolio.setText(parseUrl(user.getPortfolioUrl()));

        if(user.getBio() == null || user.getBio().equals("")) { findViewById(R.id.userBioLabel).setVisibility(View.INVISIBLE);  }
        if(user.getLocation() == null || user.getLocation().equals("")) { findViewById(R.id.userLocationLabel).setVisibility(View.INVISIBLE);  }
        if(user.getPortfolioUrl() == null || user.getPortfolioUrl().equals("")) { findViewById(R.id.userPortfolioUrlLabel).setVisibility(View.INVISIBLE);  }
    }

    private String parseUrl(String url){
        if (url == null) return "";
        if (URLUtil.isValidUrl(url)){
            Uri uri = Uri.parse(url);
            return uri.getHost() + uri.getPath();
        }
        return "";
    }
}
