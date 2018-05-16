package com.home.news.rssfeed.activities.main;

import com.home.news.rssfeed.activities.base.BaseInteractor;
import com.home.news.rssfeed.database.NewsDatabase;
import com.home.news.rssfeed.network.ApiService;
import com.home.news.rssfeed.network.data.Article;
import com.home.news.rssfeed.network.data.NewsFeedResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
                .map(new Function<NewsFeedResponse, NewsFeedResponse>() {
                    @Override
                    public NewsFeedResponse apply(NewsFeedResponse newsFeedResponse) {
                        for (Article article: newsFeedResponse.articles) {
                            article.id = article.publishedAt.getTime();
                        }
                        return newsFeedResponse;
                    }
                })
                .subscribe(new Consumer<NewsFeedResponse>() {
                    @Override
                    public void accept(NewsFeedResponse newsFeedResponse){
                        if(newsFeedResponse.status.equals("ok")) {
                            saveNews(newsFeedResponse);
                            output.NewsFeedResult(newsFeedResponse.articles);
                        } else {
                            output.Error(newsFeedResponse.status);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable){
                        fromCache();
                        output.Error(throwable.getMessage());
                    }
                })
        );
    }

    private void saveNews(final NewsFeedResponse newsFeedResponse) {
        Disposable d = Observable.fromCallable(new Callable<NewsFeedResponse>() {
            @Override
            public NewsFeedResponse call(){
                return newsFeedResponse;
            }
        }).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Consumer<NewsFeedResponse>() {
            @Override
            public void accept(NewsFeedResponse newsFeedResponse){
                database.newsDao().clearTable();
                database.newsDao().insert(newsFeedResponse.articles);
            }
        });
        addDisposable(d);
    }

    private void fromCache() {
        Disposable d = database.newsDao().selectNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Article>>() {
                    @Override
                    public void accept(List<Article> articles){
                        output.NewsFeedResult(new ArrayList<>(articles));
                    }
                });
        addDisposable(d);
    }
}

