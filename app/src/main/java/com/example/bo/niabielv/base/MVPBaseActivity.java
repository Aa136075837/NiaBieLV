package com.example.bo.niabielv.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

public abstract class MVPBaseActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;

    protected abstract P createPresenter();

    @LayoutRes
    protected abstract int initLayout();

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void initParams(Bundle extras);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        mPresenter = createPresenter();
        Bundle extras = getIntent().getExtras();
        initParams(extras);
        initViews();
        initEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.onDestroy();
        }
    }
}
