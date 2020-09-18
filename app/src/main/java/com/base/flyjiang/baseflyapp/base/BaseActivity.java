package com.base.flyjiang.baseflyapp.base;

import com.base.flyjiang.baseflyapp.app.App;
import com.base.flyjiang.baseflyapp.dagger.component.ActivityComponent;
import com.base.flyjiang.baseflyapp.dagger.component.DaggerActivityComponent;
import com.base.flyjiang.baseflyapp.dagger.module.ActivityModule;
import com.base.flyjiang.baseflyapp.widget.loading.LoadingLayout;
import javax.inject.Inject;

/**
 * Created by flyjiang on 2019/8/2.
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView{

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    protected abstract void initInject();

    @Override
    public void stateError() {
        getmLoadingLayout().setStatus(LoadingLayout.Error);
    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {
            getmLoadingLayout().setStatus(LoadingLayout.Success);
    }

    @Override
    public void showDialogLoading() {
        if (!loadingDialog.isShowing()&&!isFinishing()) {
            loadingDialog.show();
        }
    }

    @Override
    public void dismissDialogLoading() {
        loadingDialog.dismiss();
    }
}