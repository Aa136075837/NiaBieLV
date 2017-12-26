package com.example.bo.niabielv.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bo.niabielv.R;
import com.example.bo.niabielv.adapter.AccountAdapter;
import com.example.bo.niabielv.bean.AccountBean;
import com.example.bo.niabielv.http.Load;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ImageView mAdd;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private AccountAdapter mAdapter;
    private TextView mTotalMoney;

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
        mRefreshLayout = findViewById(R.id.main_refresh);
        mTotalMoney = findViewById(R.id.main_total_tv);
        findViewById(R.id.main_account).setOnClickListener(view -> {
            Intent intent = new Intent(this, UserDetailsActivity.class);
            startActivity(intent);
        });
    }

    private void initEvent() {
        mAdd.setOnClickListener(v -> toAddAccountActivity());
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });

        mAdapter = new AccountAdapter(this, R.layout.item_account);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void toAddAccountActivity() {
        Intent intent = new Intent(this, AddAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);
        flashItem();
    }

    @Override
    protected void onStart() {
        super.onStart();
        flashItem();
    }

    private void flashItem() {
        Load.createApi().searchAccount().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AccountBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AccountBean value) {
                mAdapter.setData(value);
                mTotalMoney.setText(value.getAllMoney() + "元");
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
