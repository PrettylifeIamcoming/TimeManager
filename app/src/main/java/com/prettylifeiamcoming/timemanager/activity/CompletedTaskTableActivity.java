package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.adapter.CompletedTaskAdapter;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CompletedTaskTableActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CompletedTaskAdapter mTaskAdapter;
    private RealmHelper mRealmHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_task_table);

        //设置ImageView和TextView返回TaskTableActivity
        ImageView mImageView = findViewById(R.id.completed_task_table_toolbar_return);
        TextView mTextView = findViewById(R.id.completed_task_table_toolbar_return_word);

        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(CompletedTaskTableActivity.this, MainActivity.class);
            startActivity(intent);
        });
        mTextView.setOnClickListener(v -> {
            Intent intent = new Intent(CompletedTaskTableActivity.this, MainActivity.class);
            startActivity(intent);
        });

        //RecyclerView设置
        mRecyclerView = findViewById(R.id.recycler_view_completed_task_table);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_completed_task_table);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTaskAdapter.updateData(mRealmHelper.queryCompletedTask());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        initData();

//        updateUI();
    }

    @Override
    protected void onResume() {
        mTaskAdapter.updateData(mRealmHelper.queryCompletedTask());
        super.onResume();
    }

    //    public void updateUI() {
//        this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                //此时已在主线程中，可以更新UI了
//                try {
//                    Thread.sleep(1000);
//                    onResume();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    //从数据库中读取数据
    private void initData() {
        mRealmHelper = new RealmHelper(this);

        List<Task> mTasks = mRealmHelper.queryCompletedTask();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mTaskAdapter = new CompletedTaskAdapter(this, mTasks, R.layout.item_completed_task);
        mRecyclerView.setAdapter(mTaskAdapter);
    }
}
