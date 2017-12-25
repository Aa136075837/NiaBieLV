package com.example.bo.niabielv.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.view.DatePopup;
import com.example.bo.niabielv.view.NiaBiePopup;
import com.example.bo.niabielv.view.PartsPopup;

import java.util.List;

public class AddAccountActivity extends AppCompatActivity implements NiaBiePopup.NiaPopupClickListener, PartsPopup.PartsPopupClickListener, DatePopup.DatePopupClickListener {

    private TextView mPayer;
    private TextView mParts;
    private EditText mAmount;
    private TextView mDate;
    private Button mFinish;
    private NiaBiePopup mPopup;
    private PartsPopup mPartsPopup;
    private EditText mRemakesEt;
    private DatePopup mDatePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        intView();
        initEvent();
    }

    private void intView() {
        mPayer = findViewById(R.id.account_payer);
        mParts = findViewById(R.id.parts_user);
        mAmount = findViewById(R.id.amount_et);
        mDate = findViewById(R.id.date_tv);
        mFinish = findViewById(R.id.add_btn);
        mRemakesEt = findViewById(R.id.remakes_et);
    }

    private void initEvent() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mPayer.setOnClickListener(v -> {
            if (isSoftShowing()) {
                imm.hideSoftInputFromWindow(mDate.getWindowToken(), 0);
                return;
            }
            mPopup = new NiaBiePopup(this);
            mPopup.setNiaPopupClickListener(this);
            mPopup.showAsDropDown(mPayer);
        });

        mParts.setOnClickListener(view -> {
            if (isSoftShowing()) {
                imm.hideSoftInputFromWindow(mDate.getWindowToken(), 0);
                return;
            }
            mPartsPopup = new PartsPopup(this);
            mPartsPopup.setPartsPopupClickListener(this);
            mPartsPopup.showAsDropDown(mParts);
        });

        mDate.setOnClickListener(view -> {
            if (isSoftShowing()) {
                imm.hideSoftInputFromWindow(mDate.getWindowToken(), 0);
                return;
            }
            mDatePopup = new DatePopup(this);
            mDatePopup.setDatePopupClickListener(this);

            mDatePopup.showAsDropDown(mDate);
        });

        mFinish.setOnClickListener(view -> {


        });
    }

    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom - getSoftButtonsBarHeight()!= 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftButtonsBarHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }

    @Override
    public void syncName(String name) {
        mPayer.setText(name);
        if (mPopup != null) {
            mPopup.dismiss();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mPartsPopup != null && mPartsPopup.isShowing()) {
            return false;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void syncName(List<String> names) {
        mParts.setText("");
        for (int i = 0; i < names.size(); i++) {
            if (i == names.size() - 1) {
                mParts.append(names.get(i));
            } else {
                mParts.append(names.get(i) + ",");
            }
        }
    }

    @Override
    public void date(String date) {
        mDate.setText(date);
    }
}
