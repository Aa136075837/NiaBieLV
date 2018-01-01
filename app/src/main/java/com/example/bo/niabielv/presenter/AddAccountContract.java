package com.example.bo.niabielv.presenter;

import android.content.Context;

import com.example.bo.niabielv.base.BasePresenter;
import com.example.bo.niabielv.base.BaseView;
import com.example.bo.niabielv.bean.PartsDetailsBean;
import com.example.bo.niabielv.http.Load;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bo on 2018/1/1.
 */
public interface AddAccountContract {
    interface AddAccountView extends BaseView {
        void getDetailsSuccess(List<PartsDetailsBean> value);
    }

    class AddAccountPresenter extends BasePresenter<AddAccountView> {

        public AddAccountPresenter(AddAccountView view, Context context) {
            super(view, context);
        }

        public void getUserDetails() {
            DisposableObserver<List<PartsDetailsBean>> disposableObserver = Load.createApi().userDetails().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<PartsDetailsBean>>() {

                        @Override
                        public void onNext(List<PartsDetailsBean> value) {
                            mView.getDetailsSuccess(value);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

            addObservable(disposableObserver);
        }
    }
}
