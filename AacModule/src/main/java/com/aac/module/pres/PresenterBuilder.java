package com.aac.module.pres;


import com.aac.module.ui.AacPresenter;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  java反射当前类
 */
public class PresenterBuilder {

    public static <PresenterType extends AacPresenter> PresenterType fromViewClass(Class<?> viewClass) {
        RequiresPresenter annotation = viewClass.getAnnotation(RequiresPresenter.class);
        //noinspection unchecked
        if (annotation == null) {
            return null;
            //throw new RuntimeException("You must declaration @RequiresPresenter for your Activity");
        }

        Class<PresenterType> presenterClass = (Class<PresenterType>) annotation.value();

        PresenterType presenter;
        try {
            presenter = presenterClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return presenter;
    }

}
