package application.haveri.tourism.di.components;

import android.app.Application;

import application.haveri.tourism.HaveriApplication;
import application.haveri.tourism.di.builder.ActivityBuilder;
import application.haveri.tourism.di.module.AppModule;

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
