package com.shubham.todo.data.prefs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.shubham.todo.TodoApplication;
import com.shubham.todo.util.Constants;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";

    private SharedPreferences sharedPreferences;

    public AppPreferencesHelper(SharedPreferences sharedPreference) {
        this.sharedPreferences = sharedPreference;
    }

    @Override
    public Long getUserId() {
        return sharedPreferences.getLong(PREF_KEY_USER_ID, Constants.LOGOUT);
    }

    @Override
    public void setuserId(Long userId) {
        sharedPreferences.edit().putLong(PREF_KEY_USER_ID, userId).apply();
    }
}
