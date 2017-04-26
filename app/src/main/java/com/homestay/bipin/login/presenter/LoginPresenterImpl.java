package com.homestay.bipin.login.presenter;

import com.homestay.bipin.login.interactor.LoginInteractor;
import com.homestay.bipin.login.interactor.LoginInteractorImpl;
import com.homestay.bipin.login.view.LoginActivity;
import com.homestay.bipin.login.view.LoginView;

/**
 * Created by Bipin on 4/25/17.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;



    public LoginPresenterImpl(LoginView loginView){
        this.loginView=loginView;
        loginInteractor= new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView!=null){
            loginView.showProgress();
        }

    loginInteractor.login(username,password,this);


    }

    @Override
    public void onDestroy() {
        loginView=null;
    }

    @Override
    public void onUsernameError() {
        if (loginView!=null){
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView!=null){
            loginView.setPasswordError();
            loginView.hideProgress();
        }

    }

    @Override
    public void onSuccess() {
        if (loginView!=null){
            loginView.navigateToHome();
        }
    }
}
