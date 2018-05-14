package com.home.news.rssfeed.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    NewsDatabase  provideDatabase(Context context) {
        return Room.databaseBuilder(context, NewsDatabase.class,"df").build();
    }
}
