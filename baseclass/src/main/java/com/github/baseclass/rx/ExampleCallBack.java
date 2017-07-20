package com.github.baseclass.rx;

import android.content.Context;
import android.widget.Toast;

import com.github.baseclass.view.Loading;

/**
 * Created by Administrator on 2017/5/18.
 */

public abstract class ExampleCallBack<T>{// extends RetrofitCallBack<T> {
    private Context context;
    private boolean noLoading=false;

    public  abstract void onSuccessful(T response);
    public ExampleCallBack(Context ctx) {
        context=ctx;
        Loading.show(ctx);
    }
    public ExampleCallBack(Context ctx, boolean noLoading) {
        this.noLoading=noLoading;
        context=ctx;
        if(!noLoading){
            Loading.show(ctx);
        }
    }
    protected void onSuccess(T response) {
        onSuccessful(response);
        if(!noLoading){
            Loading.dismissLoading();
        }
    }
    protected void onError(Throwable throwable) {
        if(!noLoading){
            Loading.dismissLoading();
        }
        Toast.makeText(context,"连接失败", Toast.LENGTH_SHORT).show();
        context=null;
        throwable.printStackTrace();
    }
}
