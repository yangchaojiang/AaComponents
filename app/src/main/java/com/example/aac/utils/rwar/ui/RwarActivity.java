package com.example.aac.utils.rwar.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.aac.module.ui.AacActivity;
import com.aac.module.pres.RequiresPresenter;
import com.example.aac.R;
import com.example.aac.utils.rwar.presenter.RwarPresenter;



/**
* Created by yangc on 2018/11/27 17:29:04
* E-Mail:yangchaojiang@outlook.com
* Deprecated:
**/
@RequiresPresenter(RwarPresenter.class)
public class RwarActivity extends AacActivity<RwarPresenter> {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity,RwarActivity.class);
        activity.startActivity(intent);
    }
    @Override
    public int getContentLayoutId() { return R.layout.activity_rwar;  }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    }

}
