package com.prettylifeiamcoming.timemanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prettylifeiamcoming.timemanager.R;

import androidx.fragment.app.Fragment;

public class DayFragment extends Fragment {

    public DayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day, container, false);
    }
}
