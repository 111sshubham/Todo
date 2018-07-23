package com.shubham.todo.di;

import android.arch.lifecycle.ViewModelProvider;

import com.shubham.todo.data.Repository;
import com.shubham.todo.viewmodel.CustomViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Provides
    @Singleton
    public ViewModelProvider.Factory provideViewModelFactory(Repository repository){
        return new CustomViewModelFactory(repository);
    }
}
