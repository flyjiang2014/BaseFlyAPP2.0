package com.base.flyjiang.baseflyapp.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.base.flyjiang.baseflyapp.R;
import com.base.flyjiang.baseflyapp.component.InitializeService;
import com.base.flyjiang.baseflyapp.dagger.component.AppComponent;
import com.base.flyjiang.baseflyapp.dagger.component.DaggerAppComponent;
import com.base.flyjiang.baseflyapp.dagger.module.AppModule;
import com.base.flyjiang.baseflyapp.dagger.module.HttpModule;
import com.base.flyjiang.baseflyapp.utils.DynamicTimeFormat;
import com.base.flyjiang.baseflyapp.utils.SharepreferenceUtil;
import com.base.flyjiang.baseflyapp.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * Created by flyjiang on 2019/8/2.
 */
public class App extends Application {

    public static AppComponent appComponent;
    private static App instance;
    static { //设置刷新控件刷新head and foot
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);//启用矢量图兼容
        ClassicsFooter.REFRESH_FOOTER_NOTHING = "已加载全部数据";
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.bg_app, R.color.text_grey_dark);//全局设置主题颜色
                return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableLoadMoreWhenContentNotFull(false);
                return new ClassicsFooter(context);
            }
        });
    }

    public static synchronized App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化数据库
        //  Realm.init(getApplicationContext());
        //在子线程中完成其他初始化
        InitializeService.start(this);
        ToastUtil.init(getApplicationContext()); //初始化Toast
        SharepreferenceUtil.init(getApplicationContext());//初始化Sharepreference
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
