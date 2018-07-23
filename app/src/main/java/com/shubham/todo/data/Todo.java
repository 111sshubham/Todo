package com.shubham.todo.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "todo", foreignKeys = @ForeignKey(
        entity = User.class,
        parentColumns = "_id",
        childColumns = "userId",
        onDelete = CASCADE,
        onUpdate = CASCADE)
)
public class Todo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public long id;

    public String todo;

    public long userId;

    public Todo(String todo, long userId) {
        this.todo = todo;
        this.userId = userId;
    }
}
