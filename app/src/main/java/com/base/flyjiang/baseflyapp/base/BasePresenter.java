package com.base.flyjiang.baseflyapp.base;

/**
 * Created by flyjiang on 2019/8/2.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
