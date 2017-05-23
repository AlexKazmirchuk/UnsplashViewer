package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.utills.Constants;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetPhotosServiceImpl implements GetPhotosService {

    private GetPhotosApi getPhotosApi;
    private PrefsHelper prefsHelper;

    public GetPhotosServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getConfiguredHttpClient())
                .build();
        getPhotosApi = retrofit.create(GetPhotosApi.class);
        prefsHelper = new PrefsHelperImpl();
    }

    @Override
    public Call<List<PhotoDetails>> getPhotos() {
        return getPhotosApi.getPhotos();
    }

    @Override
    public Call<List<PhotoDetails>> getPhotos(String page,String perPage) {
        return getPhotosApi.getPhotos(page,perPage);
    }

    private OkHttpClient getConfiguredHttpClient(){
        return new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder()
                                .header("Authorization","Bearer " + prefsHelper.getToken());
                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                }).build();
    }
}
