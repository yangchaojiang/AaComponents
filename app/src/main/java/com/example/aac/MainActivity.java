package com.example.aac;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.center.toolbar.TitleToolbar;
import com.example.aac.data.activity.TestDataActivity;
import com.example.aac.data.activity.TestDataListActivity;
import com.example.aac.data.bing.TestDataBindActivity;
import com.example.aac.data.fragment.TestFragmentActivity;

public class MainActivity extends AppCompatActivity {
    private TitleToolbar titleToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.titleToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(titleToolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayOptions(mActionBar.getDisplayOptions() | ActionBar.DISPLAY_HOME_AS_UP);
        titleToolbar.setTitle("我的");
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

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }
}
