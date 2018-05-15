package com.home.news.rssfeed.services;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ServiceModule {
    @ContributesAndroidInjector
    NewsService contributeService();
}
