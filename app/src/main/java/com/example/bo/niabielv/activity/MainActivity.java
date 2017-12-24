package com.example.bo.niabielv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.bo.niabielv.R;

public class MainActivity extends AppCompatActivity {

    private TextView mAdd;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        mAdd = findViewById(R.id.add_account);
        mRecyclerView = findViewById(R.id.main_recycle);
    }

    private void initEvent() {
        mAdd.setOnClickListener(v -> toAddAccountActivity());
    }

    private void toAddAccountActivity() {
        Intent intent = new Intent(this, AddAccountActivity.class);
        startActivity(intent);
    }
}
