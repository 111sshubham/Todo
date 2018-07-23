package com.shubham.todo.di;

import android.app.Application;

import com.shubham.todo.TodoApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final TodoApplication application;

    public ApplicationModule(TodoApplication application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return this.application;
    }

    @Provides
    public TodoApplication provideTodoApplication() {
        return this.application;
    }

}
