package com.shubham.todo.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.shubham.todo.R;
import com.shubham.todo.TodoApplication;
import com.shubham.todo.data.User;
import com.shubham.todo.util.BaseActivity;
import com.shubham.todo.util.Constants;
import com.shubham.todo.viewmodel.LoginViewModel;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements View.OnClickListener, Constants {
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;

    @Inject
    ViewModelProvider.Factory factory;

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        di();
        vm();
        checkLogin();
        initViews();
        setListener();
    }


    private void di() {
        ((TodoApplication) getApplication()).getApplicationComponent().inject(this);
    }

    private void vm() {
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    private void checkLogin() {
        if (viewModel.getUserId() != LOGOUT) {
            startHomeActivity();
        }
    }

    private void initViews() {
        mEditTextEmail = findViewById(R.id.login_et_mail);
        mEditTextPassword = findViewById(R.id.login_et_password);
    }

    /**
     * method use to set Listeners
     */
    private void setListener() {
        findViewById(R.id.login_tv_notAccount).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        hideSoftKeyboard();
        switch (v.getId()) {
            case R.id.login_tv_notAccount:
                startRegistrationActivity();
                return;
            case R.id.btn_login:
                login();
                return;
        }
    }

    private void login() {
        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();
        viewModel.getUser(email, password).observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (null != user) {
                    viewModel.saveUserId((long) user.id);
                    startHomeActivity();
                } else {
                    showToast(ERROR_INVALID_MAIL_PASS);
                }
            }
        });
    }

    /**
     * method use to start Registration Activity
     */
    private void startRegistrationActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    private void startHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }


}
