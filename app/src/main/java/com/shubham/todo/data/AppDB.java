package com.shubham.todo.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class, Todo.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract TodoDao todoDao();

}
