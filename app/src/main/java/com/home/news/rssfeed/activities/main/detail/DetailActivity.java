package com.home.news.rssfeed.activities.main.detail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.home.news.rssfeed.R;
import com.home.news.rssfeed.activities.base.BaseActivity;
import com.home.news.rssfeed.network.data.Article;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity implements DetailContracts.View{

    @Inject
    DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar bar = getSupportActionBar();
        if(bar != null) {
            bar.setTitle("News");
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }

        Intent i = getIntent();
        final Article article = i.getParcelableExtra("article");
        ((SimpleDraweeView)findViewById(R.id.detail_view_img)).setImageURI(Uri.parse(article.urlToImage));
        ((TextView)findViewById(R.id.detail_title)).setText(article.title);
        ((TextView)findViewById(R.id.detail_description)).setText(article.description);
        ((TextView)findViewById(R.id.detail_author)).setText(article.author);
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault());
        ((TextView)findViewById(R.id.detail_date)).setText(fmt.format(article.publishedAt));
        ((TextView)findViewById(R.id.detail_link)).setText(article.url);
        findViewById(R.id.detail_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.linkClicked(article.url);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
