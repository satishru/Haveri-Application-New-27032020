package com.example.myapplication.di.components;

import android.app.Application;

import com.example.myapplication.HaveriApplication;
import com.example.myapplication.di.builder.ActivityBuilder;
import com.example.myapplication.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(HaveriApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
