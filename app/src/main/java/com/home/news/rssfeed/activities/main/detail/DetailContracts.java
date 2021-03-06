package com.home.news.rssfeed.activities.main.detail;

import com.home.news.rssfeed.activities.base.BaseContracts;

public interface DetailContracts {
    interface View extends BaseContracts.View {

    }

    @SuppressWarnings("unused")
    interface Presenter extends BaseContracts.Presenter {
        void linkClicked(String url);

    }

    interface Interactor extends BaseContracts.Interactor {

    }

    interface InteractorOutput extends BaseContracts.InteractorOutput {

    }

    interface Router extends BaseContracts.Router {
        void navigateToUrl(String url);

    }
}

