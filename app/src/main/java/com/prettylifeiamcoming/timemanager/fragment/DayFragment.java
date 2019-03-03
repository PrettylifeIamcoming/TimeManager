package com.prettylifeiamcoming.timemanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.Sundial;
import com.prettylifeiamcoming.timemanager.adapter.MainDayAdapter;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;
import com.prettylifeiamcoming.timemanager.dialog.SetTaskProgressDialogFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.RealmObject;

public class DayFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MainDayAdapter mainDayAdapter;
    private final List<RealmObject> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        //RecyclerView设置
        mRecyclerView = view.findViewById(R.id.recycler_view_day);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Sundial.getInstance(), DividerItemDecoration.VERTICAL));
        initData();
        addListener();

        return view;
    }

    private void addListener() {
        mainDayAdapter.setOnItemClickListener((view, position) -> {
            SetTaskProgressDialogFragment setTaskProgressDialogFragment = new SetTaskProgressDialogFragment();
            if (mList.get(position) instanceof Task) {
                setTaskProgressDialogFragment.setTask((Task) mList.get(position));
            }
            setTaskProgressDialogFragment.show(getFragmentManager(), "SetProgressDialog");
        });
    }

    //从数据库中读取数据
    private void initData() {
        mList.clear();
        RealmHelper mRealmHelper = new RealmHelper(getActivity());

        List<Task> mTask = mRealmHelper.queryTodayTask();
        mList.addAll(mTask);
        List<Schedule> mSchedule = mRealmHelper.queryTodaySchedule();
        mList.addAll(mSchedule);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.mainDayAdapter = new MainDayAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mainDayAdapter);
    }
}
