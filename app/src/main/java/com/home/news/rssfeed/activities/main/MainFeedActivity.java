package com.home.news.rssfeed.activities.main;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.home.news.rssfeed.R;
import com.home.news.rssfeed.activities.base.BaseActivity;
import com.home.news.rssfeed.activities.main.adapter.MainFeedRecyclerAdapter;
import com.home.news.rssfeed.network.data.Article;


import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainFeedActivity extends BaseActivity implements MainFeedContracts.View{

    @Inject
    MainFeedPresenter presenter;

    Disposable mDisposable;

    private MainFeedRecyclerAdapter mMainFeedRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);
        RecyclerView mRecyclerView = findViewById(R.id.newsRecyclerList);
        mMainFeedRecyclerAdapter = new MainFeedRecyclerAdapter(new ArrayList<Article>());
        mRecyclerView.setAdapter(mMainFeedRecyclerAdapter);
        presenter.LoadNews();

        mDisposable = mMainFeedRecyclerAdapter.getPositionClicks().subscribe(new Consumer<Article>() {
            @Override
            public void accept(Article article){
                long t = article.id;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }

    @Override
    public void ShowData(ArrayList<Article> newsList) {
        mMainFeedRecyclerAdapter.updateData(newsList);
    }
}
