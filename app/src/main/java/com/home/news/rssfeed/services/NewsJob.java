package com.home.news.rssfeed.services;

import android.content.Intent;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.home.news.rssfeed.network.ApiService;

import javax.inject.Inject;


public class NewsJob extends JobService {
    @Inject
    ApiService service;

    @Override
    public boolean onStartJob(JobParameters job) {
        Intent i= new Intent(getApplicationContext(), NewsService.class);
        getApplication().startService(i);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return true;
    }
}

