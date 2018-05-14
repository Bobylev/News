package com.home.news.rssfeed.network;

import com.home.news.rssfeed.network.data.NewsFeedResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("top-headlines?country=us&apiKey=4c9e9e6c6b3f473aa609563f3d67b5a0")
    Observable<NewsFeedResponse> loadNewsList();
}
