package com.home.news.rssfeed.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.home.news.rssfeed.activities.main.MainFeedActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    @Provides
    @Singleton
    public static SharedPreferences providePreferences(Application application) {
        return application.getSharedPreferences("STORE",
                Context.MODE_PRIVATE);
    }

    @ActivityScope
    @ContributesAndroidInjector()
    abstract MainFeedActivity contributesMainFeedActivity();

}