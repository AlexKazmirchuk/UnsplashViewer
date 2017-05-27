package com.alexkaz.pictureviewer.view;

public interface AuthView {
    void showErrorMessage(String message);
    void handleAuthCode(String code);
    void onSuccesfull();
}
