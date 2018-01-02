package com.example.bo.niabielv.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.bo.niabielv.R;
import com.example.bo.niabielv.base.MVPBaseActivity;
import com.example.bo.niabielv.presenter.LoginContract;

public class LoginActivity extends MVPBaseActivity<LoginContract.LoginPresenter> implements
    LoginContract.LoginView {

    @BindView (R.id.et_username) EditText mEtUsername;
    @BindView (R.id.et_password) EditText mEtPassword;
    @BindView (R.id.bt_go) Button mBtGo;
    @BindView (R.id.cv) CardView mCv;
    @BindView (R.id.fab) FloatingActionButton mFab;
    @BindView (R.id.tv_forget_password) TextView mTvForgetPassword;

    @Override protected LoginContract.LoginPresenter createPresenter () {
        return null;
    }

    @Override protected int initLayout () {
        return R.layout.activity_login;
    }

    @Override protected void initViews () {

    }

    @Override protected void initEvents () {

    }

    @Override protected void initParams (Bundle extras) {

    }

    @OnClick ({ R.id.bt_go, R.id.fab,R.id.tv_forget_password }) public void onClick (View view) {
        switch (view.getId ()) {
            case R.id.bt_go:
                Toast.makeText (this, "登录", Toast.LENGTH_SHORT).show ();
                startActivity (new Intent (this,MainActivity.class));
                finish ();
                break;
            case R.id.fab:
                Toast.makeText (this, "注册", Toast.LENGTH_SHORT).show ();

                //getWindow().setExitTransition(null);
                //getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation (this, mFab,
                            mFab.getTransitionName ());
                    startActivity (new Intent (this, RegisterActivity.class), options.toBundle ());
                } else {
                    startActivity (new Intent (this, RegisterActivity.class));
                }
                break;
            case R.id.tv_forget_password:
                Toast.makeText (this, "忘记密码", Toast.LENGTH_SHORT).show ();
                break;
        }
    }

}
