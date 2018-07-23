package com.shubham.todo.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.shubham.todo.data.prefs.AppPreferencesHelper;
import com.shubham.todo.data.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(Application context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    public AppPreferencesHelper provideAppPreferencesHelper(SharedPreferences sharedPreferences) {
        return new AppPreferencesHelper(sharedPreferences);
    }
}
