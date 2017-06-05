package com.alexkaz.pictureviewer.presenter.impl;

import com.alexkaz.pictureviewer.model.entity.TokenDetails;
import com.alexkaz.pictureviewer.model.services.api.AuthService;
import com.alexkaz.pictureviewer.model.services.impl.AuthServiceImpl;
import com.alexkaz.pictureviewer.model.helpers.PrefsHelper;
import com.alexkaz.pictureviewer.model.helpers.PrefsHelperImpl;
import com.alexkaz.pictureviewer.presenter.api.AuthPresenter;
import com.alexkaz.pictureviewer.view.api.AuthView;

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
        Call<TokenDetails> tokenInfo = authService.getTokenInfo(code);
        tokenInfo.enqueue(new Callback<TokenDetails>() {
            @Override
            public void onResponse(Call<TokenDetails> call, Response<TokenDetails> response) {
                if (response.isSuccessful()){
                    TokenDetails tokenDetails = response.body();
                    prefsHelper.saveToken(tokenDetails.getAccessToken());
                    prefsHelper.setAuthenticated(true);
                    view.onSuccessfull();
                } else {
                    view.showErrorMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<TokenDetails> call, Throwable t) {
                view.showErrorMessage(t.getMessage());
            }
        });
    }
}
