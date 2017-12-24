package com.example.bo.niabielv.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
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
        mPayer.setOnClickListener(v -> {
            mPopup = new NiaBiePopup(this);
            mPopup.setNiaPopupClickListener(this);
            mPopup.showAsDropDown(mPayer);
        });

        mParts.setOnClickListener(view -> {
            mPartsPopup = new PartsPopup(this);
            mPartsPopup.setPartsPopupClickListener(this);
            mPartsPopup.showAsDropDown(mParts);
        });

        mDate.setOnClickListener(view -> {
            mDatePopup = new DatePopup(this);
            mDatePopup.setDatePopupClickListener(this);
            mDatePopup.showAsDropDown(mDate);
        });

        mFinish.setOnClickListener(view -> {


        });
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
