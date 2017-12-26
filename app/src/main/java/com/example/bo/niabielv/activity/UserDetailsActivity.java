package com.example.bo.niabielv.activity;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.adapter.UserDetailsAdapter;
import com.example.bo.niabielv.bean.PartsDetailsBean;
import com.example.bo.niabielv.http.Load;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserDetailsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private UserDetailsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        initView();
        initEvent();
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
        getUserDetails();
    }

    private void getUserDetails(){
        Load.createApi().userDetails().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<PartsDetailsBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<PartsDetailsBean> value) {
                mAdapter.setData(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
