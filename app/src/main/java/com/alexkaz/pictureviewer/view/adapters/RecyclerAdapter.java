package com.alexkaz.pictureviewer.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.app.MyApp;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoHolder> {

    private List<PhotoDetails> photos;

    public RecyclerAdapter(List<PhotoDetails> photos) {
        this.photos = photos;
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
        // todo fetch data from list and fill views in holder
        PhotoDetails photoDetails = photos.get(position);
        holder.userNameTxtView.setText(photoDetails.getUser().getFirstName() + " " + photoDetails.getUser().getLastName());
        holder.likeAmountTxtView.setText(photoDetails.getLikes() + "");
        Picasso.with(MyApp.getContext()).load(photoDetails.getUrls().getSmall()).into(holder.bigImgView);
        Picasso.with(MyApp.getContext()).load(photoDetails.getUser().getProfileImage().getMedium()).into(holder.userPhotoImgView);
    }

    public List<PhotoDetails> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDetails> photos) {
        this.photos = photos;
    }

    public static class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView userPhotoImgView;
        private TextView userNameTxtView;
        private ImageView bigImgView;
        private ImageView likeImgView;
        private TextView likeAmountTxtView;

        public PhotoHolder(View v) {
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
                    // todo launch activity with full info about user
                }
            };
        }

        private View.OnClickListener likePhotoClickListener(){
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // todo make like for photo
                }
            };
        }
    }
}