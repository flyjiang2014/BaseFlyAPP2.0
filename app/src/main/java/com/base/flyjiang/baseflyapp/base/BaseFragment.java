package com.base.flyjiang.baseflyapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.base.flyjiang.baseflyapp.app.App;
import com.base.flyjiang.baseflyapp.dagger.component.DaggerFragmentComponent;
import com.base.flyjiang.baseflyapp.dagger.component.FragmentComponent;
import com.base.flyjiang.baseflyapp.dagger.module.FragmentModule;
import com.base.flyjiang.baseflyapp.widget.loading.LoadingLayout;

import javax.inject.Inject;

/**
 * Created by flyjiang on 2019/8/2.
 * MVP Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroyView();
    }
    protected abstract void initInject();

    @Override
    public void stateError() {
        mLoadingLayout.setStatus(LoadingLayout.Error);
    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {
        mLoadingLayout.setStatus(LoadingLayout.Success);
    }

    @Override
    public void showDialogLoading() {
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    @Override
    public void dismissDialogLoading() {
        loadingDialog.dismiss();
    }
}