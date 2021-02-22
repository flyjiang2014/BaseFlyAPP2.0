package com.base.flyjiang.baseflyapp.view;

import android.os.Bundle;

import com.base.flyjiang.baseflyapp.R;
import com.base.flyjiang.baseflyapp.base.SimpleActivity;
import com.base.flyjiang.baseflyapp.http.cache.RxCache;

public class Main2Activity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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


//    @OnClick(R.id.btn_01)
//    public void onViewClicked() {
//        EventBus.getDefault().post("123");
//        finish();
//    }
}
