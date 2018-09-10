package com.duvarapps.daggerandroidcomponents.dagger;

import android.app.Application;

import com.duvarapps.daggerandroidcomponents.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ActivityBinderModule.class})
public interface AppComponent
{
    @Component.Builder
    interface Builder
    {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    // this method is called by the application class (App.java)
    void inject(App app);
}
