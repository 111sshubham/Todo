package com.shubham.todo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.shubham.todo.data.Repository;
import com.shubham.todo.data.Todo;
import com.shubham.todo.util.Resource;

public class TodoViewModel extends ViewModel {

    private Repository repository;

    public TodoViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<Long>> createNewTodo(Todo todo) {
        return repository.createNewTodo(todo);
    }

    public long getUserId(){
        return repository.getUserId();
    }

}
