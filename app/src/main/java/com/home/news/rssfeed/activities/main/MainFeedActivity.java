package com.home.news.rssfeed.activities.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.home.news.rssfeed.R;
import com.home.news.rssfeed.activities.base.BaseActivity;

import dagger.android.support.DaggerAppCompatActivity;

public class MainFeedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);
    }
}
