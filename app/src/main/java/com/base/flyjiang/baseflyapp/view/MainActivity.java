package com.base.flyjiang.baseflyapp.view;

import android.os.Bundle;
import com.base.flyjiang.baseflyapp.R;
import com.base.flyjiang.baseflyapp.base.BaseActivity;
import com.base.flyjiang.baseflyapp.contract.MainContract;
import com.base.flyjiang.baseflyapp.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setBaseContentView() {
        setIsShowTitle(false);
        return R.layout.activity_main;
    }

    @Override
    public void init() {
    }

}
