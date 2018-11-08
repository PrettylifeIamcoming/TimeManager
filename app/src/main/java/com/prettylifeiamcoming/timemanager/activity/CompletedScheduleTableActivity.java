package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;

import androidx.appcompat.app.AppCompatActivity;

public class CompletedScheduleTableActivity extends AppCompatActivity {
    private ImageView mImageView;
    private TextView mTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_schedule_table);

        //设置ImageView和TextView返回ScheduleTableActivity
        mImageView = findViewById(R.id.completed_schedule_table_toolbar_return);
        mTextView = findViewById(R.id.completed_schedule_table_toolbar_return_word);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletedScheduleTableActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletedScheduleTableActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
