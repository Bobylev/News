package com.home.news.rssfeed.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.home.news.rssfeed.network.data.Article;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Article> news);

    @Query("DELETE FROM articles")
    void clearTable();

    @Query("SELECT * FROM articles ORDER BY id DESC LIMIT 20")
    Single<List<Article>> selectNews();

}
