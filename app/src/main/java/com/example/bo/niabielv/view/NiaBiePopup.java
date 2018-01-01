package com.example.bo.niabielv.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.adapter.RecycleAdapter;

/**
 * Created by bo on 2017/12/24.
 */
public class NiaBiePopup extends PopupWindow implements RecycleAdapter.RecycleItemClickListener {
    private final ViewGroup vg;

    public NiaBiePopup(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vg = (ViewGroup) inflater.inflate(R.layout.popup_actor, null);

        RecyclerView recyclerView = vg.findViewById(R.id.popup_recycle);
        LinearLayoutManager LinManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(LinManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                outRect.set(20, 10, 20, 10);
            }
        });
        RecycleAdapter adapter = new RecycleAdapter();
        adapter.setRecycleItemClickListener(this);
        recyclerView.setAdapter(adapter);
        this.setContentView(vg);
        this.setHeight(height/*DisplayUtil.dip2px(context, 370)*/);
        this.setWidth(width);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.popWindow_animation);
        this.setOutsideTouchable(true);
        this.update();
//        ColorDrawable dw = new ColorDrawable(0000000000);
//        this.setBackgroundDrawable(dw);

    }

    public void setListener(View.OnClickListener listener) {

    }

    @Override
    public void reItemClick(String name) {
        if (mListener != null) {
            mListener.syncName(name);
        }
    }

    public interface NiaPopupClickListener {
        void syncName(String name);
    }

    public void setNiaPopupClickListener(NiaPopupClickListener listener) {
        mListener = listener;
    }

    private NiaPopupClickListener mListener;
}
