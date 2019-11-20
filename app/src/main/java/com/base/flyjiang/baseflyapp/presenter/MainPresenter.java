package com.base.flyjiang.baseflyapp.presenter;

import android.Manifest;

import com.base.flyjiang.baseflyapp.base.RxPresenter;
import com.base.flyjiang.baseflyapp.contract.MainContract;
import com.base.flyjiang.baseflyapp.http.DataManager;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import javax.inject.Inject;
import io.reactivex.functions.Consumer;

/**
 * Created by flyjiang on 2019/8/2.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void permissionApply(RxPermissions rxPermissions) {
        addSubscribe(rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                        }
                    }
                })
        );
    }
}
