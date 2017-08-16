package com.example.aac.data.fragment.data;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acc.expansion.data.BaseDataFragment;
import com.acc.module.pres.RequiresPresenter;
import com.example.aac.R;

import org.w3c.dom.Text;

/**
 * Created by yangc on 2017/8/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */
@RequiresPresenter(TestDataFragmentPresenter.class)
public class TestDataFragment extends BaseDataFragment<TestDataFragmentPresenter, String> {

    TextView textView2;

    @Override
    public int getContentLayout() {
        return R.layout.test_data_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView2 = $(view, R.id.textView2);
        showLoading(textView2);
    }

    @Override
    public void setData(@NonNull String data) {
        textView2.setText(data);
    }

    @Override
    public void setError(Throwable e) {

    }




}
