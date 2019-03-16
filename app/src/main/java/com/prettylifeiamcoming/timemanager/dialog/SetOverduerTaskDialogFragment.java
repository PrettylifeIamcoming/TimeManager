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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SetOverduerTaskDialogFragment extends DialogFragment {
    private EditText editText;
    private Button button1;
    private Button button2;

    private Task task;

    public void setTask(Task task) {
        this.task = task;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_set_overdue_task, container);
        editText = view.findViewById(R.id.dialog_set_overdue_task_deadline);
        button1 = view.findViewById(R.id.dialog_set_overdue_task_ok);
        button2 = view.findViewById(R.id.dialog_set_overdue_task_cancel);

        getDialog().setTitle("Set Overdue Task Deadline");

        editText.setInputType(InputType.TYPE_NULL);
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            // 日期对话框
            if (hasFocus) {
                SetDateDialog.showDatePickerDialog(getContext(), editText);
            }
        });
        editText.setOnClickListener(v -> SetDateDialog.showDatePickerDialog(getContext(), editText));

        button1.setOnClickListener(v -> {
            DateFormat fmt = new SimpleDateFormat("yyyy.MM.dd/kk:mm", Locale.getDefault());

            if (TextUtils.isEmpty(editText.getText().toString())) {
                Toast.makeText(Sundial.getInstance(), R.string.add_task_hint_deadline, Toast.LENGTH_SHORT).show();
            } else {
                long f,h;
                f = 0;
                h = 0;
                try {
                    f = fmt.parse(editText.getText().toString()).getTime();
                    h = fmt.parse(fmt.format(new Date())).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (f < h) {                                   //日期不能小于当前日期
                    Toast.makeText(Sundial.getInstance(), R.string.add_task_hint_deadline_time, Toast.LENGTH_SHORT).show();
                } else {
                    RealmHelper realmHelper = new RealmHelper(Sundial.getInstance());
                    realmHelper.updateTaskDeadline(task.getTaskID(), f);

                    dismiss();
                }
            }
        });

        button2.setOnClickListener(v -> {
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
