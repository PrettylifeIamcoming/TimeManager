package com.prettylifeiamcoming.timemanager.adapter;

import android.content.Context;
import android.graphics.Color;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;
import com.prettylifeiamcoming.timemanager.util.TaskOrScheduleTypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScheduleAdapter extends BaseAdapter<Schedule> {
    private RealmHelper mRealmHelper;

    public ScheduleAdapter(Context mContext, List<Schedule> mData, int mLayoutId) {
        super(mContext, mData, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, final Schedule schedule) {
        holder.setText(R.id.item_schedule_table_name, schedule.getScheduleName())
                .setText(R.id.item_schedule_table_place, schedule.getSchedulePlace())
                .setText(R.id.item_schedule_table_begin, new SimpleDateFormat("yyyy.MM.dd/HH:mm",Locale.getDefault()).format(new Date(schedule.getBeginTimestamp())))
                .setText(R.id.item_schedule_table_terminal, new SimpleDateFormat("yyyy.MM.dd/HH:mm",Locale.getDefault()).format(new Date(schedule.getTerminalTimestamp())))
                .setText(R.id.item_schedule_table_type, TaskOrScheduleTypeConverter.getType(schedule.getScheduleType()));

        holder.setCardViewBackgroundColor(R.id.item_schedule_table,Color.parseColor("#ACD6FF"));
    }
}
