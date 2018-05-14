package com.home.news.rssfeed.activities.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseInteractor<O extends BaseContracts.InteractorOutput> implements BaseContracts.Interactor {

    protected O output;
    public BaseInteractor() {}
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void setInteractorOutput(Object interactorOutput) {
        if (interactorOutput instanceof BaseContracts.InteractorOutput) {
            this.output = (O) interactorOutput;
        }
    }

    @Override
    public void addDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }


    @Override
    public void unregister() {
        this.output = null;
        this.compositeDisposable.dispose();
        this.compositeDisposable = null;
    }


}
