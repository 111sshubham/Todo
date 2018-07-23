package com.shubham.todo.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.shubham.todo.util.Resource;

public abstract class CreateNewTodoTask {
    private MutableLiveData<Resource<Long>> data = new MutableLiveData<>();

    public CreateNewTodoTask() {
        data.setValue(Resource.loading(null));
    }

    public LiveData<Resource<Long>> getAsLiveData(Todo todo) {
        createTodoTask(todo);
        return data;
    }

    private void createTodoTask(Todo todo){
        new AsyncTask<Void,Void,Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                return saveTodo(todo);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                data.setValue(Resource.success(aLong));
            }
        }.execute();
    }

    protected abstract Long saveTodo(Todo todo);
}
