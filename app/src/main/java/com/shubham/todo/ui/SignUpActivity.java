package com.shubham.todo.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shubham.todo.R;
import com.shubham.todo.TodoApplication;
import com.shubham.todo.data.User;
import com.shubham.todo.util.BaseActivity;
import com.shubham.todo.util.Constants;
import com.shubham.todo.util.Resource;
import com.shubham.todo.util.Status;
import com.shubham.todo.viewmodel.RegistrationViewModel;

import javax.inject.Inject;

public class SignUpActivity extends BaseActivity implements View.OnClickListener, Constants {
    @Inject
    ViewModelProvider.Factory factory;

    private RegistrationViewModel viewModel;

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextPasswordConfirm;


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        di();
        vm();
        initViews();
        setListener();
    }

    private void vm() {
        viewModel = ViewModelProviders.of(this, factory).get(RegistrationViewModel.class);
    }

    private void initViews() {
        mEditTextName = findViewById(R.id.registration_et_name);
        mEditTextEmail = findViewById(R.id.registration_et_mail);
        mEditTextPassword = findViewById(R.id.registration_et_password);
        mEditTextPasswordConfirm = findViewById(R.id.registration_et_confirm_password);

        textView = findViewById(R.id.tv);
    }

    private void setListener() {
        findViewById(R.id.btn_signup).setOnClickListener(this);
    }

    private void di() {
        ((TodoApplication) getApplication()).getApplicationComponent().inject(this);
    }

    @Override
    public void onClick(View v) {
        hideSoftKeyboard();
        switch (v.getId()) {
            case R.id.btn_signup:
                registerUser();
                return;
        }
    }

    private void registerUser() {
        User user = getUser();
        if (null != user) {
            viewModel.createUser(user).observe(this, (@Nullable Resource<Long> longResource) -> {
                if (longResource.status == Status.SUCCESS) {
                    Long result = longResource.data;
                    if (null == result || ERROR_DB_INSERT == result.longValue()) {
                        showToast(ERROR_EMAIL_ALREADY_EXIST);
                    } else {
                        viewModel.saveUserId(result);
                        startHomeActivity();
                    }
                }

            });
        }

    }

    private User getUser() {
        String name = mEditTextName.getText().toString();
        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();
        String confirmPassword = mEditTextPasswordConfirm.getText().toString();

        if (TextUtils.isEmpty(name)) {
            showToast(ERROR_USER_NAME);
            return null;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast(ERROR_EMAIL_ID);
            return null;
        } else if (password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH) {
            showToast(ERROR_PASS_LENGTH);
            return null;
        } else if (!password.equals(confirmPassword)) {
            showToast(ERROR_PASS_MATCH);
            return null;
        }
        return new User(name, email, password);
    }

    private void startHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
