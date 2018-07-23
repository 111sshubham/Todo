package com.shubham.todo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.shubham.todo.data.Repository;
import com.shubham.todo.data.User;
import com.shubham.todo.util.Resource;

public class RegistrationViewModel extends ViewModel {
    private Repository repository;

    public RegistrationViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<Long>> createUser(User user) {
        return repository.createNewUser(user);
    }

    public void saveUserId(Long userId) {
        repository.saveUserId(userId);
    }
}
