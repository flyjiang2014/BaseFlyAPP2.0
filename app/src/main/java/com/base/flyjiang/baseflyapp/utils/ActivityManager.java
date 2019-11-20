package com.base.flyjiang.baseflyapp.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * 作者：flyjiang
 * 说明: Activity管理类
 */
public class ActivityManager {

    private static ActivityManager instance;
    private Stack<Activity> activityStack;// activity栈

    // 单例模式
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    // 把一个activity压入栈中
    public void addActivity(Activity actvity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(actvity);
    }

    // 获取栈顶的activity，先进后出原则
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    // 移除一个activity
    public void finishActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
                activity = null;
            }
        }
    }
    /**
     * 结束指定类名的Activity
     */
    public  void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                finishActivity(cls);
                break;
            }
        }
    }

    // 退出所有activity
    public void finishAllActivity() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getLastActivity();
                if (activity == null)
                    break;
                finishActivity(activity);
            }
        }
    }
}
