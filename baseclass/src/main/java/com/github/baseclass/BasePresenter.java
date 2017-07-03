package com.github.baseclass;

/**
 * Created by Administrator on 2016/9/5.
 */
public interface BasePresenter<V extends BaseView> {
    void attach(V view);
    void detach();
}
