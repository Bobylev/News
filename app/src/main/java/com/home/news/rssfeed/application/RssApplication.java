package com.home.news.rssfeed.application;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class RssApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
