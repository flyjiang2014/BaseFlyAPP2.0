package com.base.flyjiang.baseflyapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.base.flyjiang.baseflyapp.R;
import com.base.flyjiang.baseflyapp.base.BaseActivity;
import com.base.flyjiang.baseflyapp.contract.MainContract;
import com.base.flyjiang.baseflyapp.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //     EventBus.getDefault().register(this);
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

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEvent(String data) {
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        startActivity(new Intent(mContext, Main2Activity.class));
    }

}
