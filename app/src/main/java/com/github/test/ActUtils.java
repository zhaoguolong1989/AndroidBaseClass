package com.github.test;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;

/**
 * Created by Administrator on 2017/7/17.
 */

public class ActUtils {
    /*public void STActivityForResult(Activity activity,Class clazz, int requestCode) {
        activity.startActivityForResult(new Intent(activity, clazz), requestCode);
    }
    public void STActivityForResult(Activity activity,Intent intent, Class clazz, int requestCode) {
        intent.setClass(activity, clazz);
        activity.startActivityForResult(intent, requestCode);
    }
    public void STActivity(Activity activity,Class clazz) {
        activity.startActivity(new Intent(activity, clazz));
    }
    public void STActivity2(Activity activity,Intent intent, Class clazz) {
        intent.setClass(activity, clazz);
        activity.startActivity(intent);
    }*/



    /*********************************带动画的跳转************************************View[]view,String[]transitionName*******/
    public static void STActivityForResult(Activity activity, Class clazz, int requestCode,Pair... pair ) {
        STActivityForResult(activity,new Intent(),clazz,requestCode,pair);
    }
    public static void STActivityForResult(Activity activity,Intent intent, Class clazz, int requestCode,Pair... pair) {
        intent.setClass(activity, clazz);
        if (pair!=null&&pair.length>0&&android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(activity,pair);
            activity.startActivityForResult(intent,requestCode,options.toBundle());
        }else{
            activity.startActivityForResult(intent,requestCode);
        }
    }
    public static void STActivity(Activity activity,Class clazz,Pair... pair) {//非空判断是否能进
        STActivity(activity,new Intent(),clazz,pair);
    }
    public static void STActivity(Activity activity,Intent intent, Class clazz,Pair... pair) {
        intent.setClass(activity, clazz);
        //pair!=null&&pair.length>0&&
        if (pair!=null&&pair.length>0&&android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(activity,pair);
            activity.startActivity(intent,options.toBundle());
        }else{
            activity.startActivity(intent);
        }
    }
}
