package com.home.news.rssfeed.activities.modules;

import com.home.news.rssfeed.activities.main.MainFeedActivity;
import com.home.news.rssfeed.activities.main.MainFeedContracts;
import com.home.news.rssfeed.activities.main.MainFeedInteractor;
import com.home.news.rssfeed.activities.main.MainFeedPresenter;
import com.home.news.rssfeed.activities.main.MainFeedRouter;
import com.home.news.rssfeed.application.ActivityScope;

import dagger.Binds;
import dagger.Module;


@Module
public interface MainFeedModule {

    @ActivityScope
    @Binds
    MainFeedContracts.View provideMainFeedView(MainFeedActivity activity);

    @ActivityScope
    @Binds
    MainFeedContracts.Presenter provdeMainFeedPresenter(MainFeedPresenter presenter);

    @ActivityScope
    @Binds
    MainFeedContracts.Router provideMainFeedRouter(MainFeedRouter router);

    @ActivityScope
    @Binds
    MainFeedContracts.Interactor provideMainFeedInteractor(MainFeedInteractor interactor);
}
