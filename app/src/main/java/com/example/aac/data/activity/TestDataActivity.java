package com.example.aac.data.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.aac.expansion.data.AacDataActivity;
import com.aac.module.pres.RequiresPresenter;
import com.center.toolbar.TitleToolbar;
import com.example.aac.R;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

@RequiresPresenter(TesDataPresenter.class)
public class TestDataActivity extends AacDataActivity<TesDataPresenter, String> {
    TextView te;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        te = findViewById(R.id.textView2);
        initLoadHelper(findViewById(R.id.root));
        getSupportActionBar().setTitle("测试");
    }


    @Override
    public void setData(@NonNull String data) {
        te.setText(data);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.test_data1_views;
    }
}
