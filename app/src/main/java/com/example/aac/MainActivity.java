package com.example.aac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.acc.module.ui.BaseLifecycleActivity;
import com.acc.module.pres.RequiresPresenter;
import com.example.aac.data.TestDataActivity;
import com.example.aac.data.TestDataListActivity;
import com.example.aac.data.fragment.data.Test2FragmentActivity;
import com.example.aac.data.fragment.list.TestFragmentActivity;

@RequiresPresenter(Testprenter.class)
public class MainActivity extends BaseLifecycleActivity<Testprenter> {

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
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Test2FragmentActivity.class);
                startActivity(intent);
            }
        });
    }

}
