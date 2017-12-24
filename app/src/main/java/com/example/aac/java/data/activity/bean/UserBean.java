package com.example.aac.java.data.activity.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangc on 2017/9/25.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class UserBean implements Parcelable {

  private  String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.test);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.test = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
