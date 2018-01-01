package com.example.bo.niabielv.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by bo on 2018/1/1.
 */
public class BaseActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    public void onSwitchFragment() {
//        if (mFragmentManager == null) {
//            mFragmentManager = getSupportFragmentManager();
//        }
//        FragmentTransaction transaction = mFragmentManager.beginTransaction();
//        if (mCurrentFragment != null){
//            transaction.hide(mCurrentFragment);
//        }
//
//
//
//    }

    /**
     * 跳转页面
     *
     * @param targetActivity
     */
    public void toActivity(Class<?> targetActivity) {
        startActivity(new Intent(this, targetActivity));
    }

    /**
     * 跳转页面 带参数
     *
     * @param targetActivity
     * @param bundle
     */
    public void toActivity(Class<?> targetActivity, Bundle bundle) {
        Intent intent = new Intent(this, targetActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
