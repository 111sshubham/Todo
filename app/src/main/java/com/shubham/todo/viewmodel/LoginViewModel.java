package com.shubham.todo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.shubham.todo.data.Repository;
import com.shubham.todo.data.User;

public class LoginViewModel extends ViewModel {

    private Repository repository;

    public LoginViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<User> getUser(String email, String password) {
        return repository.fetchUser(email, password);
    }

    public void saveUserId(Long userId) {
        repository.saveUserId(userId);
    }

    public Long getUserId() {
        return repository.getUserId();
    }

}
