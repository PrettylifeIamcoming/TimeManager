package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;

import androidx.appcompat.app.AppCompatActivity;

public class CompletedTaskTableActivity extends AppCompatActivity {

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
    }
}
