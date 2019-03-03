package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.adapter.DefaultItemTouchHelpCallback;
import com.prettylifeiamcoming.timemanager.adapter.TaskAdapter;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;
import com.prettylifeiamcoming.timemanager.dialog.SetTaskDialogFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TaskTableActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;
    private List<Task> mTasks = new ArrayList<>();
    private RealmHelper mRealmHelper;

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

        addListener();
    }

    //从数据库中读取数据
    private void initData() {
        mRealmHelper = new RealmHelper(this);

        this.mTasks = mRealmHelper.queryTaskTable();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.mTaskAdapter = new TaskAdapter(this, mTasks, R.layout.item_task_table);
        mRecyclerView.setAdapter(this.mTaskAdapter);

        setSwipeDelete();

        Snackbar.make(mRecyclerView, "滑动删除任务、点击任务进入修改界面", Snackbar.LENGTH_LONG).show();
    }

    //toolbar菜单填充
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_table_toolbar, menu);
        return true;
    }

    private void addListener() {
        mTaskAdapter.setOnItemClickListener((view, position) -> {
            SetTaskDialogFragment setTaskDialogFragment = new SetTaskDialogFragment();
            setTaskDialogFragment.setTask(mTasks.get(position));
            setTaskDialogFragment.show(getSupportFragmentManager(), "SetTaskDialog");
        });
    }

    //任务删除
    private void deleteTask() {
        Snackbar.make(mRecyclerView, "滑动删除任务、点击任务进入修改界面", Snackbar.LENGTH_LONG).show();
    }

    private void setSwipeDelete() {
        DefaultItemTouchHelpCallback mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                //删除数据库数据
                mRealmHelper.deleteTask(mTasks.get(adapterPosition).getTaskID());
                //滑动删除
                mTasks.remove(adapterPosition);
                mTaskAdapter.notifyItemRemoved(adapterPosition);
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {

                return false;
            }
        });
        mCallback.setDragEnable(false);
        mCallback.setSwipeEnable(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    //toolbar菜单栏按钮监控
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.task_table_toolbar_add:
                Intent intent = new Intent(TaskTableActivity.this, AddTaskActivity.class);
                startActivity(intent);
                //Toast.makeText(this, "You clicked Add Task", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.task_table_toolbar_delete:
//                //Toast.makeText(this, "You clicked Delete Task", Toast.LENGTH_SHORT).show();
//                deleteTask();
//                break;
            default:
                break;
        }
        return true;
    }

}