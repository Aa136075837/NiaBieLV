package com.example.bo.niabielv.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Administrator on 2017/12/25.
 */

public class NiaBieApplication extends MultiDexApplication {
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
