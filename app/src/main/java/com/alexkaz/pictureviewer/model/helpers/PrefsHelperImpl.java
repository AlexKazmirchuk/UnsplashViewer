package com.alexkaz.pictureviewer.model.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.alexkaz.pictureviewer.app.MyApp;
import com.alexkaz.pictureviewer.utills.Constants;

public class PrefsHelperImpl implements PrefsHelper {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public PrefsHelperImpl() {
        Context context = MyApp.getContext();
        prefs = context.getSharedPreferences(Constants.APP_PREFS,Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        editor.putBoolean(Constants.AUTHENTICATED,authenticated);
        editor.apply();
    }

    @Override
    public void saveToken(String token) {
        editor.putString(Constants.TOKEN,token);
        editor.apply();
    }

    @Override
    public String getToken() {
        return prefs.getString(Constants.TOKEN,null);
    }

    @Override
    public void saveString(String key, String value) {
        editor.putString(key,value);
        editor.apply();
    }

    @Override
    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key,value);
        editor.apply();
    }

    @Override
    public void saveInt(String key, int value) {
        editor.putInt(key,value);
        editor.apply();
    }

    @Override
    public String getString(String key) {
        return prefs.getString(key,null);
    }

    @Override
    public boolean getBoolean(String key) {
        return prefs.getBoolean(key,false);
    }

    @Override
    public int getInt(String key) {
        return prefs.getInt(key,-1);
    }
}
