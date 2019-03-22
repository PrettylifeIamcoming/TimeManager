package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.adapter.MainDayAdapter;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.RealmObject;

public class ShowOneDayActivity extends AppCompatActivity {
    private TextView title;
    private RecyclerView recyclerView;
    private MainDayAdapter oneDayAdapter;
    private List<RealmObject> mList = new ArrayList<>();
    private RealmHelper mRealmHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_one_day);

        ImageView mImageView = findViewById(R.id.activity_show_one_day_toolbar_return);
        TextView mTextView = findViewById(R.id.activity_show_one_day_toolbar_return_word);

        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(ShowOneDayActivity.this, MainActivity.class);
            startActivity(intent);
        });
        mTextView.setOnClickListener(v -> {
            Intent intent = new Intent(ShowOneDayActivity.this, MainActivity.class);
            startActivity(intent);
        });


        title = findViewById(R.id.activity_show_one_day_title);
        recyclerView = findViewById(R.id.recycler_show_one_day);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //获取日期
        DateFormat fmt = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        title.setText(fmt.format(new Date(getIntent().getLongExtra("timestamp", 0))));

        new Date();
        initData();
    }

    //从数据库中读取数据
    private void initData() {
//        mList.clear();
        mRealmHelper = new RealmHelper(this);

        mList = mRealmHelper.queryOneDay(getIntent().getLongExtra("timestamp", 0));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        this.oneDayAdapter = new MainDayAdapter(this, mList);
        recyclerView.setAdapter(oneDayAdapter);
    }
}
