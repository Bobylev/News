package com.home.news.rssfeed.application;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobTrigger;
import com.firebase.jobdispatcher.Trigger;
import com.home.news.rssfeed.database.DatabaseModule;
import com.home.news.rssfeed.network.NetworkModule;
import com.home.news.rssfeed.services.NewsJob;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import okhttp3.OkHttpClient;

public class RssApplication extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .networkModule(new NetworkModule("https://newsapi.org/v2/"))
                .databaseModule(new DatabaseModule())
                .build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(this.getApplicationContext().getCacheDir())
                .setBaseDirectoryName("fresco")
                .setMaxCacheSize(100 * ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(50 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(10 * ByteConstants.MB)
                .setVersion(1)
                .build();

        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this.getApplicationContext(), new OkHttpClient.Builder().build())
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();

        Fresco.initialize(this.getApplicationContext(), config);

        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job newsJob = dispatcher.newJobBuilder()
                .setService(NewsJob.class)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(600,700))
                .setReplaceCurrent(true)
                .setTag("newsLoaderJob")
                .build();

        dispatcher.mustSchedule(newsJob);
    }

}
