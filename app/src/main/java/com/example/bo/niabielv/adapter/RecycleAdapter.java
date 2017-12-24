package com.example.bo.niabielv.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.constant.AppConstant;

/**
 * Created by bo on 2017/12/24.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.NiaViewHolder> {
    private RecycleItemClickListener mListener;


    @Override
    public NiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor, parent, false);
        return new NiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NiaViewHolder holder, int position) {
        holder.mName.setText(AppConstant.names[position]);
        holder.mName.setOnClickListener(view -> {
            if (mListener == null) return;
            mListener.reItemClick(AppConstant.names[position]);
        });
    }

    @Override
    public int getItemCount() {
        return AppConstant.names.length;
    }

    public interface RecycleItemClickListener {
        void reItemClick(String name);
    }

    public void setRecycleItemClickListener(@NonNull RecycleItemClickListener listener) {
        mListener = listener;
    }

    static class NiaViewHolder extends RecyclerView.ViewHolder {

        private final TextView mName;

        public NiaViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.item_name);
        }
    }
}
