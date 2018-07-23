package com.shubham.todo.data;

import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;

import com.shubham.todo.data.prefs.AppPreferencesHelper;
import com.shubham.todo.util.Resource;

import java.util.List;

public class Repository {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final AppPreferencesHelper preferencesHelper;

    public Repository(TodoRepository todoRepository, UserRepository userRepository,AppPreferencesHelper preferencesHelper) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.preferencesHelper = preferencesHelper;
    }

    public LiveData<Resource<Long>> createNewUser(User user) {
        return userRepository.createUser(user);
    }


    public LiveData<User> fetchUser(String email, String password) {
        return userRepository.fetchUser(email, password);
    }

    public LiveData<Resource<Long>> createNewTodo(Todo todo) {
        return todoRepository.createNewTodo(todo);
    }

    public LiveData<List<Todo>> fetchTodoList(long userId) {
        return todoRepository.fetchTodoList(userId);
    }

    public void saveUserId(Long userId){
        preferencesHelper.setuserId(userId);
    }

    public Long getUserId(){
        return preferencesHelper.getUserId();
    }


    /*public void updateTodo(Todo todo) {
        todoDao.update(todo);
    }

    public void deleteTodo(Todo todo) {
        todoDao.delete(todo);
    }

    */

}
