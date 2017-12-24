package com.example.bo.niabielv.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bo on 2017/12/24.
 */
public class PartsPopup extends PopupWindow {
    private final ViewGroup vg;
    private final CheckBox mZhangCheck;
    private final CheckBox mCaoCheck;
    private final CheckBox mYueCheck;
    private final CheckBox mMacCheck;
    private final CheckBox mLvCheck;
    private final CheckBox mDongCheck;

    private final TextView mZhangCheckTv;
    private final TextView mCaoCheckTv;
    private final TextView mYueCheckTv;
    private final TextView mMacCheckTv;
    private final TextView mLvCheckTv;
    private final TextView mDongCheckTv;

    public PartsPopup(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vg = (ViewGroup) inflater.inflate(R.layout.popup_parts, null);
        mZhangCheck = vg.findViewById(R.id.zhang_check);
        mCaoCheck = vg.findViewById(R.id.cao_check);
        mYueCheck = vg.findViewById(R.id.yue_check);
        mMacCheck = vg.findViewById(R.id.mac_check);
        mLvCheck = vg.findViewById(R.id.lv_check);
        mDongCheck = vg.findViewById(R.id.dong_check);


        mZhangCheckTv = vg.findViewById(R.id.zhang_tv);
        mCaoCheckTv = vg.findViewById(R.id.cao_tv);
        mYueCheckTv = vg.findViewById(R.id.yue_tv);
        mMacCheckTv = vg.findViewById(R.id.mac_tv);
        mLvCheckTv = vg.findViewById(R.id.lv_tv);
        mDongCheckTv = vg.findViewById(R.id.dong_tv);

        Button btn = vg.findViewById(R.id.parts_btn);
        btn.setOnClickListener(view -> dismiss());

        this.setContentView(vg);
        this.setHeight(DisplayUtil.dip2px(context, 500));
        this.setWidth(width);
        this.setFocusable(false);
        this.setAnimationStyle(R.style.popWindow_animation);
        this.setOutsideTouchable(false);
        this.update();
    }

    @Override
    public void dismiss() {
        if (mListener != null) {
            mListener.syncName(getCheckedNames());
        }

        super.dismiss();
    }

    private List<String> getCheckedNames() {
        List<String> checkedNames = new ArrayList<>();
        if (mZhangCheck.isChecked()) {
            checkedNames.add(mZhangCheckTv.getText().toString().trim());
        }
        if (mCaoCheck.isChecked()) {
            checkedNames.add(mCaoCheckTv.getText().toString().trim());
        }
        if (mYueCheck.isChecked()) {
            checkedNames.add(mYueCheckTv.getText().toString().trim());
        }
        if (mMacCheck.isChecked()) {
            checkedNames.add(mMacCheckTv.getText().toString().trim());
        }
        if (mLvCheck.isChecked()) {
            checkedNames.add(mLvCheckTv.getText().toString().trim());
        }
        if (mDongCheck.isChecked()) {
            checkedNames.add(mDongCheckTv.getText().toString().trim());
        }
        return checkedNames;
    }


    public void setListener(View.OnClickListener listener) {

    }


    public interface PartsPopupClickListener {
        void syncName(List<String> names);
    }

    public void setPartsPopupClickListener(PartsPopupClickListener listener) {
        mListener = listener;
    }

    private PartsPopupClickListener mListener;
}
