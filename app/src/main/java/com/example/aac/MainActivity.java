package com.example.aac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aac.module.ui.AacActivity;
import com.aac.module.pres.RequiresPresenter;
import com.example.aac.data.activity.TestDataActivity;
import com.example.aac.data.activity.TestDataListActivity;
import com.example.aac.data.bing.TestDataBindActivity;
import com.example.aac.data.fragment.TestFragmentActivity;

@RequiresPresenter(TestPresenter.class)
public class MainActivity extends AacActivity<TestPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestDataActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestDataListActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestFragmentActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestDataBindActivity.class);
                startActivity(intent);
            }
        });
    }
}
