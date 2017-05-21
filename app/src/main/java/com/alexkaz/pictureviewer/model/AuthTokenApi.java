package com.alexkaz.pictureviewer.model;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthTokenApi {

    @POST("oauth/token")
    Call<AuthResponse> getToken(@Query("client_id")String clientId,
                                @Query("client_secret")String clientSecret,
                                @Query("redirect_uri")String redirectUri,
                                @Query("code")String code,
                                @Query("grant_type")String grantType);

}
