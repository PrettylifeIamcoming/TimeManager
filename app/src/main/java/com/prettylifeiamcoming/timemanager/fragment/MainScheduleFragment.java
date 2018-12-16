package com.prettylifeiamcoming.timemanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.bean.Schedule;

import androidx.fragment.app.Fragment;

public class MainScheduleFragment extends Fragment {
    private Schedule mSchedule;
    private TextView mBeginTime;
    private TextView mTerminalTime;
    private TextView mScheduleName;
    private TextView mScheduleType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSchedule = new Schedule();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activity_main_schedule, container, false);

        mBeginTime = (TextView) v.findViewById(R.id.fragment_activity_main_schedule_begin_time);
        mTerminalTime = (TextView) v.findViewById(R.id.fragment_activity_main_schedule_terminal_time);
        mScheduleName = (TextView) v.findViewById(R.id.fragment_activity_main_schedule);
        mScheduleType = (TextView) v.findViewById(R.id.fragment_activity_main_schedule_type);

        return v;
    }
}
