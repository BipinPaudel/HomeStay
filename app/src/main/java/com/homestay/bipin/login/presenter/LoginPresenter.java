package com.homestay.bipin.login.presenter;

/**
 * Created by Bipin on 4/25/17.
 */

public interface LoginPresenter {

    void validateCredentials(String username,String password);

    void onDestroy();
}
