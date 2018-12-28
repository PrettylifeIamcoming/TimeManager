package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.adapter.ScheduleAdapter;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CompletedScheduleTableActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RealmHelper mRealmHelper;
    private List<Schedule> mSchedules = new ArrayList<>();
    private ScheduleAdapter mScheduleAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_schedule_table);

        //设置ImageView和TextView返回ScheduleTableActivity
        ImageView mImageView = findViewById(R.id.completed_schedule_table_toolbar_return);
        TextView mTextView = findViewById(R.id.completed_schedule_table_toolbar_return_word);

        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(CompletedScheduleTableActivity.this, MainActivity.class);
            startActivity(intent);
        });
        mTextView.setOnClickListener(v -> {
            Intent intent = new Intent(CompletedScheduleTableActivity.this, MainActivity.class);
            startActivity(intent);
        });

        //RecyclerView设置
        mRecyclerView = findViewById(R.id.recycler_view_completed_schedule_table);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        initData();
    }

    //从数据库中读取数据
    private void initData() {
        mRealmHelper = new RealmHelper(this);

        this.mSchedules = mRealmHelper.queryCompletedSchedule();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.mScheduleAdapter = new ScheduleAdapter(this, mSchedules, R.layout.item_schedule_table);
        mRecyclerView.setAdapter(this.mScheduleAdapter);
    }

}
