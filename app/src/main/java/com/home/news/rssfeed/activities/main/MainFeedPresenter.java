package com.home.news.rssfeed.activities.main;

import com.home.news.rssfeed.activities.base.BasePresenter;
import com.home.news.rssfeed.network.data.Article;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainFeedPresenter  extends BasePresenter<MainFeedContracts.View,
        MainFeedContracts.Interactor,
        MainFeedContracts.Router>
        implements MainFeedContracts.Presenter,
        MainFeedContracts.InteractorOutput {


    @Inject
    public MainFeedPresenter(MainFeedContracts.View view, MainFeedContracts.Interactor interactor, MainFeedContracts.Router router) {
        super(view, interactor, router);
    }

    @Override
    public void LoadNews() {
        interactor.LoadNewsData();
    }

    @Override
    public void UpdateNews() {
        interactor.LoadNewsData();
    }

    @Override
    public void ItemClick(Long id) {
        router.NavigateToDetail();
    }

    @Override
    public void NewsFeedResult(ArrayList<Article> newsList) {
        view.ShowData(newsList);

    }
}
