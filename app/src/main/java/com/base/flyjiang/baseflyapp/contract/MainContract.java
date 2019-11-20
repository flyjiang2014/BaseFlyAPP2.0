package com.base.flyjiang.baseflyapp.contract;

import com.base.flyjiang.baseflyapp.base.BasePresenter;
import com.base.flyjiang.baseflyapp.base.BaseView;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 *Created by flyjiang on 2019/8/2.
 */

public interface MainContract {

    interface View extends BaseView {
    }

    interface  Presenter extends BasePresenter<View> {
        void permissionApply(RxPermissions rxPermissions);
    }
}
