package com.home.news.rssfeed.activities.main.detail;

import com.home.news.rssfeed.activities.base.BasePresenter;

import javax.inject.Inject;

public class DetailPresenter extends BasePresenter<DetailContracts.View,
        DetailContracts.Interactor,
        DetailContracts.Router>
        implements DetailContracts.Presenter,
        DetailContracts.InteractorOutput {


    @Inject
    public DetailPresenter(DetailContracts.View view, DetailContracts.Interactor interactor, DetailContracts.Router router) {
        super(view, interactor, router);
    }


    @Override
    public void linkClicked(String url) {
        router.navigateToUrl(url);
    }
}


