package com.shubham.todo.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.shubham.todo.R;
import com.shubham.todo.TodoApplication;
import com.shubham.todo.data.Todo;
import com.shubham.todo.util.BaseActivity;
import com.shubham.todo.util.Resource;
import com.shubham.todo.util.Status;
import com.shubham.todo.viewmodel.LoginViewModel;
import com.shubham.todo.viewmodel.TodoViewModel;

import javax.inject.Inject;

import static com.shubham.todo.util.Constants.ERROR_DB_INSERT;
import static com.shubham.todo.util.Constants.ERROR_EMAIL_ALREADY_EXIST;

public class TodoActivity extends BaseActivity implements View.OnClickListener {

    @Inject
    ViewModelProvider.Factory factory;

    private TodoViewModel viewModel;

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        di();
        vm();
        initViews();
        setListeners();
    }


    private void di() {
        ((TodoApplication) getApplication()).getApplicationComponent().inject(this);
    }

    private void vm() {
        viewModel = ViewModelProviders.of(this, factory).get(TodoViewModel.class);
    }

    private void initViews() {
        mEditText = findViewById(R.id.todo_et);
    }

    private void setListeners() {
        findViewById(R.id.todo_btn_save).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.todo_btn_save:
                saveTodo();
                return;
        }
    }

    private void saveTodo() {
        String todoText = mEditText.getText().toString();
        long userId = viewModel.getUserId();
        Todo todo = new Todo(todoText,userId);
        viewModel.createNewTodo(todo).observe(this, new Observer<Resource<Long>>() {
            @Override
            public void onChanged(@Nullable Resource<Long> longResource) {
                if (longResource.status == Status.SUCCESS) {
                    Long result = longResource.data;
                    if (null == result || ERROR_DB_INSERT == result.longValue()) {
                        showToast(ERROR_EMAIL_ALREADY_EXIST);
                    } else {

                        startHomeActivity();
                    }
                }
            }
        });

    }

    private void startHomeActivity() {
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }
}
