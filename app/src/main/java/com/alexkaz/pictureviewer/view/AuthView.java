package com.alexkaz.pictureviewer.view;

public interface AuthView {
    void showErrorMessage();
    void handleAuthCode(String code);
    void onSuccesfull();
    void onFail();
}
