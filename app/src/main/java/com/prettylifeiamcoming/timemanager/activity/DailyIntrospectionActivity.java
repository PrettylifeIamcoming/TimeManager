package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;

import androidx.appcompat.app.AppCompatActivity;

public class DailyIntrospectionActivity extends AppCompatActivity {
    private ImageView mImageView;
    private TextView mTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_introspection);

        //设置ImageView和TextView返回TaskTableActivity
        mImageView = findViewById(R.id.daily_introspection_toolbar_return);
        mTextView = findViewById(R.id.daily_introspection_toolbar_return_word);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyIntrospectionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyIntrospectionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
