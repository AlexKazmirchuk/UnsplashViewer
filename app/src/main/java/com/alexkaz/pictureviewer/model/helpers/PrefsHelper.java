package com.alexkaz.pictureviewer.model.helpers;

public interface PrefsHelper {
    void setAuthenticated(boolean authenticated);
    void saveToken(String token);
    String getToken();
    void saveString(String key, String value);
    void saveBoolean(String key, boolean value);
    void saveInt(String key, int value);
    String getString(String key);
    boolean getBoolean(String key);
    int getInt(String key);
}

