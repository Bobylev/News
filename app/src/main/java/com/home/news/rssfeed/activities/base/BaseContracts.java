package com.home.news.rssfeed.activities.base;

import io.reactivex.disposables.Disposable;

public class BaseContracts {
    public interface View {
    }

    public interface Presenter {
        void onDestroy();
    }

    public interface Interactor {
        void setInteractorOutput(Object interactorOutput);
        void unregister();
        void addDisposable(Disposable disposable);
    }

    public interface InteractorOutput {
    }

    public interface Router {
        void unregister();
    }
}
