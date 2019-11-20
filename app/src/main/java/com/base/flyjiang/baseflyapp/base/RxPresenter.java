package com.base.flyjiang.baseflyapp.base;

import com.base.flyjiang.baseflyapp.http.api.RxBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by flyjiang on 2019/8/2.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class RxPresenter <T extends BaseView> implements BasePresenter<T>  {

    protected CompositeDisposable mCompositeDisposable;

    protected T mView;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected void addSubscribeWithDialog(Disposable subscription) {
        mView.showDialogLoading();
        addSubscribe(subscription);
    }

    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxBus.getDefault().toDefaultFlowable(eventType, act));
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        unSubscribe();
    }
}
