package com.prettylifeiamcoming.timemanager.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private EditText mEditText1;
    private EditText mEditText2;

    private Spinner mSpinner1;
    private Spinner mSpinner2;


    private EditText mEditText7;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //设置ImageView和TextView返回TaskTableActivity
        ImageView mImageView = findViewById(R.id.add_task_toolbar_return);
        TextView mTextView = findViewById(R.id.add_task_toolbar_return_word);

        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(AddTaskActivity.this, TaskTableActivity.class);
            startActivity(intent);
        });
        mTextView.setOnClickListener(v -> {
            Intent intent = new Intent(AddTaskActivity.this, TaskTableActivity.class);
            startActivity(intent);
        });

        //设置两个按钮
        Button mButton1 = findViewById(R.id.add_task_ok);
        Button mButton2 = findViewById(R.id.add_task_cancel);

        mButton1.setOnClickListener(v -> {
            Date date = new Date();
            DateFormat fmt = new SimpleDateFormat("yyyy.MM.dd/kk:mm",Locale.getDefault());
            //判断输入是否有误
            if (TextUtils.isEmpty(mEditText1.getText().toString())) {                 //任务名称不能为空
                hint(1);
            } else {
                if (TextUtils.isEmpty(mEditText2.getText().toString())) {            //日期不能为空
                    hint(2);
                } else {
                    long f = 0, h = 0;
                    try {
                        f = fmt.parse(mEditText2.getText().toString()).getTime();
                        h = fmt.parse(fmt.format(new Date())).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (f < h) {                                   //日期不能小于当前日期
                        hint(0);
                    } else {
                        if (TextUtils.isEmpty(mEditText7.getText().toString())) {     //自定义等级不能为空
                            hint(3);
                        } else {
                            //这个位置写数据操作，添加task进入到数据库中
                            Task task = new Task();

                            //提取任务名称
                            task.setTaskName(mEditText1.getText().toString());

                            //提取deadline
                            try {
                                date = fmt.parse(mEditText2.getText().toString());
                                task.setDeadline(date.getTime());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            //提取任务类型
                            String a = mSpinner1.getSelectedItem().toString();
                            if (a.equals("学习") || a.equals("Study")) {
                                task.setTaskType(1);
                            }
                            if (a.equals("社交") || a.equals("Social")) {
                                task.setTaskType(2);
                            }
                            if (a.equals("工作") || a.equals("Work")) {
                                task.setTaskType(3);
                            }
                            if (a.equals("娱乐") || a.equals("Play")) {
                                task.setTaskType(4);
                            }
                            if (a.equals("睡觉") || a.equals("Sleep")) {
                                task.setTaskType(5);
                            }
                            if (a.equals("其它") || a.equals("Others")) {
                                task.setTaskType(6);
                            }

                            //提取任务四象限等级
                            String b = mSpinner2.getSelectedItem().toString();
                            if (b.equals("重要且紧急") || b.equals("Important and Urgent")) {
                                task.setTaskLevel(1);
                            }
                            if (b.equals("重要但不紧急") || b.equals("Important But Not Urgent")) {
                                task.setTaskLevel(2);
                            }
                            if (b.equals("不重要但紧急") || b.equals("Not Important But Urgent")) {
                                task.setTaskLevel(3);
                            }
                            if (b.equals("既不重要又不紧急") || b.equals("Neither Important Nor Urgent")) {
                                task.setTaskLevel(4);
                            }
                            task.setTaskCustomLevel(Integer.parseInt(mEditText7.getText().toString()));
                            task.setDuration(task.getTerminalTimestamp()-task.getBeginTimestamp());
                            RealmHelper realmHelper = new RealmHelper(AddTaskActivity.this);
                            realmHelper.addTask(task);

                            Intent intent = new Intent(AddTaskActivity.this, TaskTableActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        });

        mButton2.setOnClickListener(v -> {
            Intent intent = new Intent(AddTaskActivity.this, TaskTableActivity.class);
            startActivity(intent);
            finish();
        });

        //设置每一个EditText和mSpinner的输入控制
        mEditText1 = findViewById(R.id.add_task_name);
        mEditText2 = findViewById(R.id.add_task_deadline);
        mSpinner1 = findViewById(R.id.add_task_type);
        mSpinner2 = findViewById(R.id.add_task_level);
        mEditText7 = findViewById(R.id.add_task_custom_level);

        //deadline控制输入
        mEditText2.setInputType(InputType.TYPE_NULL);
        mEditText2.setOnFocusChangeListener((v, hasFocus) -> {
            // 日期对话框
            if (hasFocus) {
                showDatePickerDialog();
            }
        });
        mEditText2.setOnClickListener(v -> showDatePickerDialog());


        //为Spinner设置内容
        //Spinner1，设置任务类型
        List<String> dataList1 = new ArrayList<>();
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

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataList1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner1.setAdapter(adapter1);
        mSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner2，设置四象限等级
        List<String> dataList2 = new ArrayList<>();
        String string7 = getString(R.string.add_task_level_first);
        String string8 = getString(R.string.add_task_level_second);
        String string9 = getString(R.string.add_task_level_third);
        String string10 = getString(R.string.add_task_level_fourth);
        dataList2.add(string7);
        dataList2.add(string8);
        dataList2.add(string9);
        dataList2.add(string10);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataList2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner2.setAdapter(adapter2);
    }

    /**
     * 展示日期选择对话框
     */
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(AddTaskActivity.this, (view, year, monthOfYear, dayOfMonth) -> {
            final String a = year + "." + (monthOfYear + 1) + "." + dayOfMonth;
            // 时间对话框
            new TimePickerDialog(AddTaskActivity.this, (view1, hourOfDay, minute) -> {
                String b = null;
                if (minute < 10) {
                    b = hourOfDay + ":0" + minute;
                } else {
                    b = hourOfDay + ":" + minute;
                }
                String c = a + "/" + b;
                mEditText2.setText(c);
            }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true).show();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //错误提示
    private void hint(int i) {
        switch (i) {
            case 0:
                Toast.makeText(this, R.string.add_task_hint_deadline_time, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, R.string.add_task_hint_name, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, R.string.add_task_hint_deadline, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, R.string.add_task_hint_custom_level, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
