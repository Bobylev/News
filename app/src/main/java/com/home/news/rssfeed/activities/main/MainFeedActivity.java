package com.home.news.rssfeed.activities.main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.home.news.rssfeed.R;
import com.home.news.rssfeed.activities.base.BaseActivity;
import com.home.news.rssfeed.activities.main.adapter.MainFeedRecyclerAdapter;
import com.home.news.rssfeed.network.data.Article;


import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainFeedActivity extends BaseActivity implements MainFeedContracts.View, SwipeRefreshLayout.OnRefreshListener{

    @Inject
    MainFeedPresenter presenter;

    Disposable mDisposable;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Snackbar errorView;

    private MainFeedRecyclerAdapter mMainFeedRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);
        RecyclerView mRecyclerView = findViewById(R.id.newsRecyclerList);
        mMainFeedRecyclerAdapter = new MainFeedRecyclerAdapter(new ArrayList<Article>());
        mRecyclerView.setAdapter(mMainFeedRecyclerAdapter);
        presenter.LoadNews();
        errorView = Snackbar.make(findViewById(R.id.mainConstraint), "", Snackbar.LENGTH_SHORT);
        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);

        mDisposable = mMainFeedRecyclerAdapter.getPositionClicks().subscribe(new Consumer<Article>() {
            @Override
            public void accept(Article article){
                presenter.ItemClick(article);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.LoadNews();
    }

    @Override
    public void ShowData(ArrayList<Article> newsList) {
        mSwipeRefreshLayout.setRefreshing(false);
        mMainFeedRecyclerAdapter.updateData(newsList);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        presenter.LoadNews();
    }

    @Override
    public void ShowError(String message) {
        errorView.setText(message).show();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
