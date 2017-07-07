package com.github.baseclass;

import android.content.Intent;

import com.github.androidtools.rx.IOCallBack;

/**
 * Created by Administrator on 2016/8/16.
 */
public interface BaseView {
    void showMsg(String msg);
    void showLoading();
    void hideLoading();
    void actFinish();
    void STActivityForResult(Class clazz, int requestCode);
    void STActivityForResult(Intent intent, Class clazz, int requestCode);
    void STActivity(Class clazz);
    void STActivity(Intent intent, Class clazz);
    <T>  void RXStart(IOCallBack<T> callBack);
    <T>  void RXStart2(IOCallBack<T> callBack);
}
