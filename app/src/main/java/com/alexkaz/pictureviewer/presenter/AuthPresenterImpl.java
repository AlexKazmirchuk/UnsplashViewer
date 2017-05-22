package com.alexkaz.pictureviewer.presenter;

import android.util.Log;

import com.alexkaz.pictureviewer.model.AuthResponse;
import com.alexkaz.pictureviewer.model.AuthService;
import com.alexkaz.pictureviewer.model.AuthServiceImpl;
import com.alexkaz.pictureviewer.model.PrefsHelper;
import com.alexkaz.pictureviewer.model.PrefsHelperImpl;
import com.alexkaz.pictureviewer.view.AuthView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthPresenterImpl implements AuthPresenter, Callback<AuthResponse> {
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
        tokenInfo.enqueue(this);
        // todo save token info to preferences file
        //view.onSuccesfull();
    }

    @Override
    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
        if (response.isSuccessful()){
            String accessToken = response.body().getAccessToken();
            Log.d("tag",accessToken);
            Log.d("tag",response.body().getScope());
            Log.d("tag",response.body().getTokenType());
            Log.d("tag",response.body().getCreatedAt() + "");
            Log.d("tag",accessToken);
        } else {
            Log.d("tag","Error!!!!!!!");
        }
    }

    @Override
    public void onFailure(Call<AuthResponse> call, Throwable t) {
        Log.d("tag",t.getMessage());
    }
}
