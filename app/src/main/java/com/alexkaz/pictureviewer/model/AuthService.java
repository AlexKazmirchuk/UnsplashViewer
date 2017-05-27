package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.AuthResponse;

import retrofit2.Call;

public interface AuthService {
    Call<AuthResponse> getTokenInfo(String code);
}
