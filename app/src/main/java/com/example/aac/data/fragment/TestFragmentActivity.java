package com.example.aac.data.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.aac.module.ui.AacActivity;
import com.example.aac.R;
import com.example.aac.data.fragment.data.Test2FragmentActivity;
import com.example.aac.data.fragment.list.custom.TestCustomFragmentActivity;
import com.example.aac.data.fragment.list.defaults.TestDataFragmentActivity;

/**
 * Created by yangc on 2017/8/27.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestFragmentActivity extends AacActivity {
    public static final String TAG = "TestDataFragmentActivity";
  ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_fragmen_view);
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestFragmentActivity.this, Test2FragmentActivity.class);
                startActivity(intent);

            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestFragmentActivity.this, TestDataFragmentActivity.class);
                startActivity(intent);

            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestFragmentActivity.this, TestCustomFragmentActivity.class);
                startActivity(intent);

            }
        });
    }
}
