package com.base.flyjiang.baseflyapp.dagger.component;

import android.app.Activity;

import com.base.flyjiang.baseflyapp.dagger.module.ActivityModule;
import com.base.flyjiang.baseflyapp.dagger.scope.ActivityScope;
import com.base.flyjiang.baseflyapp.view.MainActivity;

import dagger.Component;

/**
 * Created by flyjiang on 2019/8/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
    void inject(MainActivity activity);
}
