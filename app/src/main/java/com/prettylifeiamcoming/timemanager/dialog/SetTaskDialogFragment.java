package com.prettylifeiamcoming.timemanager.dialog;

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
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SetTaskDialogFragment extends DialogFragment {

    private EditText mEditText1;
    private EditText mEditText2;
    private EditText mEditText3;

    private Task task;

    public void setTask(Task task){
        this.task = task;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
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
                SetDateDialog.showDatePickerDialog(getContext(), mEditText1);
            }
        });
        mEditText1.setOnClickListener(v -> SetDateDialog.showDatePickerDialog(getContext(), mEditText1));

        //任务终止时间设置
        mEditText2.setInputType(InputType.TYPE_NULL);
        mEditText2.setOnFocusChangeListener((v, hasFocus) -> {
            // 日期对话框
            if (hasFocus) {
                SetDateDialog.showDatePickerDialog(getContext(), mEditText2);
            }
        });
        mEditText2.setOnClickListener(v -> SetDateDialog.showDatePickerDialog(getContext(), mEditText2));

        //设置两个按钮
        Button mButton1 = view.findViewById(R.id.dialog_set_task_ok);
        Button mButton2 = view.findViewById(R.id.dialog_set_task_cancel);

        mButton1.setOnClickListener(v -> {
            Date date = new Date();
            DateFormat fmt = new SimpleDateFormat("yyyy.MM.dd/kk:mm",Locale.getDefault());
            //判断输入是否有误
            if (TextUtils.isEmpty(mEditText1.getText().toString())) {                 //起始时间不能为空
                Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_begin, Toast.LENGTH_SHORT).show();
            }else{
                long f = 0, h = 0;
                try {
                    f = fmt.parse(mEditText1.getText().toString()).getTime();
                    h = fmt.parse(fmt.format(new Date())).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (f < h) {                                   //起始时间不能小于当前时间
                    Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_begin_time, Toast.LENGTH_SHORT).show();
                }else {
                    if (TextUtils.isEmpty(mEditText2.getText().toString())) {                 //终止时间不能为空
                        Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_terminal, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_terminal_time, Toast.LENGTH_SHORT).show();
                        }else {
                            if (f>task.getDeadline()) {
                                Toast.makeText(Sundial.getInstance(), getString(R.string.dialog_set_task_hint_terminal_deadline) + String.valueOf(fmt.format(task.getDeadline())), Toast.LENGTH_LONG).show();
                            }else {

                                try {
                                    f = fmt.parse(mEditText1.getText().toString()).getTime();
                                    h = fmt.parse(mEditText2.getText().toString()).getTime();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (f>h){
                                    Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_begin_terminal, Toast.LENGTH_SHORT).show();
                                }else {
                                    if (TextUtils.isEmpty(mEditText3.getText().toString())){
                                        Toast.makeText(Sundial.getInstance(), R.string.dialog_set_task_hint_place, Toast.LENGTH_SHORT).show();
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
        });

        mButton2.setOnClickListener(v -> {
            // 关闭对话框
            dismiss();
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }
}
