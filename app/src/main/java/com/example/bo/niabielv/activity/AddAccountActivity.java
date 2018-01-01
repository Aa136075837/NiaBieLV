package com.example.bo.niabielv.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.bean.UploadBean;
import com.example.bo.niabielv.http.Load;
import com.example.bo.niabielv.view.DatePopup;
import com.example.bo.niabielv.view.NiaBiePopup;
import com.example.bo.niabielv.view.PartsPopup;

import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AddAccountActivity extends AppCompatActivity implements NiaBiePopup.NiaPopupClickListener, PartsPopup.PartsPopupClickListener, DatePopup.DatePopupClickListener {

    private TextView mPayerTv;
    private TextView mPartsTv;
    private EditText mAmountTv;
    private TextView mDateTv;
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
        mPayerTv = findViewById(R.id.account_payer);
        mPartsTv = findViewById(R.id.parts_user);
        mAmountTv = findViewById(R.id.amount_et);
        mDateTv = findViewById(R.id.date_tv);
        mFinish = findViewById(R.id.add_btn);
        mRemakesEt = findViewById(R.id.remakes_et);
    }

    private void initEvent() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mPayerTv.setOnClickListener(v -> {
            if (isSoftShowing()) {
                imm.hideSoftInputFromWindow(mDateTv.getWindowToken(), 0);
                return;
            }
            mPopup = new NiaBiePopup(this);
            mPopup.setNiaPopupClickListener(this);
            mPopup.showAsDropDown(mPayerTv);
        });

        mPartsTv.setOnClickListener(view -> {
            if (isSoftShowing()) {
                imm.hideSoftInputFromWindow(mDateTv.getWindowToken(), 0);
                return;
            }
            mPartsPopup = new PartsPopup(this);
            mPartsPopup.setPartsPopupClickListener(this);
            mPartsPopup.showAsDropDown(mPartsTv);
        });

        mDateTv.setOnClickListener(view -> {
            if (isSoftShowing()) {
                imm.hideSoftInputFromWindow(mDateTv.getWindowToken(), 0);
                return;
            }
            mDatePopup = new DatePopup(this);
            mDatePopup.setDatePopupClickListener(this);

            mDatePopup.showAsDropDown(mDateTv);
        });

        mFinish.setOnClickListener(view -> {

            addAccount();
        });
    }

    private LinkedHashMap<String, Object> initUploadParams() {
        String payer = mPayerTv.getText().toString().trim();
        String parts = mPartsTv.getText().toString().trim();
        String amount = mAmountTv.getText().toString().trim();
        String date = mDateTv.getText().toString().trim();
        String remake = mRemakesEt.getText().toString().trim();
        if (TextUtils.isEmpty(payer) ||
                TextUtils.isEmpty(parts) ||
                TextUtils.isEmpty(amount) ||
                TextUtils.isEmpty(date) ||
                TextUtils.isEmpty(remake)) {
            Toast.makeText(this, "参数不能为空", Toast.LENGTH_SHORT).show();
            return null;
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("money_person", payer);
        map.put("actor", parts);
        map.put("money", amount);
        map.put("date_time", date);
        map.put("content", remake);
        return map;
    }

    private void addAccount() {

        String payer = mPayerTv.getText().toString().trim();
        String parts = mPartsTv.getText().toString().trim();
        String amount = mAmountTv.getText().toString().trim();
        String date = mDateTv.getText().toString().trim();
        String remake = mRemakesEt.getText().toString().trim();
        if (TextUtils.isEmpty(payer) ||
                TextUtils.isEmpty(parts) ||
                TextUtils.isEmpty(amount) ||
                TextUtils.isEmpty(date) ||
                TextUtils.isEmpty(remake)) {
            Toast.makeText(this, "参数不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressDialog progerssDialog = ProgressDialog.show(this, "", "正在添加...");
        progerssDialog.setCanceledOnTouchOutside(true);
        RequestBody body = new FormBody.Builder()
                .add("money_person", payer)
                .add("actor", parts)
                .add("money", amount)
                .add("date_time", date)
                .add("content", remake).build();


        Load.createApi().uploadAccount(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UploadBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UploadBean value) {
                if (value.isResult()) {
                    uploadSuccess();

                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                progerssDialog.dismiss();
            }
        });
    }

    private void uploadSuccess() {
        finish();
    }

    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom - getSoftButtonsBarHeight() != 0;
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
        mPayerTv.setText(name);
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
        mPartsTv.setText("");
        for (int i = 0; i < names.size(); i++) {
            if (i == names.size() - 1) {
                mPartsTv.append(names.get(i));
            } else {
                mPartsTv.append(names.get(i) + ",");
            }
        }
    }

    @Override
    public void date(String date) {
        mDateTv.setText(date);
    }
}
