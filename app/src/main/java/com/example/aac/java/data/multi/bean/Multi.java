package com.example.aac.java.data.multi.bean;


import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
* Created by yangc on 2018/10/15 11:08:53
* E-Mail:yangchaojiang@outlook.com
* Deprecated:
**/
public class Multi implements Parcelable,MultiItemEntity {


    protected Multi(Parcel in) {
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Multi> CREATOR = new Creator<Multi>() {
        @Override
        public Multi createFromParcel(Parcel in) {
            return new Multi(in);
        }

        @Override
        public Multi[] newArray(int size) {
            return new Multi[size];
        }
    };

    @Override
    public int getItemType() {
        return 0;
    }
}
