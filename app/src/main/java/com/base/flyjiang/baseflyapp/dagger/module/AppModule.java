package com.base.flyjiang.baseflyapp.dagger.module;
import com.base.flyjiang.baseflyapp.app.App;
import com.base.flyjiang.baseflyapp.http.DataManager;
import com.base.flyjiang.baseflyapp.http.HttpHelper;
import com.base.flyjiang.baseflyapp.http.RetrofitHelper;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by flyjiang on 2019/8/2.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }
}
