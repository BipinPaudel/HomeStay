package com.homestay.bipin.login.interactor;

/**
 * Created by Bipin on 4/25/17.
 */

public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void onUsernameError();
        void onPasswordError();
        void onSuccess();
    }

    void login(String username,String password,OnLoginFinishedListener listener);

}
