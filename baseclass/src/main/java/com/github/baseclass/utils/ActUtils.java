package com.github.baseclass.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.view.View;

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



    /*********************************带动画的跳转*******************************************/
    public void STActivityForResult(Activity activity, Class clazz, int requestCode, View[]view,String[]transitionName) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            if (view != null) {
                for (int i = 0; i < view.length; i++) {

                }
            }
            /*
                            Pair.create(holder.getView(R.id.iv_xinyong_card),"content"),
                            Pair.create(holder.getView(R.id.iv_xinyong_card)*/
            for (View v:view) {
                if (v != null) {
                    Pair.create(null,"content");
                }
            }
            ActivityOptions options =ActivityOptions
                    .makeSceneTransitionAnimation(activity,getPair(),"content");
            activity.startActivity(new options.toBundle());
        }else{
            STActivity(CreditCardDetailsActivity.class);
        }
    }


    public void STActivityForResult(Activity activity,Intent intent, Class clazz, int requestCode) {
        intent.setClass(activity, clazz);
        activity.startActivityForResult(intent, requestCode);
    }
    public void STActivity(Activity activity,Class clazz) {
        activity.startActivity(new Intent(activity, clazz));
    }
    public void STActivity(Activity activity,Intent intent, Class clazz) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =ActivityOptions
                    .makeSceneTransitionAnimation(getActivity(),
                            Pair.create(holder.getView(R.id.iv_xinyong_card),"content"),
                            Pair.create(holder.getView(R.id.iv_xinyong_card),"content")
                    );
            activity.startActivity(new options.toBundle());
        }else{
            STActivity(activity,intent,clazz);
        }
    }
}
