package com.prettylifeiamcoming.timemanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.bean.Task;

import androidx.fragment.app.Fragment;

public class TaskTableFragment extends Fragment {
    private Task mTask;
    private TextView mTaskName;
    private TextView mDeadline;
    private TextView mTaskType;
    private TextView mTaskProcess;
    private TextView mCustomLevel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_table, container, false);

        mTaskName = (TextView)v.findViewById(R.id.fragment_task_table_name);
        mDeadline = (TextView)v.findViewById(R.id.fragment_task_table_deadline);
        mTaskType = (TextView)v.findViewById(R.id.fragment_task_table_type);
        mTaskProcess = (TextView)v.findViewById(R.id.fragment_task_table_process);
        mCustomLevel = (TextView)v.findViewById(R.id.fragment_task_table_custom_level);

        return v;
    }
}
