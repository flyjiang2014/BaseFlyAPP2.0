package com.base.flyjiang.baseflyapp.dagger.component;

import android.app.Activity;

import com.base.flyjiang.baseflyapp.dagger.module.FragmentModule;
import com.base.flyjiang.baseflyapp.dagger.scope.FragmentScope;

import dagger.Component;

/**
 * Created by flyjiang on 2019/8/2.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
}
