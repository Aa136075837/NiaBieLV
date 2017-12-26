package com.example.bo.niabielv.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.bean.AccountBean;

import java.util.List;

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
        List<AccountBean.ListBean> list = data.getList();
        AccountBean.ListBean bean = list.get(position);
        holder.mPayerTv.setText(bean.getMoney_person());
        holder.mPartsTv.setText(bean.getActor());
        holder.mDate.setText(bean.getDate_time());
        holder.mMoney.setText(bean.getMoney());
        holder.mRemakesTv.setText(bean.getContent());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.getList().size();
    }

    private AccountBean data;

    public void setData(AccountBean value) {
        data = value;
        notifyDataSetChanged();
    }

    static class AuditHolder extends RecyclerView.ViewHolder {

        private final View mLeftView;
        private final TextView mPartsTv;
        private final TextView mPayerTv;
        private final TextView mDate;
        private final TextView mMoney;
        private final TextView mRemakesTv;

        public AuditHolder(View itemView) {
            super(itemView);
            mLeftView = itemView.findViewById(R.id.item_left_view);
            mPartsTv = itemView.findViewById(R.id.item_parts);
            mPayerTv = itemView.findViewById(R.id.item_payer);
            mDate = itemView.findViewById(R.id.item_date);
            mMoney = itemView.findViewById(R.id.item_money);
            mRemakesTv = itemView.findViewById(R.id.item_remakes);
        }
    }
}
