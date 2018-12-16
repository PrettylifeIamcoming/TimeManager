package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.adapter.TaskAdapter;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TaskTableActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_table);

        //设置toolbar
        Toolbar toolbar = findViewById(R.id.task_table_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //设置ImageView和TextView返回MainActivity
        ImageView mImageView = findViewById(R.id.task_table_toolbar_return);
        TextView mTextView = findViewById(R.id.task_table_toolbar_return_word);

        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(TaskTableActivity.this, MainActivity.class);
            startActivity(intent);
        });
        mTextView.setOnClickListener(v -> {
            Intent intent = new Intent(TaskTableActivity.this, MainActivity.class);
            startActivity(intent);
        });

        //RecyclerView设置
        mRecyclerView = findViewById(R.id.recycler_view_task_table);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        initData();

    }

    //从数据库中读取数据
    private void initData() {
        RealmHelper mRealmHelper = new RealmHelper(this);

        List<Task> mTasks = mRealmHelper.queryAllTask();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        TaskAdapter mAdapter = new TaskAdapter(this, mTasks, R.layout.item_task_table);
        mRecyclerView.setAdapter(mAdapter);

    }

    //toolbar菜单填充
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_table_toolbar, menu);
        return true;
    }

    //toolbar菜单栏按钮监控
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.task_table_toolbar_add:
                Intent intent = new Intent(TaskTableActivity.this, AddTaskActivity.class);
                startActivity(intent);
                Toast.makeText(this, "You clicked Add Task", Toast.LENGTH_SHORT).show();
                break;
            case R.id.task_table_toolbar_delete:
                Toast.makeText(this, "You clicked Delete Task", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
