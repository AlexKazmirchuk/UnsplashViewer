package com.alexkaz.pictureviewer.app;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getContext(){
        return MyApp.appContext;
    }
}
