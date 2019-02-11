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
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;
import com.prettylifeiamcoming.timemanager.dialog.SetDateDialog;
import com.prettylifeiamcoming.timemanager.util.TaskLevelSetter;
import com.prettylifeiamcoming.timemanager.util.TaskOrScheduleTypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                SetDateDialog.showDatePickerDialog(this, mEditText2);
            }
        });
        mEditText2.setOnClickListener(v -> SetDateDialog.showDatePickerDialog(this, mEditText2));

        //设置两个按钮
        Button mButton1 = findViewById(R.id.add_task_ok);
        Button mButton2 = findViewById(R.id.add_task_cancel);

        mButton1.setOnClickListener(v -> {
            Date date;
            DateFormat fmt = new SimpleDateFormat("yyyy.MM.dd/kk:mm",Locale.getDefault());
            //判断输入是否有误
            if (TextUtils.isEmpty(mEditText1.getText().toString())) {                 //任务名称不能为空
                Toast.makeText(this, R.string.add_task_hint_name, Toast.LENGTH_SHORT).show();
            } else {
                if (TextUtils.isEmpty(mEditText2.getText().toString())) {            //日期不能为空
                    Toast.makeText(this, R.string.add_task_hint_deadline, Toast.LENGTH_SHORT).show();
                } else {
                    long f = 0, h = 0;
                    try {
                        f = fmt.parse(mEditText2.getText().toString()).getTime();
                        h = fmt.parse(fmt.format(new Date())).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (f < h) {                                   //日期不能小于当前日期
                        Toast.makeText(this, R.string.add_task_hint_deadline_time, Toast.LENGTH_SHORT).show();
                    } else {
                        if (TextUtils.isEmpty(mEditText7.getText().toString())) {     //自定义等级不能为空
                            Toast.makeText(this, R.string.add_task_hint_custom_level, Toast.LENGTH_SHORT).show();
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
                            TaskOrScheduleTypeConverter.setTaskType(mSpinner1, task);

                            //提取任务四象限等级
                            TaskLevelSetter.setTaskLevel(mSpinner2, task);

                            task.setTaskCustomLevel(Integer.parseInt(mEditText7.getText().toString()));
                            task.setDuration(task.getTerminalTimestamp() - task.getBeginTimestamp() + task.getDuration());
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
    }
}
