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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SetTaskProgressDialogFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener {
    private SeekBar mSeekBar;
    private TextView mTaskProgress;

    private Task task;

    public void setTask(Task task) {
        this.task = task;
    }

    public int getProgress(){
        if (mSeekBar != null){
            return mSeekBar.getProgress();
        } else {
            return -1;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_set_task_progress, container);

        getDialog().setTitle("Set Task Progress");

        mSeekBar = view.findViewById(R.id.dialog_task_progress_seek_bar);
        mTaskProgress = view.findViewById(R.id.dialog_task_progress_data);

        String a = getString(R.string.dialog_set_task_progress_prompt) + task.getTaskProgress() + getString(R.string.dialog_set_task_progress_percent);
        mTaskProgress.setText(a);

        mSeekBar.setProgress(task.getTaskProgress());

        Button button1 = view.findViewById(R.id.dialog_set_task_progress_ok);
        Button button2 = view.findViewById(R.id.dialog_set_task_progress_cancel);

        button1.setOnClickListener(v -> {
            task.setTaskProgress(mSeekBar.getProgress());

            RealmHelper realmHelper = new RealmHelper(getContext());
            realmHelper.updateTaskProgress(task.getTaskID(), mSeekBar.getProgress());
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
        String a = getString(R.string.dialog_set_task_progress_prompt) + progress + getString(R.string.dialog_set_task_progress_percent);
        mTaskProgress.setText(a);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
