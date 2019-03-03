package com.prettylifeiamcoming.timemanager.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SetTaskProgressDialogFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener {
    private SeekBar mSeekBar;
    private TextView mTaskProgress;

    private Task task;

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_set_task_progress, container);

        getDialog().setTitle("Set Task Progress");

        mSeekBar = view.findViewById(R.id.dialog_task_progress_seek_bar);
        mTaskProgress = view.findViewById(R.id.dialog_task_progress_data);

        Button button1 = view.findViewById(R.id.dialog_set_task_progress_ok);
        Button button2 = view.findViewById(R.id.dialog_set_task_progress_cancel);

        button1.setOnClickListener(v -> {
            dismiss();
        });

        button2.setOnClickListener(v -> {
            // 关闭对话框
            dismiss();
        });

        mSeekBar.setOnSeekBarChangeListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mTaskProgress.setText("当前任务进度：" + progress + "%");
        task.setTaskProgress(progress);

        RealmHelper realmHelper = new RealmHelper(getContext());
        realmHelper.updateTaskProgress(task.getTaskID(), progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
