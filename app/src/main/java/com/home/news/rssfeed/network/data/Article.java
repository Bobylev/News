package com.home.news.rssfeed.network.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "articles")
public class Article {
    @PrimaryKey
    public Long id;
    public String author;
    public String title;
    public String description;
    public String url;
    @ColumnInfo(name = "url_to_image")
    public String urlToImage;
    @ColumnInfo(name = "published_at")
    public Date publishedAt;
}
