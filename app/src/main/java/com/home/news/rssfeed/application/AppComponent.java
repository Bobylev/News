package com.home.news.rssfeed.application;


import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(RssApplication application);
        AppComponent build();
    }
}
