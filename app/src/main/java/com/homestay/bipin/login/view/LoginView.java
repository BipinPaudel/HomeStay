package com.homestay.bipin.login.view;

/**
 * Created by Bipin on 4/25/17.
 */

public interface LoginView {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

}
