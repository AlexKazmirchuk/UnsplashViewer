package com.alexkaz.pictureviewer.model.services.impl;


import com.alexkaz.pictureviewer.model.helpers.PrefsHelper;
import com.alexkaz.pictureviewer.model.helpers.PrefsHelperImpl;
import com.alexkaz.pictureviewer.utills.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseService {

    private PrefsHelper prefsHelper;
    private Retrofit retrofit;

    public BaseService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getConfiguredHttpClient())
                .build();
        prefsHelper = new PrefsHelperImpl();
    }

    public <T> T getApi(Class<T> api){
        return retrofit.create(api);
    }

    private OkHttpClient getConfiguredHttpClient() {
        return new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder()
                                .header(Constants.AUTHORIZATION,Constants.AUTHORIZATION_TYPE + " " + prefsHelper.getToken());
                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                }).build();
    }
}
