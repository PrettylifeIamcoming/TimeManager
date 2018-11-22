package com.prettylifeiamcoming.timemanager.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class AddScheduleActivity extends AppCompatActivity {
    private ImageView mImageView;
    private TextView mTextView;
    private EditText mEditText1;
    private EditText mEditText2;
    private EditText mEditText3;
    private Spinner mSpinner;
    private Button mButton1;
    private Button mButton2;

    private List<String> dataList1;
    private ArrayAdapter<String> adapter1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        //设置ImageView和TextView返回scheduleTableActivity
        mImageView = findViewById(R.id.add_schedule_toolbar_return);
        mTextView = findViewById(R.id.add_schedule_toolbar_return_word);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddScheduleActivity.this, ScheduleTableActivity.class);
                startActivity(intent);
            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddScheduleActivity.this, ScheduleTableActivity.class);
                startActivity(intent);
            }
        });

        //设置两个按钮
        mButton1 = findViewById(R.id.add_schedule_ok);
        mButton2 = findViewById(R.id.add_schedule_cancel);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                DateFormat fmt = new SimpleDateFormat("yyyy.MM.dd/kk:mm");
                long f = 0, h = 0;

                if (TextUtils.isEmpty(mEditText1.getText().toString())){            //日程名称不能为空
                    hint(1);
                }else {
                    if (TextUtils.isEmpty(mEditText2.getText().toString())){        //起始时间不能为空
                        hint(2);
                    }else {
                        try {
                            f = fmt.parse(mEditText2.getText().toString()).getTime();
                            h = fmt.parse(fmt.format(new Date())).getTime();
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                        if (f <  h){                                   //起始时间不能小于当前时间
                            hint(0);
                        }else {
                            if (TextUtils.isEmpty(mEditText3.getText().toString())){
                                hint(3);
                            }else {
                                try {
                                    f = fmt.parse(mEditText3.getText().toString()).getTime();
                                    h = fmt.parse(fmt.format(new Date())).getTime();
                                }catch (ParseException e){
                                    e.printStackTrace();
                                }
                                if (f < h){                                   //终止时间不能小于当前时间
                                    hint(5);
                                }else {
                                    try {
                                        f = fmt.parse(mEditText2.getText().toString()).getTime();
                                        h = fmt.parse(mEditText3.getText().toString()).getTime();
                                    }catch (ParseException e){
                                        e.printStackTrace();
                                    }
                                    if (h < f)                         //起始时间不能小于终止时间
                                    {
                                        hint(4);
                                    }else {
                                        //这个位置写数据操作，添加schedule
                                        Schedule schedule = new Schedule();
                                        //提取日程名称
                                        schedule.setScheduleName(mEditText1.getText().toString());
                                        //提取起始时间

                                        try {
                                            date = fmt.parse(mEditText2.getText().toString());
                                            schedule.setBeginTimestamp(date.getTime());
                                        }catch (ParseException e){
                                            e.printStackTrace();
                                        }
                                        //提取截止时间
                                        try {
                                            date = fmt.parse(mEditText3.getText().toString());
                                            schedule.setTerminalTimestamp(date.getTime());
                                        }catch (ParseException e){
                                            e.printStackTrace();
                                        }

                                        //提取日程类型
                                        String a = mSpinner.getSelectedItem().toString();
                                        if (a.equals("学习")  || a.equals("Study")){
                                            schedule.setScheduleType(1);
                                        }
                                        if (a.equals("社交") || a.equals("Social")){
                                            schedule.setScheduleType(2);
                                        }
                                        if (a.equals("工作") || a.equals("Work")){
                                            schedule.setScheduleType(3);
                                        }
                                        if (a.equals("娱乐") || a.equals("Play")){
                                            schedule.setScheduleType(4);
                                        }
                                        if (a.equals("睡觉") || a.equals("Sleep")){
                                            schedule.setScheduleType(5);
                                        }
                                        if (a.equals("其它") || a.equals("Others")){
                                            schedule.setScheduleType(6);
                                        }
                                        RealmHelper realmHelper = new RealmHelper(AddScheduleActivity.this);
                                        realmHelper.addSchedule(schedule);

                                        Intent intent = new Intent(AddScheduleActivity.this, ScheduleTableActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddScheduleActivity.this, ScheduleTableActivity.class);
                startActivity(intent);
            }
        });

        //设置每一个EditText的输入控制
        mEditText1 = findViewById(R.id.add_schedule_name);
        mEditText2 = findViewById(R.id.add_schedule_begin_time);
        mEditText3 = findViewById(R.id.add_schedule_terminal_time);
        mSpinner = findViewById(R.id.add_schedule_type);

        //起始时间控制输入
        mEditText2.setInputType(InputType.TYPE_NULL);
        mEditText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // 日期对话框
                if(hasFocus) {
                    showDatePickerDialog1();
                }
            }
        });
        mEditText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog1();
            }
        });

        //终止时间控制
        mEditText3.setInputType(InputType.TYPE_NULL);
        mEditText3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // 日期对话框
                if(hasFocus) {
                    showDatePickerDialog2();
                }
            }
        });
        mEditText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog2();
            }
        });

        //为Spinner设置内容
        dataList1 = new ArrayList<>();
        String string1 = getString(R.string.add_task_study);
        String string2 = getString(R.string.add_task_social);
        String string3 = getString(R.string.add_task_work);
        String string4 = getString(R.string.add_task_play);
        String string5 = getString(R.string.add_task_sleep);
        String string6 = getString(R.string.add_task_others);
        dataList1.add(string1);
        dataList1.add(string2);
        dataList1.add(string3);
        dataList1.add(string4);
        dataList1.add(string5);
        dataList1.add(string6);
        adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,dataList1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter1);
    }

    /**
     * 展示日期选择对话框
     */
    private void showDatePickerDialog1() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(AddScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                final String a = year+"."+(monthOfYear+1)+"."+dayOfMonth;
                // 时间对话框
                new TimePickerDialog(AddScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String b = null;
                        if (minute <10){
                            b = hourOfDay + ":0" + minute;
                        }else {
                            b = hourOfDay + ":" + minute;
                        }
                        String c = a + "/" + b;
                        mEditText2.setText(c);
                    }
                }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true).show();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showDatePickerDialog2() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(AddScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                final String a = year+"."+(monthOfYear+1)+"."+dayOfMonth;
                // 时间对话框
                new TimePickerDialog(AddScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String b = null;
                        if (minute <10){
                            b = hourOfDay + ":0" + minute;
                        }else {
                            b = hourOfDay + ":" + minute;
                        }
                        String c = a + "/" + b;
                        mEditText3.setText(c);
                    }
                }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true).show();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //测试用
    private void hint(int i){
        switch (i) {
            case 0:
                Toast.makeText(this, "起始时间不能小于当前时间", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "起始时间不能为空", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "日程名称不能为空", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this,"终止时间不能为空",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this,"起始时间不可以小于终止时间",Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "终止时间不能小于当前时间", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
