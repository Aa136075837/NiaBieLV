package com.example.bo.niabielv.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.bo.niabielv.R;
import com.example.bo.niabielv.base.BaseActivity;
import com.example.bo.niabielv.base.MVPBaseActivity;
import com.example.bo.niabielv.presenter.LoginContract;
import com.example.bo.niabielv.presenter.RegisterContract;

public class RegisterActivity extends MVPBaseActivity<RegisterContract.RegisterPresenter> implements
    RegisterContract.RegisterView {

    @BindView (R.id.et_username) EditText mEtUsername;
    @BindView (R.id.et_password) EditText mEtPassword;
    @BindView (R.id.et_repeatpassword) EditText mEtRepeatpassword;
    @BindView (R.id.bt_go) Button mBtGo;
    @BindView (R.id.cv_add) CardView mCvAdd;
    @BindView (R.id.fab) FloatingActionButton mFab;


    @OnClick ({ R.id.bt_go, R.id.fab }) public void onClick (View view) {
        switch (view.getId ()) {
            case R.id.bt_go:
                break;
            case R.id.fab:
                animateRevealClose();
                break;
        }
    }


    public void animateRevealClose() {
        Animator
            mAnimator = ViewAnimationUtils.createCircularReveal(mCvAdd,mCvAdd.getWidth()/2,0, mCvAdd.getHeight(), mFab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator ());
        mAnimator.addListener(new AnimatorListenerAdapter () {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                mFab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override protected RegisterContract.RegisterPresenter createPresenter () {
        return null;
    }

    @Override protected int initLayout () {
        return R.layout.activity_register;
    }

    @Override protected void initViews () {

    }

    @Override protected void initEvents () {

    }

    @Override protected void initParams (Bundle extras) {

    }
}
