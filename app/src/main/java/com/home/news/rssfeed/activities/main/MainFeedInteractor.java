package com.home.news.rssfeed.activities.main;

import com.home.news.rssfeed.activities.base.BaseInteractor;
import com.home.news.rssfeed.network.ApiService;
import com.home.news.rssfeed.network.data.NewsFeedResponse;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainFeedInteractor extends BaseInteractor<MainFeedContracts.InteractorOutput>
        implements MainFeedContracts.Interactor {

    @Inject
    ApiService service;

    @Inject
    public MainFeedInteractor() {

    }

    @Override
    public void LoadNewsData() {
        compositeDisposable.add(service.loadNewsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<NewsFeedResponse>() {
                    @Override
                    public void accept(NewsFeedResponse newsFeedResponse) throws Exception {
                        output.NewsFeedResult(newsFeedResponse.articles);
                    }
                })

        );
    }
}

