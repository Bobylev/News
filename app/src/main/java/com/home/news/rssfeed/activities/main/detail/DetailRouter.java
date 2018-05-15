package com.home.news.rssfeed.activities.main.detail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.home.news.rssfeed.activities.base.BaseRouter;
import com.home.news.rssfeed.util.Logger;

import javax.inject.Inject;

public class DetailRouter extends BaseRouter<DetailActivity> implements DetailContracts.Router {

    @Inject
    public DetailRouter(DetailActivity activity) {
        super(activity);
    }

    @Inject
    Logger log;

    @Override
    public void navigateToUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        PackageManager packageManager = activity.getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
            activity.startActivity(intent);
        } else {
            log.logEvent("No any application to open link");
        }
    }
}
