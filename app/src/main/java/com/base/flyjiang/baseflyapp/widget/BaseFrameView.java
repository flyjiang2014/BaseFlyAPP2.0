package com.base.flyjiang.baseflyapp.widget;

import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.base.flyjiang.baseflyapp.base.SimpleActivity;

/**
 * 作者：flyjiang
 * 说明: View基类
 */
public abstract class BaseFrameView extends LinearLayout implements InitializeStep {

    private SimpleActivity mActivity;
    protected String tag = "";

    public BaseFrameView(SimpleActivity activity) {
        super(activity);
        this.mActivity = activity;
        this.tag = mActivity.TAG;
    }

    public void init(int layoutId) {
        LayoutInflater.from(mActivity).inflate(layoutId, this);
        initData();
        initView();
        initViewData();
        initViewListener();
    }
    public SimpleActivity getmActivity() {
        return this.mActivity;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public SimpleActivity getActivity() {
        return this.mActivity;
    }
}
