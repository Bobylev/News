package com.home.news.rssfeed.activities.base;

public abstract class BasePresenter<V extends BaseContracts.View,
        I extends  BaseContracts.Interactor,
        R extends BaseContracts.Router> implements BaseContracts.Presenter, BaseContracts.InteractorOutput {

    protected V view;
    protected I interactor;
    protected R router;

    public BasePresenter(V view, I interactor, R router) {
        this.view = view;
        this.interactor = interactor;
        this.router = router;
        this.interactor.setInteractorOutput(this);
    }

    @Override
    public void onDestroy() {
        view = null;

        if (interactor != null) {
            interactor.unregister();
        }
        interactor = null;

        if (router != null) {
            router.unregister();
        }

        router = null;
    }
}
