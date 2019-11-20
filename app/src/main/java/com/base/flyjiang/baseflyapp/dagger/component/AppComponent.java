package com.base.flyjiang.baseflyapp.dagger.component;
import com.base.flyjiang.baseflyapp.app.App;
import com.base.flyjiang.baseflyapp.dagger.module.AppModule;
import com.base.flyjiang.baseflyapp.dagger.module.HttpModule;
import com.base.flyjiang.baseflyapp.http.DataManager;
import com.base.flyjiang.baseflyapp.http.RetrofitHelper;
import javax.inject.Singleton;
import dagger.Component;

/**
 *Created by flyjiang on 2019/8/2.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类
}
