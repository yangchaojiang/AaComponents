package com.example.aac.data.bing;

import android.os.SystemClock;
import com.aac.module.model.AacViewModel;
import com.example.aac.data.activity.bean.UserBean;
import com.example.aac.databinding.TestDataViewBinding;

 

public class DataBindViewModel extends AacViewModel {

    public void  getBindData(final TestDataViewBinding testDataViewBinding) {
        Thread s = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                // data.postValue("撒的阿萨德");
                UserBean userBean = new UserBean();
                userBean.setTest("数据");
                testDataViewBinding.setUser(userBean);
            }
        });
        s.start();
    }
}
