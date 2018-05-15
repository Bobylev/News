package com.home.news.rssfeed.activities.base;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseRouter<A extends AppCompatActivity> implements BaseContracts.Router {

    protected A activity;
    private final FragmentManager fragmentManager;


    public BaseRouter(A activity) {
        this.activity = activity;
        this.fragmentManager = activity.getSupportFragmentManager();
    }

    @Override
    public void unregister() {
        activity = null;
    }

    public void NavigateBack(){
        activity.finish();
    }
}