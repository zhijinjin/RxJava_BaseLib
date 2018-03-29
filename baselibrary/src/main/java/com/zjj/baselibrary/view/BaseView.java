package com.zjj.baselibrary.view;

/**
 * Created by zhijinjin (951507056@qq.com)
 * on 2018/3/29.
 * view interface,所有View(此项目中的View主要是Fragment和自定义的ViewGroup)必须实现此接口
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    void displayError(java.lang.Throwable exception);
}
