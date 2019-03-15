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
import com.prettylifeiamcoming.timemanager.adapter.ScheduleAdapter;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ScheduleTableActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RealmHelper mRealmHelper;
    private List<Schedule> mSchedules = new ArrayList<>();
    private ScheduleAdapter mScheduleAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_table);

        //设置toolbar
        Toolbar toolbar = findViewById(R.id.schedule_table_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //设置ImageView和TextView返回MainActivity
        ImageView mImageView = findViewById(R.id.schedule_table_toolbar_return);
        TextView mTextView = findViewById(R.id.schedule_table_toolbar_return_word);

        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(ScheduleTableActivity.this, MainActivity.class);
            startActivity(intent);
        });
        mTextView.setOnClickListener(v -> {
            Intent intent = new Intent(ScheduleTableActivity.this, MainActivity.class);
            startActivity(intent);
        });

        //RecyclerView设置
        mRecyclerView = findViewById(R.id.recycler_view_schedule_table);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_schedule_table);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mScheduleAdapter.updateData(mRealmHelper.queryScheduleTable());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        initData();

        Snackbar.make(mRecyclerView, "滑动删除日程、点击日程进入修改界面", Snackbar.LENGTH_LONG).show();
        setSwipeDelete();

//        updateUI();
    }

    @Override
    protected void onResume() {
        mScheduleAdapter.updateData(mRealmHelper.queryScheduleTable());
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

    //toolbar菜单填充
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.schedule_table_toolbar, menu);
        return true;
    }

    //从数据库中读取数据
    private void initData() {
        mRealmHelper = new RealmHelper(this);

        mSchedules = mRealmHelper.queryScheduleTable();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mScheduleAdapter = new ScheduleAdapter(this, mSchedules, R.layout.item_schedule_table);
        mRecyclerView.setAdapter(mScheduleAdapter);
    }

    //删除日程
    private void deleteSchedule() {
        Snackbar.make(mRecyclerView, "滑动删除日程、点击日程进入修改界面", Snackbar.LENGTH_LONG).show();
    }

    private void setSwipeDelete() {
        DefaultItemTouchHelpCallback mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                //删除数据库数据
                mRealmHelper.deleteSchedule(mSchedules.get(adapterPosition).getScheduleID());
                //滑动删除
                mSchedules.remove(adapterPosition);
                mScheduleAdapter.notifyItemRemoved(adapterPosition);
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
            case R.id.schedule_table_toolbar_add:
                Intent intent = new Intent(ScheduleTableActivity.this, AddScheduleActivity.class);
                startActivity(intent);
                //Toast.makeText(this, "You clicked Add Schedule", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.schedule_table_toolbar_delete:
//                //Toast.makeText(this, "You clicked Delete Schedule", Toast.LENGTH_SHORT).show();
//                deleteSchedule();
//                break;
            default:
                break;
        }
        return true;
    }
}
