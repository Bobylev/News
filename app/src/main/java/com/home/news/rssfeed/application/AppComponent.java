package com.home.news.rssfeed.application;


import com.home.news.rssfeed.database.DatabaseModule;
import com.home.news.rssfeed.network.NetworkModule;
import com.home.news.rssfeed.services.ServiceModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, NetworkModule.class, DatabaseModule.class, ServiceModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(RssApplication application);
        Builder networkModule(NetworkModule networkModule);
        Builder databaseModule(DatabaseModule databaseModule);
        AppComponent build();
    }
}
