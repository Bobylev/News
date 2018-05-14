package com.home.news.rssfeed.activities.main;

import android.os.Bundle;

import com.home.news.rssfeed.R;
import com.home.news.rssfeed.activities.base.BaseActivity;
import com.home.news.rssfeed.network.data.Article;


import java.util.ArrayList;

import javax.inject.Inject;


public class MainFeedActivity extends BaseActivity implements MainFeedContracts.View{

    @Inject
    MainFeedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        presenter.LoadNews();
    }


    @Override
    public void ShowData(ArrayList<Article> newsList) {

    }
}
