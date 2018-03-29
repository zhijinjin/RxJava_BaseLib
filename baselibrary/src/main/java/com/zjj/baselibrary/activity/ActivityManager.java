package com.zjj.baselibrary.activity;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by zhijinjin on 2018/3/28.
 */

public class ActivityManager {
    private static ActivityManager activityStackManager;

    public static ActivityManager getInstance() {
        if (activityStackManager == null) {
            activityStackManager = new ActivityManager();
        }
        return activityStackManager;
    }

    private ActivityManager() {
        activityStack = new Stack<>();
    }

    private Stack<Activity> activityStack;

    public void push(Activity activity) {
        activityStack.push(activity);
    }

    public Activity pop() {
        return activityStack.pop();
    }

    public void remove(Activity activity) {
        activityStack.remove(activity);
    }

    public void finishAll() {
        while (!activityStack.isEmpty()) {
            activityStack.pop().finish();
        }
    }

    public void finishToActivity(Activity activity){
        while (!activityStack.isEmpty()) {
            if (activityStack.peek()==activity){
                break;
            }
            activityStack.pop().finish();
        }
    }

    public void finishNActivity(int n){
        int i=n;
        while (!activityStack.isEmpty() && i>0) {
            activityStack.pop().finish();
            i--;
        }
    }

    public void finishUntilLast() {
        if (!activityStack.isEmpty()) {
            Activity activity = activityStack.pop();
            finishAll();
            push(activity);
        }
    }

    public Stack<Activity> getActivityStack() {
        return activityStack;
    }
}
