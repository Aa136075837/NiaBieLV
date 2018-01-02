package com.example.bo.niabielv.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/12/25.
 */

public class NiaBieApplication extends Application {
    private static NiaBieApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Context getInstance() {
        return mInstance;
    }
}
