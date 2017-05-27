package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.AuthResponse;
import com.alexkaz.pictureviewer.utills.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthServiceImpl implements AuthService {

    private AuthTokenApi authTokenApi;

    public AuthServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.OAUTH2_TOKEN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        authTokenApi = retrofit.create(AuthTokenApi.class);
    }

    @Override
    public Call<AuthResponse> getTokenInfo(String code) {
        return authTokenApi.getToken(Constants.CLIENT_ID,
                Constants.CLIENT_SECRET,
                Constants.REDIRECT_URI,
                code,
                Constants.GRANT_TYPE);
    }
}
