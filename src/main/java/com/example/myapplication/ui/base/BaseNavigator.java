package com.example.myapplication.ui.base;

public interface BaseNavigator {
    String getStringFromId(int string_id);
    void showToast(String message);
    void displayError(Throwable throwable);
    void displayErrorMessage(int errorCode,String errorMessage);
    void showLoader(boolean isShowLoader);
}
