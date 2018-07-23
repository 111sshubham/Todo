package com.shubham.todo.data;

import android.arch.lifecycle.LiveData;

import com.shubham.todo.util.Resource;

public class UserRepository {
    private final UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public LiveData<Resource<Long>> createUser(User user){
        return new CreateNewUserTask(){
            @Override
            protected Long saveUser(User user) {
                return userDao.insertUser(user);
            }
        }.getAsLiveData(user);
    }

    public LiveData<User> fetchUser(String email, String password) {
        return userDao.getUser(email, password);
    }
}
