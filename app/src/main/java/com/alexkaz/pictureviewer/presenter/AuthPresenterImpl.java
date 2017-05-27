package com.alexkaz.pictureviewer.presenter;

import android.util.Log;

import com.alexkaz.pictureviewer.model.entity.AuthResponse;
import com.alexkaz.pictureviewer.model.AuthService;
import com.alexkaz.pictureviewer.model.AuthServiceImpl;
import com.alexkaz.pictureviewer.model.PrefsHelper;
import com.alexkaz.pictureviewer.model.PrefsHelperImpl;
import com.alexkaz.pictureviewer.view.AuthView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthPresenterImpl implements AuthPresenter {
    private AuthView view;
    private AuthService authService;
    private PrefsHelper prefsHelper;

    public AuthPresenterImpl(AuthView view) {
        this.view = view;
        authService = new AuthServiceImpl();
        prefsHelper = new PrefsHelperImpl();
    }

    @Override
    public void doFullAuth(String code) {
        Call<AuthResponse> tokenInfo = authService.getTokenInfo(code);
        tokenInfo.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()){
                    AuthResponse tokenInfo = response.body();

                    prefsHelper.saveToken(tokenInfo.getAccessToken());
                    prefsHelper.saveString("scope",tokenInfo.getScope());
                    prefsHelper.saveString("tokenType",tokenInfo.getTokenType());
                    prefsHelper.saveInt("createdAt",tokenInfo.getCreatedAt());
                    prefsHelper.setAuthenticated(true);
                    view.onSuccesfull();
                } else {
                    view.showErrorMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                view.showErrorMessage(t.getMessage());
            }
        });
    }
}
