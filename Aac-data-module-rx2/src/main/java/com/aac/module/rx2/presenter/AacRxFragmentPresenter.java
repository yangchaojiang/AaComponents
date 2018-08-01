package com.aac.module.rx2.presenter;

import android.support.annotation.CallSuper;

import com.aac.module.ui.AacFragment;
import com.aac.module.ui.AacFragmentPresenter;
import com.aac.module.ui.AacPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author  yangc
 * date 2017/12/18
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class AacRxFragmentPresenter<T extends AacFragment> extends AacFragmentPresenter<T> {
    private CompositeDisposable compositeDisposable;

    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }


    @CallSuper
    @Override
    protected void onDestroy() {
        //Activity销毁时，取消网络请求
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
        super.onDestroy();
    }
}
