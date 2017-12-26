package com.example.bo.niabielv.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.utils.DisplayUtil;

import java.util.Date;

/**
 * Created by bo on 2017/12/24.
 */
public class DatePopup extends PopupWindow {
    private final ViewGroup vg;
    private final DatePicker mDp;
    private final Button mDateBtn;

    private int mYear;
    private int mMonth;
    private int mDate1;

    public DatePopup(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vg = (ViewGroup) inflater.inflate(R.layout.popup_date, null);

        mDp = vg.findViewById(R.id.date_picker);
        mDp.setMaxDate(System.currentTimeMillis());
        mDateBtn = vg.findViewById(R.id.date_btn);
        mDateBtn.setOnClickListener(view -> {
            if (mListener != null) {
                Date date = new Date();
                mYear = date.getYear() + 1900;
                mMonth = date.getMonth() + 1;
                mDate1 = date.getDate();

                if (mDp.getYear() > mYear || mDp.getMonth() > mMonth || mDp.getDayOfMonth() > mDate1) {
                    Toast.makeText(context, "不能选择未来的时间", Toast.LENGTH_SHORT).show();
                }
                mListener.date(mDp.getYear() + "-" + (mDp.getMonth()+1) + "-" + mDp.getDayOfMonth());
            }
            dismiss();
        });

        this.setContentView(vg);
        this.setHeight(DisplayUtil.dip2px(context, 300));
        this.setWidth(width);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.popWindow_animation);
        this.setOutsideTouchable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.update();
    }

    public interface DatePopupClickListener {
        void date(String date);
    }

    public void setDatePopupClickListener(DatePopupClickListener listener) {
        mListener = listener;
    }

    private DatePopupClickListener mListener;
}
