package com.github.baseclass.rx;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/6/16.
 */
public abstract class IOCallBack<T> {
    public abstract void call(Subscriber<? super T> sub);
    public abstract void onMyNext(T obj);
    public void onMyCompleted(){
    };
    public void onMyError(Throwable e){
        e.printStackTrace();
    };
}
