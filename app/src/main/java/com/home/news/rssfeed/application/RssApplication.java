package com.home.news.rssfeed.application;

import com.home.news.rssfeed.database.DatabaseModule;
import com.home.news.rssfeed.network.NetworkModule;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class RssApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .networkModule(new NetworkModule("https://newsapi.org/v2/"))
                .databaseModule(new DatabaseModule())
                .build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
