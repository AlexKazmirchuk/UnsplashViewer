package com.alexkaz.pictureviewer.view.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.app.MyApp;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.utills.CircleTransform;
import com.alexkaz.pictureviewer.view.impl.UserInfoActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoHolder> {

    private List<PhotoDetails> photos;

    protected RecyclerAdapter() {
        this.photos = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_row,parent,false);
        return new PhotoHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        PhotoDetails photoDetails = photos.get(position);

        holder.photoId = photoDetails.getId();
        holder.isLikedByUser = photoDetails.isLikedByUser();

        Picasso.with(MyApp.getContext()).load(photoDetails.getUser().getProfileImage().getMedium()).transform(new CircleTransform()).into(holder.userPhotoImgView);
        holder.userNameTxtView.setText(photoDetails.getUser().getName());
        Picasso.with(MyApp.getContext()).load(photoDetails.getUrls().getSmall()).into(holder.bigImgView);
        holder.likeAmountTxtView.setText(String.format(Locale.getDefault(),"%d", photoDetails.getLikes()));
        holder.likeImgView.setImageResource(photoDetails.isLikedByUser() ? R.drawable.liked_ic : R.drawable.unliked_ic);
    }

    public abstract void likePhoto(String photoId, int position);

    public abstract void unLikePhoto(String photoId, int position);

    public List<PhotoDetails> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDetails> photos) {
        this.photos = photos;
    }

    class PhotoHolder extends RecyclerView.ViewHolder {
        private String photoId;
        private boolean isLikedByUser;

        private ImageView userPhotoImgView;
        private TextView userNameTxtView;
        private ImageView bigImgView;
        private ImageView likeImgView;
        private TextView likeAmountTxtView;

        PhotoHolder(View v) {
            super(v);
            userPhotoImgView = (ImageView) v.findViewById(R.id.userPhotoImgView);
            userNameTxtView = (TextView) v.findViewById(R.id.userNameTxtView);
            bigImgView = (ImageView) v.findViewById(R.id.bigImgView);
            likeImgView = (ImageView) v.findViewById(R.id.likeImgView);
            likeAmountTxtView = (TextView) v.findViewById(R.id.likeAmountTxtView);

            userPhotoImgView.setOnClickListener(showUserInfoClickListener());
            userNameTxtView.setOnClickListener(showUserInfoClickListener());

            likeImgView.setOnClickListener(likePhotoClickListener());
        }

        private View.OnClickListener showUserInfoClickListener(){
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), UserInfoActivity.class);
                    intent.putExtra("user",photos.get(getLayoutPosition()).getUser());
                    v.getContext().startActivity(intent);
                }
            };
        }

        private View.OnClickListener likePhotoClickListener(){
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isLikedByUser){
                        unLikePhoto(photoId,getLayoutPosition());
                    } else {
                        likePhoto(photoId,getLayoutPosition());
                    }
                }
            };
        }
    }
}
