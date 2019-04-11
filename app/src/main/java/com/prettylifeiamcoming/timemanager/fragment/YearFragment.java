package com.prettylifeiamcoming.timemanager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettylifeiamcoming.timemanager.R;

import com.github.prettylife.yearview.CalendarView;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class YearFragment extends Fragment implements CalendarView.OnYearChangeListener {
    private TextView textView;
    private CalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_year,container,false);
        textView = view.findViewById(R.id.year);
        textView.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        calendarView = view.findViewById(R.id.calendarView_year);
        calendarView.showYearSelectLayout(Calendar.getInstance().get(Calendar.YEAR));
        calendarView.setOnYearChangeListener(this);

        return view;
    }

    @Override
    public void onYearChange(int year) {
        textView.setText(String.valueOf(year));
    }
}
