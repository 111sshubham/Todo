package com.shubham.todo.data;

import android.arch.lifecycle.LiveData;

import com.shubham.todo.util.Resource;

import java.util.List;

public class TodoRepository {
    private final TodoDao todoDao;

    public TodoRepository(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public LiveData<Resource<Long>> createNewTodo(Todo todo) {
        return new CreateNewTodoTask() {

            @Override
            protected Long saveTodo(Todo todo) {
                return todoDao.insert(todo);
            }
        }.getAsLiveData(todo);
    }

    public LiveData<List<Todo>> fetchTodoList(long userId) {
        return todoDao.getTodoById(userId);
    }

}
