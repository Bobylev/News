package com.home.news.rssfeed.util;

import android.util.Log;

import javax.inject.Inject;

public class Logger {

    @Inject
    public Logger(){}

    public void logEvent(String msg){
        Log.i("NEWS_APP", msg);
    }

}
