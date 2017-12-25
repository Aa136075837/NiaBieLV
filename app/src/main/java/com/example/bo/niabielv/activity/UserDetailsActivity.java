package com.example.bo.niabielv.activity;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.adapter.UserDetailsAdapter;

public class UserDetailsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        initView();
        initEvent();
    }

    private void initEvent() {
        UserDetailsAdapter adapter = new UserDetailsAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.user_details_recycle);
    }
}
