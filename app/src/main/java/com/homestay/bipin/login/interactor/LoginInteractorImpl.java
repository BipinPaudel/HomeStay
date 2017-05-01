package com.homestay.bipin.login.interactor;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by Bipin on 4/25/17.
 */

public class LoginInteractorImpl implements LoginInteractor{

    @Override
    public void login(final String username,final String password, final OnLoginFinishedListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error=false;
                if (TextUtils.isEmpty(username)){
                    listener.onUsernameError();
                    error=false;
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    error=false;
                    return;
                }
//                if (!error){
                    listener.onSuccess();
//                }
            }
        },500);
    }
}
