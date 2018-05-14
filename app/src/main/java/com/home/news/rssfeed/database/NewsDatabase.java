package com.home.news.rssfeed.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.home.news.rssfeed.network.data.Article;

@Database(entities = {Article.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract NewsDao userDao();
}
