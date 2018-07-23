package com.shubham.todo;

import android.app.Application;

import com.shubham.todo.di.ApplicationComponent;
import com.shubham.todo.di.ApplicationModule;
import com.shubham.todo.di.DaggerApplicationComponent;
import com.shubham.todo.di.DataBaseModule;
import com.shubham.todo.di.SharedPrefModule;
import com.shubham.todo.di.ViewModelModule;

public class TodoApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.
                builder().
                applicationModule(new ApplicationModule(this)).
                dataBaseModule(new DataBaseModule(this)).
                viewModelModule(new ViewModelModule()).
                sharedPrefModule(new SharedPrefModule()).
                build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
