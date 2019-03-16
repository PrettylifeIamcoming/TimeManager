package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.adapter.TaskAdapter;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;
import com.prettylifeiamcoming.timemanager.dialog.SetOverduerTaskDialogFragment;
import com.prettylifeiamcoming.timemanager.dialog.SetTaskDialogFragment;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class OverdueTaskActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RealmHelper mRealmHelper;
    private TaskAdapter mTaskAdapter;
    private List<Task> mTasks;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdue_task_table);

        //设置toolbar
        Toolbar toolbar = findViewById(R.id.overdue_task_table_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //设置ImageView和TextView返回MainActivity
        ImageView mImageView = findViewById(R.id.overdue_task_table_toolbar_return);
        TextView mTextView = findViewById(R.id.overdue_task_table_toolbar_return_word);

        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(OverdueTaskActivity.this, MainActivity.class);
            startActivity(intent);
        });
        mTextView.setOnClickListener(v -> {
            Intent intent = new Intent(OverdueTaskActivity.this, MainActivity.class);
            startActivity(intent);
        });

        //RecyclerView设置
        mRecyclerView = findViewById(R.id.recycler_view_overdue_task_table);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_overdue_task_table);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTaskAdapter.updateData(mRealmHelper.queryOverdueTask());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        initData();

//        updateUI();

        addListener();
    }

    @Override
    protected void onResume() {
        mTaskAdapter.updateData(mRealmHelper.queryOverdueTask());
        super.onResume();
    }

    private void addListener() {
        mTaskAdapter.setOnItemClickListener((view, position) -> {
            SetOverduerTaskDialogFragment setOverdueTaskDialogFragment = new SetOverduerTaskDialogFragment();
            setOverdueTaskDialogFragment.setTask(mTasks.get(position));
            setOverdueTaskDialogFragment.show(getSupportFragmentManager(), "SetOverdueTaskDialog");
        });
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

        mTasks = mRealmHelper.queryOverdueTask();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mTaskAdapter = new TaskAdapter(this, mTasks, R.layout.item_task_table);
        mRecyclerView.setAdapter(mTaskAdapter);
    }

}
