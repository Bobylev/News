package com.home.news.rssfeed.activities.main;

import com.home.news.rssfeed.activities.base.BaseInteractor;
import com.home.news.rssfeed.database.NewsDatabase;
import com.home.news.rssfeed.network.ApiService;
import com.home.news.rssfeed.network.data.NewsFeedResponse;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainFeedInteractor extends BaseInteractor<MainFeedContracts.InteractorOutput>
        implements MainFeedContracts.Interactor {

    @Inject
    ApiService service;

    @Inject
    NewsDatabase database;

    @Inject
    public MainFeedInteractor() {

    }

    @Override
    public void LoadNewsData() {
        compositeDisposable.add(service.loadNewsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<NewsFeedResponse>() {
                    @Override
                    public void accept(NewsFeedResponse newsFeedResponse) throws Exception {
                        saveNews(newsFeedResponse);
                    }
                })
                .subscribe(new Consumer<NewsFeedResponse>() {
                    @Override
                    public void accept(NewsFeedResponse newsFeedResponse) throws Exception {
                        output.NewsFeedResult(newsFeedResponse.articles);
                    }
                })
        );
    }

    private void saveNews(NewsFeedResponse newsFeedResponse){
        //Observable.fromCallable(database.newsDao().insert(newsFeedResponse.articles)).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe();
    }
}

