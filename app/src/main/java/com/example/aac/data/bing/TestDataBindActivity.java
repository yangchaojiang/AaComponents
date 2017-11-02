package com.example.aac.data.bing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.aac.data.binging.AacDataBindingActivity;
import com.aac.module.pres.RequiresPresenter;
import com.example.aac.R;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

@RequiresPresenter(TesDataBindPresenter.class)
public class TestDataBindActivity extends AacDataBindingActivity<TesDataBindPresenter> {
    public static final String TAG = "TestDataBindActivity";
    private  boolean is;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  TestDataViewBinding testDataViewBinding = DataBindingUtil.setContentView(this, R.layout.test_data_view);
    }

    @Override
    public int getContentLayout() {
        return R.layout.test_data_view;
    }



    @Override
    public void setError(Throwable e) {

    }


}
