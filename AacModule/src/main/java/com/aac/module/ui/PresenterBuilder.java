package com.aac.module.ui;


import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import com.aac.module.pres.RequiresPresenter;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  java反射当前类
 */
public class PresenterBuilder {

    public static <PresenterType extends AacPresenter> PresenterType fromViewClass(@NonNull LifecycleOwner viewClass) {
        RequiresPresenter annotation = viewClass.getClass().getAnnotation(RequiresPresenter.class);
        //noinspection unchecked
        if (annotation == null) {
            return null;
            //throw new RuntimeException("You must declaration @RequiresPresenter for your Activity");
        }
        Class<PresenterType> presenterClass = (Class<PresenterType>) annotation.value();
        PresenterType presenter;
        try {
            presenter = presenterClass.newInstance();
            presenter.create(viewClass);
        } catch (InstantiationException e) {
            throw new RuntimeException("PresenterBuilder Find generic failed:"+e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("PresenterBuilder Find generic failed:"+e);
        }
        return presenter;
    }

}
