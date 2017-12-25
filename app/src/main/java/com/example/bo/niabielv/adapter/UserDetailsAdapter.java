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
public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.DetailsHolder> {
    private RecycleItemClickListener mListener;


    @Override
    public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_details, parent, false);
        return new DetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailsHolder holder, int position) {

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

    static class DetailsHolder extends RecyclerView.ViewHolder {

        public DetailsHolder(View itemView) {
            super(itemView);

        }
    }
}
