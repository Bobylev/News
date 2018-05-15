package com.home.news.rssfeed.services;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.home.news.rssfeed.database.NewsDatabase;
import com.home.news.rssfeed.network.ApiService;
import com.home.news.rssfeed.network.data.NewsFeedResponse;
import com.home.news.rssfeed.util.Logger;

import javax.inject.Inject;

import dagger.android.DaggerIntentService;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class NewsService extends DaggerIntentService{

    @Inject
    ApiService service;

    @Inject
    NewsDatabase database;

    @Inject
    Logger logger;


    public NewsService() {
        super("newsService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        logger.logEvent("NewsService: try to update cache");
        Disposable d = service.loadNewsList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<NewsFeedResponse>() {
                    @Override
                    public void accept(NewsFeedResponse newsFeedResponse){
                        if(newsFeedResponse.status.equals("ok")) {
                            if(!newsFeedResponse.articles.isEmpty()) {
                                database.newsDao().clearTable();
                                database.newsDao().insert(newsFeedResponse.articles);
                                logger.logEvent("NewsService: cache updated");
                            }
                        }
                    }
                });
    }


}
