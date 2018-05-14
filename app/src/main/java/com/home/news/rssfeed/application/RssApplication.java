package com.home.news.rssfeed.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.home.news.rssfeed.database.DatabaseModule;
import com.home.news.rssfeed.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Provides;
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
