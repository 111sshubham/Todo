package com.shubham.todo.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TodoDao {

    /**
     * insert todo into db
     *
     * @param todo
     * @return
     */
    @Insert(onConflict = REPLACE)
    Long insert(Todo todo);

    /**
     * update existing todo
     *
     * @param todo
     */
    @Update
    void update(Todo todo);

    /**
     * delete todo from db
     *
     * @param todo
     */
    @Delete
    void delete(Todo todo);

    @Query("SELECT * FROM todo WHERE userId = :userId")
    LiveData<List<Todo>> getTodoById(long userId);

}
