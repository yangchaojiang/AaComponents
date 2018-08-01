package com.aac.data.http.utils;

import android.annotation.SuppressLint;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

/**
 * author  yangc
 * date 2018/7/27
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */
public class SSS {


    @SuppressLint("CheckResult")
    public void ssss(){
        Flowable.just("12")
                .flatMap(new Function<String, Publisher<Boolean>>() {
                    @Override
                    public Publisher<Boolean> apply(String s) throws Exception {
                        return  Flowable.just(true);
                    }
                });
        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> emitter) throws Exception {

            }
        }, BackpressureStrategy.DROP);
    }


}
