package com.shubham.todo.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.shubham.todo.util.Resource;

public abstract class CreateNewUserTask {

    private MutableLiveData<Resource<Long>> data = new MutableLiveData<>();

    public CreateNewUserTask() {
        data.setValue(Resource.loading(null));
    }

    public LiveData<Resource<Long>> getAsLiveData(User user) {
        createUserTask(user);
        return data;
    }

    private void createUserTask(User user){
        new AsyncTask<Void,Void,Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                return saveUser(user);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                data.setValue(Resource.success(aLong));
            }
        }.execute();
    }

    protected abstract Long saveUser(User user);
}
