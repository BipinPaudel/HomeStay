package com.homestay.bipin.login.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.homestay.bipin.R;
import com.homestay.bipin.guest.guestList.view.GuestActivity;
import com.homestay.bipin.login.presenter.LoginPresenter;
import com.homestay.bipin.login.presenter.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    @BindView(R.id.username_id)
    EditText username;

    @BindView(R.id.password_id)
    EditText password;

    @BindView(R.id.login_id)
    TextView login;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenterImpl(this);

        login.setOnClickListener(this);


    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        Intent intent =new Intent(LoginActivity.this,GuestActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }

    @Override
    public void onClick(View view) {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());

    }
}
