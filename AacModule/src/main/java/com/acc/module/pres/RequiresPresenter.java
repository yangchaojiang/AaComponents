package com.acc.module.pres;

import com.acc.module.ui.BaseLifecycle;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPresenter {
    Class<? extends BaseLifecycle> value();
}
