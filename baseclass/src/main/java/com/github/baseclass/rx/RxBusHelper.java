package com.github.baseclass.rx;

import com.github.androidtools.rx.MySubscriber;
import com.github.androidtools.rx.RxBus;

import rx.Subscription;

/**
 * Created by Administrator on 2017/6/26.
 */

public class RxBusHelper {
    public static <T> Subscription getRxBusEvent(Class<T> eventType,MySubscriber sub){
        Subscription subscribe = RxBus.getInstance().toObservable(eventType).subscribe(sub);
        return subscribe;
    }
}
