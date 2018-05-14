package com.home.news.rssfeed.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.home.news.rssfeed.activities.main.MainFeedActivity;
import com.home.news.rssfeed.activities.modules.MainFeedModule;

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

    @Provides
    @Singleton
    public static Context provideContext(Application application) {
        return application;
    }
    @ActivityScope
    @ContributesAndroidInjector(modules = {MainFeedModule.class})
    abstract MainFeedActivity contributesMainFeedActivity();

}