package com.example.bo.niabielv.base;

import android.content.Context;

import com.example.bo.niabielv.utils.L;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by bo on 2018/1/1.
 */
public class BasePresenter<V extends BaseView> {
    protected V mView;
    protected Context mContext;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(V view, Context context) {
        mView = view;
        mContext = context;
    }

    public CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable;
    }

    public void addObservable(Disposable disposable) {
        try {
            if (mCompositeDisposable == null) {
                mCompositeDisposable = getCompositeDisposable();
            }
            mCompositeDisposable.add(disposable);
        } catch (Exception e) {
            L.e(" BasePresenter 添加异常 " + e.toString());
        }
    }

    public void onDestroy() {
        mView = null;
        mContext = null;
        try {
            if (mCompositeDisposable != null) {
                mCompositeDisposable.clear();
            }
        } catch (Exception e) {
            L.e(" BasePresenter 注销RX异常" + e.toString());
        }
    }
}
