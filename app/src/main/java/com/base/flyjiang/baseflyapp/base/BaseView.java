package com.base.flyjiang.baseflyapp.base;

/**
 * Created by flyjiang on 2019/8/2.
 * View基类
 */
public interface BaseView {

    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();

    void showDialogLoading();

    void dismissDialogLoading();

}
