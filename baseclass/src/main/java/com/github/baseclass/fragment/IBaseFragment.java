package com.github.baseclass.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.github.androidtools.ToastUtils;
import com.github.baseclass.BaseView;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.CommonAdapter;
import com.github.baseclass.rx.IOCallBack;
import com.github.baseclass.view.Loading;
import com.github.baseclass.view.MyDialog;
import com.github.baseclass.view.MyPopupwindow;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class IBaseFragment extends Fragment implements BaseView {
    protected Activity mContext;
    protected BaseRecyclerAdapter mRcAdapter;
    protected CommonAdapter mAdapter;
    protected MyPopupwindow mPopupwindow;
    protected MyDialog.Builder mDialog;
    /****************************RxJava********************************/
    private CompositeSubscription mCSubscription;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
//        RxBus.get().register(this);
    }

    protected void showToastS(String toast) {
        ToastUtils.showToast(mContext, toast);
    }

    protected void showToastL(String toast) {
        ToastUtils.showToast(mContext, toast, Toast.LENGTH_LONG);
    }

    public void STActivityForResult(Class clazz,int requestCode){
        startActivityForResult(new Intent(getActivity(), clazz), requestCode);
    }
    public void STActivityForResult(Intent intent,Class clazz,int requestCode){
        intent.setClass(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }
    public void STActivity(Class clazz){
        startActivity(new Intent(getActivity(), clazz));
    }
    public void STActivity(Intent intent,Class clazz){
        intent.setClass(getActivity(), clazz);
        startActivity(intent);
    }
    protected void showLoading(boolean isExit){
        Loading.showForExit(getActivity(), isExit);
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showToast(getActivity(),msg);
    }

    public void showLoading(){
        Loading.show(getActivity());
    }

    @Override
    public void hideLoading() {
        Loading.dismissLoading();
    }

    @Override
    public void actFinish() {
    }

    protected void dismissLoading(){
        Loading.dismissLoading();
    }

    //RXjava注册
    protected void addSubscription(Subscription subscription) {
        if (mCSubscription == null) {
            mCSubscription = new CompositeSubscription();
        }
        mCSubscription.add(subscription);
    }

    //RXjava取消注册，以避免内存泄露
    protected void onUnSubscription() {
        if (mCSubscription != null && mCSubscription.hasSubscriptions()) {
            mCSubscription.unsubscribe();
        }
    }

    /****************************************************************************/
    public <T> void RXStart(final IOCallBack<T> callBack) {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                callBack.call(subscriber);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        callBack.onMyCompleted();
                    }
                    @Override
                    public void onError(Throwable e) {
                        callBack.onMyError(e);
                    }
                    @Override
                    public void onNext(T t) {
                        callBack.onMyNext(t);
                    }
                });
        addSubscription(subscribe);
    }
    public <T> void RXStart2(final IOCallBack<T> callBack) {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                callBack.call(subscriber);
            }
        })
                .asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        callBack.onMyCompleted();
                    }
                    @Override
                    public void onError(Throwable e) {
                        callBack.onMyError(e);
                    }
                    @Override
                    public void onNext(T t) {
                        callBack.onMyNext(t);
                    }
                });
        addSubscription(subscribe);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mContext = null;
        ToastUtils.cancelToast();
    }
    /****************************************************************************/


}
