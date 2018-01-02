package com.example.bo.niabielv.presenter;

import android.content.Context;
import com.example.bo.niabielv.base.BasePresenter;
import com.example.bo.niabielv.base.BaseView;

/**
 * Created by shuangyue on 2018/1/3.
 */
public interface RegisterContract {
    interface RegisterView extends BaseView{}
    class RegisterPresenter extends BasePresenter<RegisterView>{

        public RegisterPresenter (RegisterView view, Context context) {
            super (view, context);
        }
    }
}
