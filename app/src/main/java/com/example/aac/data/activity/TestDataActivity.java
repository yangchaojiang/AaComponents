package com.example.aac.data.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.aac.expansion.data.AacDataActivity;
import com.aac.module.pres.RequiresPresenter;
import com.example.aac.R;
import com.example.aac.databinding.TestDataViewBinding;
import com.helper.loadviewhelper.help.OnLoadViewListener;
import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

@RequiresPresenter(TesDataPresenter.class)
public class TestDataActivity extends AacDataActivity<TesDataPresenter, String> {
    public static final String TAG = "TestDataBindActivity";
    private  boolean is;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper=new LoadViewHelper( findViewById(R.id.root));
        helper.showLoading();
        helper.setListener(new OnLoadViewListener() {
            @Override
            public void onRetryClick() {
                helper.showLoading();
                getPresenter().getLoad();
            }
        });
    }


    @Override
    public int getContentLayout() {
        return R.layout.test_data1_view;
    }

    @Override
    public void setData(@NonNull String data) {
        Toast.makeText(this, ":" + data, Toast.LENGTH_LONG).show();
        TextView te = $(R.id.textView2);
        te.setText(data);

    }

    @Override
    public void setError(Throwable e) {
        getLoadViewHelper().getLoadError();
    }


}
