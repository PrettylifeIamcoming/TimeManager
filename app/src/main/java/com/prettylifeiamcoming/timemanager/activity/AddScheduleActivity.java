package com.prettylifeiamcoming.timemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;
import com.prettylifeiamcoming.timemanager.dialog.SetDateDialog;
import com.prettylifeiamcoming.timemanager.util.TaskOrScheduleTypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class AddScheduleActivity extends AppCompatActivity {
    private EditText mEditText1;
    private EditText mEditText2;
    private EditText mEditText3;
    private EditText mEditText4;
    private Spinner mSpinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        //设置ImageView和TextView返回scheduleTableActivity
        ImageView mImageView = findViewById(R.id.add_schedule_toolbar_return);
        TextView mTextView = findViewById(R.id.add_schedule_toolbar_return_word);

        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(AddScheduleActivity.this, ScheduleTableActivity.class);
            startActivity(intent);
        });
        mTextView.setOnClickListener(v -> {
            Intent intent = new Intent(AddScheduleActivity.this, ScheduleTableActivity.class);
            startActivity(intent);
        });

        //设置每一个EditText的输入控制
        mEditText1 = findViewById(R.id.add_schedule_name);
        mEditText2 = findViewById(R.id.add_schedule_begin_time);
        mEditText3 = findViewById(R.id.add_schedule_terminal_time);
        mEditText4 = findViewById(R.id.add_schedule_place);
        mSpinner = findViewById(R.id.add_schedule_type);

        //起始时间控制输入
        mEditText2.setInputType(InputType.TYPE_NULL);
        mEditText2.setOnFocusChangeListener((v, hasFocus) -> {
            // 日期对话框
            if (hasFocus) {
                SetDateDialog.showDatePickerDialog(this, mEditText2);
            }
        });
        mEditText2.setOnClickListener(v -> SetDateDialog.showDatePickerDialog(this, mEditText2));

        //终止时间控制
        mEditText3.setInputType(InputType.TYPE_NULL);
        mEditText3.setOnFocusChangeListener((v, hasFocus) -> {
            // 日期对话框
            if (hasFocus) {
                SetDateDialog.showDatePickerDialog(this, mEditText3);
            }
        });
        mEditText3.setOnClickListener(v -> SetDateDialog.showDatePickerDialog(this, mEditText3));

        //设置两个按钮
        Button mButton1 = findViewById(R.id.add_schedule_ok);
        Button mButton2 = findViewById(R.id.add_schedule_cancel);

        mButton1.setOnClickListener(v -> {
            Date date = new Date();
            DateFormat fmt = new SimpleDateFormat("yyyy.MM.dd/kk:mm",Locale.getDefault());
            long f = 0, h = 0;

            if (TextUtils.isEmpty(mEditText1.getText().toString())) {            //日程名称不能为空
                Toast.makeText(this, R.string.add_schedule_hint_name, Toast.LENGTH_SHORT).show();
            } else {
                if (TextUtils.isEmpty(mEditText4.getText().toString())) {        //日程地点不能为空
                    Toast.makeText(this, R.string.add_schedule_hint_place, Toast.LENGTH_SHORT).show();
                } else {
                    if (TextUtils.isEmpty(mEditText2.getText().toString())) {        //起始时间不能为空
                        Toast.makeText(this, R.string.add_schedule_hint_begin, Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            f = fmt.parse(mEditText2.getText().toString()).getTime();
                            h = fmt.parse(fmt.format(new Date())).getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (f < h) {                                   //起始时间不能小于当前时间
                            Toast.makeText(this, R.string.add_schedule_hint_begin_time, Toast.LENGTH_SHORT).show();
                        } else {
                            if (TextUtils.isEmpty(mEditText3.getText().toString())) {       //终止时间不能为空
                                Toast.makeText(this, R.string.add_schedule_hint_terminal, Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    f = fmt.parse(mEditText3.getText().toString()).getTime();
                                    h = fmt.parse(fmt.format(new Date())).getTime();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (f < h) {                                   //终止时间不能小于当前时间
                                    Toast.makeText(this, R.string.add_schedule_hint_terminal_time, Toast.LENGTH_SHORT).show();
                                } else {
                                    try {
                                        f = fmt.parse(mEditText2.getText().toString()).getTime();
                                        h = fmt.parse(mEditText3.getText().toString()).getTime();
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    if (h < f)                         //起始时间不能小于终止时间
                                    {
                                        Toast.makeText(this, R.string.add_schedule_hint_begin_terminal, Toast.LENGTH_SHORT).show();
                                    } else {
                                        //这个位置写数据操作，添加schedule
                                        Schedule schedule = new Schedule();
                                        //提取日程名称
                                        schedule.setScheduleName(mEditText1.getText().toString());
                                        //提取日程地点
                                        schedule.setSchedulePlace(mEditText4.getText().toString());
                                        //提取起始时间
                                        try {
                                            date = fmt.parse(mEditText2.getText().toString());
                                            schedule.setBeginTimestamp(date.getTime());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        //提取截止时间
                                        try {
                                            date = fmt.parse(mEditText3.getText().toString());
                                            schedule.setTerminalTimestamp(date.getTime());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        //提取日程类型
                                        TaskOrScheduleTypeConverter.setScheduleType(mSpinner, schedule);

                                        schedule.setDuration(schedule.getTerminalTimestamp() - schedule.getBeginTimestamp() + schedule.getDuration());
                                        RealmHelper realmHelper = new RealmHelper(AddScheduleActivity.this);
                                        realmHelper.addSchedule(schedule);

                                        Intent intent = new Intent(AddScheduleActivity.this, ScheduleTableActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        mButton2.setOnClickListener(v -> {
            Intent intent = new Intent(AddScheduleActivity.this, ScheduleTableActivity.class);
            startActivity(intent);
            finish();
        });
    }
}