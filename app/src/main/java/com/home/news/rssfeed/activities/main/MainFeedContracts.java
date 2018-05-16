package com.home.news.rssfeed.activities.main;

import com.home.news.rssfeed.activities.base.BaseContracts;
import com.home.news.rssfeed.network.data.Article;

import java.util.ArrayList;

public interface MainFeedContracts {
    interface View extends BaseContracts.View {
        void ShowData(ArrayList<Article> newsList);
        void ShowError(String message);
    }

    @SuppressWarnings("unused")
    interface Presenter extends BaseContracts.Presenter {
        void LoadNews();
        void UpdateNews();
        void ItemClick(Article article);

    }

    interface Interactor extends BaseContracts.Interactor {
        void LoadNewsData();
    }

    interface InteractorOutput extends BaseContracts.InteractorOutput {
        void NewsFeedResult(ArrayList<Article> newsList);
        void Error(String message);
    }

    interface Router extends BaseContracts.Router {
        void NavigateToDetail(Article article);
    }
}

