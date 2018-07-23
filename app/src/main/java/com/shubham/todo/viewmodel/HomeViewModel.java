package com.shubham.todo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.shubham.todo.data.Repository;
import com.shubham.todo.data.Todo;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private Repository repository;

    public HomeViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Todo>> getTodoList(long userId){
        return repository.fetchTodoList(userId);
    }

    public Long getUserId() {
        return repository.getUserId();
    }
}
