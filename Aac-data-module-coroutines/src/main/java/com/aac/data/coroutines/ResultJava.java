package com.aac.data.coroutines;

import android.support.annotation.Nullable;

/**
 * author  yangc
 * date 2018/3/15
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class ResultJava<T> {

    private  T result  ;
    private Throwable error ;

    public T getResult() {
        return result;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }
    public static <T> ResultJava<T> of(Throwable error){
        ResultJava<T> bean=new ResultJava<>();
        bean.error=error;
        return bean;
    }
    public static <T> ResultJava<T> of(T bean){
        ResultJava<T> bean2=new ResultJava<>();
        bean2.result=bean;
        return bean2;
    }

    public T component1 () {  return result ; }
    public Throwable component2 () {  return error ; }
}
