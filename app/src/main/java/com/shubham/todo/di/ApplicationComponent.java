package com.shubham.todo.di;

import com.shubham.todo.data.prefs.AppPreferencesHelper;
import com.shubham.todo.ui.HomeActivity;
import com.shubham.todo.ui.LoginActivity;
import com.shubham.todo.ui.SignUpActivity;
import com.shubham.todo.ui.TodoActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DataBaseModule.class,
        ViewModelModule.class,
        SharedPrefModule.class
})
public interface ApplicationComponent {
    void inject(LoginActivity loginActivity);

    void inject(SignUpActivity signUpActivity);

    void inject(TodoActivity todoActivity);

    void inject(HomeActivity homeActivity);

}
