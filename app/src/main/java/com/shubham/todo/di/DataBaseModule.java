package com.shubham.todo.di;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;

import com.shubham.todo.data.AppDB;
import com.shubham.todo.data.Repository;
import com.shubham.todo.data.Todo;
import com.shubham.todo.data.TodoDao;
import com.shubham.todo.data.TodoRepository;
import com.shubham.todo.data.UserDao;
import com.shubham.todo.data.UserRepository;
import com.shubham.todo.data.prefs.AppPreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {
    private final AppDB db;

    public DataBaseModule(Application application) {
        this.db = Room.databaseBuilder(
                application,
                AppDB.class,
                "todo.db"
        ).build();
    }

    @Provides
    @Singleton
    public AppDB provideAppDB() {
        return this.db;
    }

    @Provides
    @Singleton
    public Repository provideRepository(TodoRepository todoRepository, UserRepository userRepository,
                                        AppPreferencesHelper preferencesHelper) {
        return new Repository(todoRepository,userRepository,preferencesHelper);
    }

    @Provides
    @Singleton
    public TodoRepository provideTodoRepository(TodoDao todoDao){
        return new TodoRepository(todoDao);
    }

    @Provides
    @Singleton
    public UserRepository provideUserRepository(UserDao userDao){
        return new UserRepository(userDao);
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(AppDB db) {
        return db.userDao();
    }

    @Provides
    @Singleton
    public TodoDao provideTodoDao(AppDB db) {
        return db.todoDao();
    }
}
