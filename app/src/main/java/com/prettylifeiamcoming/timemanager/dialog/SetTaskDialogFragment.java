package com.prettylifeiamcoming.timemanager.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.Sundial;
import com.prettylifeiamcoming.timemanager.activity.AddTaskActivity;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class SetTaskDialogFragment extends DialogFragment {

    private EditText mEditText1;
    private EditText mEditText2;
    private EditText mEditText3;

    private Task task;

    public void setTask(Task task){
        this.task = task;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_set_task, container);

        mEditText1 = view.findViewById(R.id.dialog_set_task_begin);
        mEditText2 = view.findViewById(R.id.dialog_set_task_terminal);
        mEditText3 = view.findViewById(R.id.dialog_set_task_place);

        //任务起始时间设置
        mEditText1.setInputType(InputType.TYPE_NULL);
        mEditText1.setOnFocusChangeListener((v, hasFocus) -> {
            // 日期对话框
            if (hasFocus) {
                showDatePickerDialog(mEditText1);
            }
        });
        mEditText1.setOnClickListener(v -> showDatePickerDialog(mEditText1));

        //任务终止时间设置
        mEditText2.setInputType(InputType.TYPE_NULL);
        mEditText2.setOnFocusChangeListener((v, hasFocus) -> {
            // 日期对话框
            if (hasFocus) {
                showDatePickerDialog(mEditText2);
            }
        });
        mEditText2.setOnClickListener(v -> showDatePickerDialog(mEditText2));

        //设置两个按钮
        Button mButton1 = view.findViewById(R.id.dialog_set_task_ok);
        Button mButton2 = view.findViewById(R.id.dialog_set_task_cancel);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                DateFormat fmt = new SimpleDateFormat("yyyy.MM.dd/kk:mm",Locale.getDefault());
                //判断输入是否有误
                if (TextUtils.isEmpty(mEditText1.getText().toString())) {                 //起始时间不能为空
                    hint(0);
                }else{
                    long f = 0, h = 0;
                    try {
                        f = fmt.parse(mEditText1.getText().toString()).getTime();
                        h = fmt.parse(fmt.format(new Date())).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (f < h) {                                   //起始时间不能小于当前时间
                        hint(1);
                    }else {
                        if (TextUtils.isEmpty(mEditText2.getText().toString())) {                 //终止时间不能为空
                            hint(2);
                        }else {
                            f = 0;
                            h = 0;
                            try {
                                f = fmt.parse(mEditText2.getText().toString()).getTime();
                                h = fmt.parse(fmt.format(new Date())).getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (f < h) {                                   //终止时间不能小于当前时间
                                hint(3);
                            }else {
                                if (f>task.getDeadline()) {
                                    hint(6);
                                }else {

                                    try {
                                        f = fmt.parse(mEditText1.getText().toString()).getTime();
                                        h = fmt.parse(mEditText2.getText().toString()).getTime();
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    if (f>h){
                                        hint(4);
                                    }else {
                                        if (TextUtils.isEmpty(mEditText3.getText().toString())){
                                            hint(5);
                                        }else {
                                            //此处设置task的属性
                                            RealmHelper realmHelper = new RealmHelper(Sundial.getInstance());
                                            try {
                                                date = fmt.parse(mEditText1.getText().toString());
                                                realmHelper.updateTaskBeginTime(task.getTaskID(),date.getTime());
                                                f=date.getTime();
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                date = fmt.parse(mEditText2.getText().toString());
                                                realmHelper.updateTaskTerminalTime(task.getTaskID(),date.getTime());
                                                h=date.getTime();
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            realmHelper.updateTaskDuration(task.getTaskID(),h-f);
                                            realmHelper.updateTaskPlace(task.getTaskID(),mEditText3.getText().toString());

                                            // 关闭对话框
                                            dismiss();
                                        }
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
                // 关闭对话框
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    /**
     * 展示日期选择对话框
     */
    private void showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(getContext(), (view, year, monthOfYear, dayOfMonth) -> {
            final String a = year + "." + (monthOfYear + 1) + "." + dayOfMonth;
            // 时间对话框
            new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                String b = null;
                if (minute < 10) {
                    b = hourOfDay + ":0" + minute;
                } else {
                    b = hourOfDay + ":" + minute;
                }
                String c = a + "/" + b;
                editText.setText(c);
            }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true).show();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //错误提示
    private void hint(int i) {
        switch (i) {
            case 0:
                Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_begin, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_begin_time, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_terminal, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_tetminal_time, Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_begin_terminal, Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_place, Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_terminal_deadline, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
