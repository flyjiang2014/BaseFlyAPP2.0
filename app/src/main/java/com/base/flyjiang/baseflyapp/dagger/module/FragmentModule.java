package com.base.flyjiang.baseflyapp.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.base.flyjiang.baseflyapp.dagger.scope.FragmentScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by flyjiang on 2019/8/2.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
