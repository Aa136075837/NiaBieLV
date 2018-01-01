package com.example.bo.niabielv.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.bean.PartsDetailsBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by bo on 2017/12/24.
 */
public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.DetailsHolder> {
    private RecycleItemClickListener mListener;


    @Override
    public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_details, parent, false);
        return new DetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailsHolder holder, int position) {
        PartsDetailsBean bean = data.get(position);
        holder.mDetailsPayer.setText(bean.getName());
        DecimalFormat df = new DecimalFormat ("#0.00");

        holder.mInMoney.setText(df.format((bean.getInMoney() - bean.getOutMoney())) + "元");
        holder.mOutMoney.setText(df.format(bean.getOutMoney()) + "元");
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private List<PartsDetailsBean> data;

    public void setData(List<PartsDetailsBean> value) {
        data = value;
        notifyDataSetChanged();
    }

    public interface RecycleItemClickListener {
        void reItemClick(String name);
    }

    public void setRecycleItemClickListener(@NonNull RecycleItemClickListener listener) {
        mListener = listener;
    }

    static class DetailsHolder extends RecyclerView.ViewHolder {

        private final TextView mDetailsPayer;
        private final TextView mInMoney;
        private final TextView mOutMoney;

        public DetailsHolder(View itemView) {
            super(itemView);
            mDetailsPayer = itemView.findViewById(R.id.item_details_payer_tv);
            mInMoney = itemView.findViewById(R.id.item_in_money_tv);
            mOutMoney = itemView.findViewById(R.id.item_out_money_tv);

        }
    }
}
