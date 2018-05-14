package com.home.news.rssfeed.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.home.news.rssfeed.network.data.Article;

import java.util.List;
import java.util.concurrent.Callable;

@Dao
public interface NewsDao {
    @Insert
    List<Long> insert(List<Article> news);

}
