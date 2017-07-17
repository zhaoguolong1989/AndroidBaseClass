package com.github.baseclass.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;

/**
 * Created by Administrator on 2017/7/17.
 */

public class ActUtils {
    public void STActivityForResult(Activity activity,Class clazz, int requestCode) {
        activity.startActivityForResult(new Intent(activity, clazz), requestCode);
    }
    public void STActivityForResult(Activity activity,Intent intent, Class clazz, int requestCode) {
        intent.setClass(activity, clazz);
        activity.startActivityForResult(intent, requestCode);
    }
    public void STActivity(Activity activity,Class clazz) {
        activity.startActivity(new Intent(activity, clazz));
    }
    public void STActivity(Activity activity,Intent intent, Class clazz) {
        intent.setClass(activity, clazz);
        activity.startActivity(intent);
    }



    /*********************************带动画的跳转************************************View[]view,String[]transitionName*******/
    public void STActivityForResult(Activity activity, Class clazz, int requestCode,Pair... pair ) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            /*if (view != null) {
                for (int i = 0; i < view.length; i++) {
                    Pair.create(null,null);
                }
            }*/
            /*
                            Pair.create(holder.getView(R.id.iv_xinyong_card),"content"),
                            Pair.create(holder.getView(R.id.iv_xinyong_card)*/
            /*for (View v:view) {
                if (v != null) {
                    Pair.create(null,"content");
                }
            }*/
            ActivityOptions options =ActivityOptions
                    .makeSceneTransitionAnimation(activity,pair);
            activity.startActivity(new Intent(activity,clazz), options.toBundle());
        }else{
            STActivity(activity,clazz);
        }
    }


    public void STActivityForResult(Activity activity,Intent intent, Class clazz, int requestCode,Pair... pair) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(activity,pair);
            activity.startActivityForResult(new Intent(activity,clazz), requestCode,options.toBundle());
        }else{
            STActivityForResult(activity,clazz,requestCode);
        }
    }
    public void STActivity(Activity activity,Class clazz,Pair... pair) {//非空判断是否能进
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(activity,pair);
            activity.startActivity(new Intent(activity,clazz), options.toBundle());
        }else{
            STActivity(activity,clazz);
        }
    }
    public void STActivity(Activity activity,Intent intent, Class clazz,Pair... pair) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            intent.setClass(activity, clazz);
            ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(activity,pair);
            activity.startActivity(intent,options.toBundle());
        }else{
            STActivity(activity,intent,clazz);
        }
    }
}
