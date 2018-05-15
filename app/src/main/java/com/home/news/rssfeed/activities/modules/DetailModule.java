package com.home.news.rssfeed.activities.modules;

import com.home.news.rssfeed.activities.main.detail.DetailActivity;
import com.home.news.rssfeed.activities.main.detail.DetailContracts;
import com.home.news.rssfeed.activities.main.detail.DetailInteractor;
import com.home.news.rssfeed.activities.main.detail.DetailPresenter;
import com.home.news.rssfeed.activities.main.detail.DetailRouter;
import com.home.news.rssfeed.application.ActivityScope;

import dagger.Binds;
import dagger.Module;

@Module
public interface DetailModule {
    @ActivityScope
    @Binds
    DetailContracts.View provideDetailView(DetailActivity activity);

    @ActivityScope
    @Binds
    DetailContracts.Presenter provideDetailPresenter(DetailPresenter presenter);

    @ActivityScope
    @Binds
    DetailContracts.Router provideDetailRouter(DetailRouter router);

    @ActivityScope
    @Binds
    DetailContracts.Interactor provideDetailInteractor(DetailInteractor interactor);

}
