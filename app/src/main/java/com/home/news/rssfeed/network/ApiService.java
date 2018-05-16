package com.home.news.rssfeed.network;

import com.home.news.rssfeed.network.data.NewsFeedResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("top-headlines?country=us")
    Observable<NewsFeedResponse> loadNewsList();
}
