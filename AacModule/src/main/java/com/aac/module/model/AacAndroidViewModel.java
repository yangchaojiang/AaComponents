package com.aac.module.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

/**
 * Created by yangc on 2017/8/27.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:父类view模型
 */

public abstract class AacAndroidViewModel extends AndroidViewModel {

    public AacAndroidViewModel(Application application) {
        super(application);
    }
}
