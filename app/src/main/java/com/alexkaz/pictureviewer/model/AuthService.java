package com.alexkaz.pictureviewer.model;

import retrofit2.Call;

public interface AuthService {
    Call<AuthResponse> getTokenInfo(String code);
}
