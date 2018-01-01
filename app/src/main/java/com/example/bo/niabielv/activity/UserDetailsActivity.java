package com.example.bo.niabielv.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.adapter.UserDetailsAdapter;
import com.example.bo.niabielv.base.MVPBaseActivity;
import com.example.bo.niabielv.bean.PartsDetailsBean;
import com.example.bo.niabielv.presenter.AddAccountContract;

import java.util.List;

public class UserDetailsActivity extends MVPBaseActivity<AddAccountContract.AddAccountPresenter> implements AddAccountContract.AddAccountView {

    private RecyclerView mRecyclerView;
    private UserDetailsAdapter mAdapter;

    @Override
    protected AddAccountContract.AddAccountPresenter createPresenter() {
        return new AddAccountContract.AddAccountPresenter(this, this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_user_details;
    }

    @Override
    protected void initViews() {
        initView();
    }

    @Override
    protected void initEvents() {
        initEvent();
    }

    @Override
    protected void initParams(Bundle extras) {

    }

    private void initEvent() {
        mAdapter = new UserDetailsAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.user_details_recycle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getUserDetails();
    }


    @Override
    public void getDetailsSuccess(List<PartsDetailsBean> value) {
        if (mAdapter != null) {
            mAdapter.setData(value);
        }
    }
}
