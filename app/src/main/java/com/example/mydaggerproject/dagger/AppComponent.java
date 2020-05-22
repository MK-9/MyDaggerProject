package com.example.mydaggerproject.dagger;

import android.app.Application;

import com.example.mydaggerproject.AppController;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.android.AndroidInjectionModule;

@Component(modules = {
        ApiModule.class,
        DbModule.class,
        ViewModelModule.class,
        ActivityModule.class,
        AndroidInjectionModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder setApp(Application app);

        AppComponent build();
    }

    void inject(AppController appController);
}
