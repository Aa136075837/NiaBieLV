package com.example.bo.niabielv.presenter;

import android.content.Context;
import com.example.bo.niabielv.base.BasePresenter;
import com.example.bo.niabielv.base.BaseView;

/**
 * Created by shuangyue on 2018/1/3.
 */
public interface LoginContract {
    interface LoginView extends BaseView{}
    class LoginPresenter extends BasePresenter<LoginView>{

        public LoginPresenter (LoginView view, Context context) {
            super (view, context);
        }
    }
}
