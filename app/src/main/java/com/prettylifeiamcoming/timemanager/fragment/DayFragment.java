package com.prettylifeiamcoming.timemanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.Sundial;
import com.prettylifeiamcoming.timemanager.activity.MainActivity;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.realm.RealmObject;

public class DayFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainDayAdapter mainDayAdapter;
    private List<RealmObject> mList = new ArrayList<>();
    private RealmHelper mRealmHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        //RecyclerView设置
        mRecyclerView = view.findViewById(R.id.recycler_view_day);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Sundial.getInstance(), DividerItemDecoration.VERTICAL));

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_day);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainDayAdapter.updateData(mRealmHelper.queryToday());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        initData();

        addListener();

        return view;
    }

    @Override
    public void onResume() {
        mainDayAdapter.updateData(mRealmHelper.queryToday());
        super.onResume();
    }

    private void addListener() {
        mainDayAdapter.setOnItemClickListener((view, position) -> {
            if (mList.get(position) instanceof Task) {
                SetTaskProgressDialogFragment setTaskProgressDialogFragment = new SetTaskProgressDialogFragment();
                setTaskProgressDialogFragment.setTask((Task) mList.get(position));
                setTaskProgressDialogFragment.show(getFragmentManager(), "SetProgressDialog");
            } else {

            }
        });
    }

    //从数据库中读取数据
    private void initData() {
//        mList.clear();
        mRealmHelper = new RealmHelper(getActivity());

        mList = mRealmHelper.queryToday();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.mainDayAdapter = new MainDayAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mainDayAdapter);
    }
}
