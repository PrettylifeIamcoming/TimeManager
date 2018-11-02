package com.prettylifeiamcoming.timemanager.adapter;

import android.content.Context;

import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.util.List;

public class TaskAdapter extends BaseAdapter<Task>{

    private RealmHelper mRealmHleper;

    public TaskAdapter(Context mContext, List<Task> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
        mRealmHleper = new RealmHelper(mContext);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, final Task task) {

    }

}
