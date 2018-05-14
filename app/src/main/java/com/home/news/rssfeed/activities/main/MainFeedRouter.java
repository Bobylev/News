package com.home.news.rssfeed.activities.main;

import android.content.Intent;

import com.home.news.rssfeed.activities.base.BaseRouter;
import com.home.news.rssfeed.activities.main.detail.DetailActivity;

import javax.inject.Inject;

public class MainFeedRouter extends BaseRouter<MainFeedActivity> implements MainFeedContracts.Router {

    @Inject
    public MainFeedRouter(MainFeedActivity activity) {
        super(activity);
    }

    @Override
    public void NavigateToDetail() {
        Intent intent = new Intent(activity , DetailActivity.class);
        activity.startActivity(intent);
    }
}
