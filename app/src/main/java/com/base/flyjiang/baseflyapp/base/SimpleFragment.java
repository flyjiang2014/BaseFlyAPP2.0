package com.base.flyjiang.baseflyapp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.flyjiang.baseflyapp.R;
import com.base.flyjiang.baseflyapp.widget.loading.LoadingDialog;
import com.base.flyjiang.baseflyapp.widget.loading.LoadingLayout;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * Created by flyjiang on 2019/8/2.
 * 说明: Fragment基类，无mvp
 */
public abstract class SimpleFragment extends SupportFragment {
    protected View rootView;
    protected Context mContext;
    protected boolean isVisible; //是否可见
    protected boolean isPrepared;  //View是否已加载完毕
    //  protected boolean isFirst = true;//是否第一次加载数据,为false时，切换不在重新加载数据
    protected int pageSize = 10;

    /**
     * 页面加载过程布局
     */
    protected LoadingLayout mLoadingLayout;
    /**
     * 是否使用loading框架
     */
    private boolean isUseLoading = true;

    /**
     * 全局dialog
     */
    protected LoadingDialog loadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();//使用整个应用的上下文对象
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedanceState) {
        if (rootView == null) {
            rootView = initLayoutView(inflater);
        }
        if (isUseLoading && mLoadingLayout == null) {
            mLoadingLayout = (LoadingLayout) inflater.inflate(R.layout.loading_layout, null);
            mLoadingLayout.addView(rootView, 0); //自定义的界面加载到最底层
            mLoadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() { //load点击重试功能
                @Override
                public void onReload(View v) {
                    reLoadData();
                }
            });
        }
        return isUseLoading ? mLoadingLayout : rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadingDialog = dialogCreate();
        isPrepared = true;
        lazyLoad();
    }

    public void lazyLoad() {
        if (!isPrepared || !isVisible) { //   || !isFirst
            return;
        }
        initData();
        //   isFirst = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    /**
     * 子类实现初始化View操作
     */
    private View initLayoutView(LayoutInflater inflater){
        View view = inflater.inflate(onLayoutRes(),null);
        initView(view);
        initViewData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
        isVisible = false;
        if (isUseLoading) {
            if (mLoadingLayout != null && mLoadingLayout.getParent() != null) {
                ((ViewGroup) mLoadingLayout.getParent()).removeView(mLoadingLayout);
            }
        } else {
            if (rootView != null && rootView.getParent() != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            }
        }
    }

    /**
     * 子类实现初始化布局view
     */
    protected abstract int onLayoutRes();

    public abstract void initView(View view);

    /**
     * 子类实现初始化View本地数据初始化
     */
    protected abstract void initViewData();

    /**
     * 子类实现初始化数据操作(子类自己调用)
     */
    public abstract void initData();

    /**
     * 设置是否使用loading框架
     */
    public void setUseLoading(boolean useLoading) {
        isUseLoading = useLoading;
    }

    /**
     * 加载菊花提示
     *
     * @return
     */
    public LoadingDialog dialogCreate() {
        return new LoadingDialog.Builder(getActivity()).create();
    }

    /**
     * 重试处理，需重写的处理方法
     */
    public void reLoadData() {
    }
}
