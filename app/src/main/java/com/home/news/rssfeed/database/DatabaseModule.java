package com.home.news.rssfeed.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.home.news.rssfeed.network.data.Article;
import com.home.news.rssfeed.network.data.NewsFeedResponse;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private Context mContext;

    @Provides
    @Singleton
    NewsDatabase  provideDatabase(Context context) {
        mContext = context;
        return Room.databaseBuilder(context, NewsDatabase.class,"df").addCallback(callback).build();
    }

    private RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        public void onCreate (@NonNull SupportSQLiteDatabase db) {
            Log.i("NEWS_APP","dbCreated");
            String json = null;
            try {
                InputStream inputStream = mContext.getAssets().open("articles.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                int count = inputStream.read(buffer);
                inputStream.close();
                if (count > 0) {
                    json = new String(buffer, "UTF-8");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (json != null){
                NewsFeedResponse response = new Gson().fromJson(json, NewsFeedResponse.class);
                String INSERT_ROW = "INSERT or replace INTO articles (id, author, title, description, url, url_to_image, published_at) VALUES(?,?,?,?,?,?,?)" ;
                for (Article art: response.articles)
                {
                    db.execSQL(INSERT_ROW, new Object[]{art.id, art.author, art.title, art.description, art.url, art.urlToImage, art.publishedAt});
                }
            }
        }
        public void onOpen (@NonNull SupportSQLiteDatabase db) {

        }
    };
}
