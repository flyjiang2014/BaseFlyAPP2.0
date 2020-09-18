package com.base.flyjiang.baseflyapp.view;

import android.os.Bundle;
import android.widget.Button;

import com.base.flyjiang.baseflyapp.R;
import com.base.flyjiang.baseflyapp.base.SimpleActivity;
import com.base.flyjiang.baseflyapp.http.cache.RxCache;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends SimpleActivity {

    @BindView(R.id.btn_01)
    Button btn01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }

    @Override
    public int setBaseContentView() {
        return R.layout.activity_main2;
    }

    @Override
    public void init() {
        RxCache rxchahe = new RxCache.Builder()
                .setDebug(false)
                .build();

    }


    @OnClick(R.id.btn_01)
    public void onViewClicked() {
        EventBus.getDefault().post("123");
        finish();
    }
}
