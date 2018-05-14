package com.home.news.rssfeed.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.home.news.rssfeed.network.data.Article;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
