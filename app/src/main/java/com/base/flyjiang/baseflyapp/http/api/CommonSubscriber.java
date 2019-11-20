package com.base.flyjiang.baseflyapp.http.api;

import com.base.flyjiang.baseflyapp.app.Constants;
import com.base.flyjiang.baseflyapp.base.BaseView;
import com.base.flyjiang.baseflyapp.http.response.HttpResponse;
import com.base.flyjiang.baseflyapp.utils.SharepreferenceUtil;
import com.base.flyjiang.baseflyapp.utils.ToastUtil;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by flyjiang on 2019/8/2.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    private boolean  showStatue; //请求是否响应loading状态,用于展示noNetView, loadingView，emptyView,errorView
    private BaseView mView;

    public CommonSubscriber(BaseView mView) {
        this.mView = mView;
    }

    public CommonSubscriber(BaseView mView, boolean showStatue) {
        this.showStatue = showStatue;
        this.mView = mView;
    }

    @Override
    public void onNext(T t) {
        if(t instanceof HttpResponse){
            if(((HttpResponse) t).getSuccess()!=1){
                ToastUtil.showToast(((HttpResponse) t).getMessage());
            }
            if (showStatue) {
                mView.stateMain();
            }

            if(-1==((HttpResponse) t).getSuccess()){
                SharepreferenceUtil.removeKeyValue(Constants.TOKEN);
            }
        }
    }

    @Override
    public void onComplete() {
        mView.dismissDialogLoading();
    }

    @Override
    public void onError(Throwable e) {
        mView.dismissDialogLoading();
        ToastUtil.showToast(e.toString());
        if (showStatue) {
            mView.stateError();
        }
    }
}
