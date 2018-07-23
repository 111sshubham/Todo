package com.shubham.todo.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.shubham.todo.R;
import com.shubham.todo.TodoApplication;
import com.shubham.todo.data.Todo;
import com.shubham.todo.viewmodel.HomeViewModel;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;

    @Inject
    ViewModelProvider.Factory factory;

    private HomeViewModel viewModel;
    private long mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        di();
        vm();
        initViews();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserId = viewModel.getUserId();
        viewModel.getTodoList(mUserId).observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(@Nullable List<Todo> todos) {
                Log.e("HomeActivity",todos.toString());
            }
        });
    }

    private void di() {
        ((TodoApplication) (getApplication())).getApplicationComponent().inject(this);
    }

    private void vm() {
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.home_recyclerview);
    }

    private void setListener() {
        findViewById(R.id.home_fab).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_fab:
                startActivity(new Intent(this, TodoActivity.class));
                return;
        }

    }
}
