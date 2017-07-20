package com.github.baseclass.rx;

import android.content.Context;

import com.github.androidtools.ToastUtils;
import com.github.baseclass.view.Loading;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/6/16.
 */
public abstract class ExampleSub<T> extends Subscriber<T> {
    private Context context;
    private boolean noLoading=false;

    public ExampleSub(Context ctx) {
        this.context = ctx;
    }
    public ExampleSub(Context ctx, boolean isHiddenLoad) {
        this.noLoading=isHiddenLoad;
        context=ctx;
        if(!noLoading){
            Loading.show(ctx);
        }
    }

    public abstract void onMyNext(T obj);
    public void onMyCompleted(){
    };
    public void onMyError(Throwable e){
    };
    @Override
    public void onCompleted() {
        onMyCompleted();
        if(!noLoading){
            Loading.dismissLoading();
        }
        this.context=null;
    }
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if(e instanceof ServerException){//||e instanceof NoNetworkException){
            ToastUtils.showToast(context,e.getMessage());
        }
        onMyError(e);
        if(!noLoading){
            Loading.dismissLoading();
        }
        this.context=null;
    }
    @Override
    public void onNext(T obj) {
        onMyNext(obj);
    }
}
