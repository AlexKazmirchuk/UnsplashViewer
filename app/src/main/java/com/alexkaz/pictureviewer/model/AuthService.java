package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.TokenDetails;

import retrofit2.Call;

public interface AuthService {
    Call<TokenDetails> getTokenInfo(String code);
}
