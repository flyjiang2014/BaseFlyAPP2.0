package com.base.flyjiang.baseflyapp.component;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.base.flyjiang.baseflyapp.R;
import com.base.flyjiang.baseflyapp.widget.loading.LoadingLayout;

/**
 * Created by flyjiang on 2019/8/2.
 */

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
               initApplication();
            }
        }
    }

    private void initApplication() {
        LoadingLayout.getConfig()
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("抱歉，暂无数据")
                .setNoNetworkText("请检查网络设置")
                .setErrorImage(R.mipmap.error)
                .setEmptyImage(R.mipmap.empty)
                .setNoNetworkImage(R.mipmap.no_network)
                .setAllTipTextSize(16)
                .setReloadButtonText("重新加载")
                .setReloadButtonTextSize(14)
                .setReloadButtonWidthAndHeight(150,40);
    }
}
