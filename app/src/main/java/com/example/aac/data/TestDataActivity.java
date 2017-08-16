package com.example.aac.data;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.acc.expansion.data.BaseDataActivity;
import com.acc.module.pres.RequiresPresenter;
import com.example.aac.R;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

@RequiresPresenter(TesDataPresenter.class)
public class TestDataActivity extends BaseDataActivity<TesDataPresenter, String> {
    public static final String TAG = "TestDataActivity";


    @Override
    public int getContentLayout() {
        return R.layout.test_data_view;
    }

    @Override
    public void setData(@NonNull String data) {
        Toast.makeText(this, ":" + data, Toast.LENGTH_LONG).show();
        TextView te = $(R.id.textView2);
        te.setText(data);

    }

    @Override
    public void setError(Throwable e) {

    }

    @Override
    public boolean isLoadingOpen() {
        return false;
    }

}
