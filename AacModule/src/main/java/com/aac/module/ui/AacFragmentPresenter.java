package com.aac.module.ui;


/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  Fragment业务处理控制类
 *
 * @param <FragmentType> AacFragmentPresenter 订阅{@link AacFragment}类
 * @see AacFragmentPresenter
 */


public abstract class AacFragmentPresenter<FragmentType extends AacFragment> extends AacPresenter<FragmentType> {


    public void onDestroyView() {

    }

    public void onViewCreated() {
    }


}
