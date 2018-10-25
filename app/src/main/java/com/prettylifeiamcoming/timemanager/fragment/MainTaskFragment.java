package com.prettylifeiamcoming.timemanager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.bean.Task;

public class MainTaskFragment extends Fragment {
    private Task mTask;
    private TextView mBeginTime;
    private TextView mTerminalTime;
    private TextView mTaskName;
    private TextView mTaskType;
    private RadioButton mTomatoWatch;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTask = new Task();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_activity_main_task, container, false);

        mBeginTime = (TextView)v.findViewById(R.id.fragment_activity_main_task_begin_time);
        mTerminalTime = (TextView)v.findViewById(R.id.fragment_activity_main_task_terminal_time);
        mTaskName = (TextView)v.findViewById(R.id.fragment_activity_main_task);
        mTaskType = (TextView)v.findViewById(R.id.fragment_activity_main_task_type_name);
        mTomatoWatch = (RadioButton)v.findViewById(R.id.fragment_activity_main_task_tomato_watch_choose);


        return v;
    }
}
