package com.shubham.todo.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    /**
     * add new user into db
     *
     * @param user
     * @return id
     */
    @Insert()
    Long insertUser(User user);

    /**
     * update user
     *
     * @param user
     */
    @Update
    void updateUser(User user);

    /**
     * delete user from db
     *
     * @param user
     */
    @Delete
    void delete(User user);

    /**
     * fetch user by email and password
     *
     * @param email
     * @param password
     * @return
     */
    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    LiveData<User> getUser(String email, String password);

   /* @Query("SELECT * FROM User")
    LiveData<List<User>> getUsers();*/


}
