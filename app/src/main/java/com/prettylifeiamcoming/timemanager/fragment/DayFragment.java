package com.prettylifeiamcoming.timemanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.Sundial;
import com.prettylifeiamcoming.timemanager.adapter.MainDayAdapter;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.RealmObject;

public class DayFragment extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;
    private MainDayAdapter mainDayAdapter;
    private List<RealmObject> mList = new ArrayList<>();
    private List<Task> mTask = new ArrayList<>();
    private List<Schedule> mSchedule = new ArrayList<>();
    private RealmHelper mRealmHelper;

    public DayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_day, container, false);
        //RecyclerView设置
        mRecyclerView = view.findViewById(R.id.recycler_view_day);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Sundial.getInstance(), DividerItemDecoration.VERTICAL));
        initData();

        return view;
    }

    //从数据库中读取数据
    private void initData() {
        mRealmHelper = new RealmHelper(getActivity());

        mTask = mRealmHelper.queryTodayTask();
        mList.addAll(mTask);
        mSchedule = mRealmHelper.queryTodaySchedule();
        mList.addAll(mSchedule);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.mainDayAdapter = new MainDayAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mainDayAdapter);
    }
}
