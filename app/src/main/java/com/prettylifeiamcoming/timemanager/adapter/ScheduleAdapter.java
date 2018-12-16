package com.prettylifeiamcoming.timemanager.adapter;

import android.content.Context;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.Sundial;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScheduleAdapter extends BaseAdapter<Schedule> {
    private RealmHelper mRealmHleper;

    public ScheduleAdapter(Context mContext, List<Schedule> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, final Schedule schedule) {
        holder.setText(R.id.item_schedule_table_name, schedule.getScheduleName())
                .setText(R.id.item_schedule_table_place, schedule.getSchedulePlace())
                .setText(R.id.item_schedule_table_begin, new SimpleDateFormat("yyyy.MM.dd/HH:mm",Locale.getDefault()).format(new Date(schedule.getBeginTimestamp())))
                .setText(R.id.item_schedule_table_terminal, new SimpleDateFormat("yyyy.MM.dd/HH:mm",Locale.getDefault()).format(new Date(schedule.getTerminalTimestamp())))
                .setText(R.id.item_schedule_table_type, getType(schedule.getScheduleType()));

    }

    /**
     * 日程类型转换器
     */
    private String getType(int i) {
        switch (i) {
            case 1:
                return Sundial.getContext().getString(R.string.add_task_study);
            case 2:
                return Sundial.getContext().getString(R.string.add_task_social);
            case 3:
                return Sundial.getContext().getString(R.string.add_task_work);
            case 4:
                return Sundial.getContext().getString(R.string.add_task_play);
            case 5:
                return Sundial.getContext().getString(R.string.add_task_sleep);
            case 6:
                return Sundial.getContext().getString(R.string.add_task_others);
        }

        return "类型错误";
    }
}
