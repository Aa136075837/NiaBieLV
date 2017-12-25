package com.example.bo.niabielv.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bo.niabielv.R;

/**
 * Created by Administrator on 2017/12/13.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AuditHolder> {
    private Context mContext;
    private int mResId;

    public AccountAdapter(Context context, @LayoutRes int resId) {
        super();
        mContext = context;
        mResId = resId;
    }

    @Override
    public AuditHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mResId, parent, false);
        return new AuditHolder(view);
    }

    @Override
    public void onBindViewHolder(AuditHolder holder, int position) {
        holder.itemView.setTag(position);
        if (position % 2 == 0) {
            holder.mLeftView.setBackground(mContext.getResources().getDrawable(R.drawable.backgroud_item_left_orange));
        } else {
            holder.mLeftView.setBackground(mContext.getResources().getDrawable(R.drawable.backgroud_item_left_blue));
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    static class AuditHolder extends RecyclerView.ViewHolder {

        private final View mLeftView;

        public AuditHolder(View itemView) {
            super(itemView);
            mLeftView = itemView.findViewById(R.id.item_left_view);
        }
    }
}
