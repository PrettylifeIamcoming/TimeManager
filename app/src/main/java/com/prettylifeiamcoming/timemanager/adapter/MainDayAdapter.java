package com.prettylifeiamcoming.timemanager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.Sundial;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.bean.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;

import io.realm.RealmObject;

public class MainDayAdapter extends BaseAdapter<RealmObject> {

    private static final int TYPE_TASK = 0;     //任务
    private static final int TYPE_SCHEDULE = 1; //日程
    private Context mContext;
    //对应四个象限的颜色设置，红、黄、粉、绿
    private String[] mColors = {"#FF7575","#FFFFB9","#FFC1E0","#BBFFBB"};
    private List<RealmObject> mDatas;

    public MainDayAdapter(Context mContext, List<RealmObject> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, RealmObject o) {
        if (o instanceof Task){
            initTaskUI(holder, (Task)o);
        }
        if (o instanceof Schedule){
            initScheduleUI(holder, (Schedule)o);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position) instanceof Task)
            return TYPE_TASK;
        if (mDatas.get(position) instanceof Schedule)
            return TYPE_SCHEDULE;

        return -1;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TASK){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_task, parent, false);
            return new BaseViewHolder(view);
        }
        if (viewType == TYPE_SCHEDULE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_schedule_table, parent, false);
            return new BaseViewHolder(view);
        }

        return null;
    }

    //初始化任务界面
    private void initTaskUI(BaseViewHolder holder, Task task){

        holder.setText(R.id.item_main_task_name, task.getTaskName())
                .setText(R.id.item_main_task_begin, new SimpleDateFormat("yyyy.MM.dd/HH:mm",Locale.getDefault()).format(new Date(task.getBeginTimestamp())))
                .setText(R.id.item_main_task_terminal, new SimpleDateFormat("yyyy.MM.dd/HH:mm",Locale.getDefault()).format(new Date(task.getTerminalTimestamp())))
                .setText(R.id.item_main_task_place, task.getmTaskPlace())
                .setText(R.id.item_main_task_process, String.valueOf(task.getTaskProcess() + "%"));

        //填充颜色
        switch (task.getTaskLevel()){
            case 1:
                holder.setCardViewBackgroundColor(R.id.item_main_task,Color.parseColor(mColors[0]));
                break;
            case 2:
                holder.setCardViewBackgroundColor(R.id.item_main_task,Color.parseColor(mColors[1]));
                break;
            case 3:
                holder.setCardViewBackgroundColor(R.id.item_main_task,Color.parseColor(mColors[2]));
                break;
            case 4:
                holder.setCardViewBackgroundColor(R.id.item_main_task,Color.parseColor(mColors[3]));
                break;
        }
    }

    //初始化日程界面
    private void initScheduleUI(BaseViewHolder holder, Schedule schedule){
        holder.setText(R.id.item_schedule_table_name, schedule.getScheduleName())
                .setText(R.id.item_schedule_table_place, schedule.getSchedulePlace())
                .setText(R.id.item_schedule_table_begin, new SimpleDateFormat("yyyy.MM.dd/HH:mm",Locale.getDefault()).format(new Date(schedule.getBeginTimestamp())))
                .setText(R.id.item_schedule_table_terminal, new SimpleDateFormat("yyyy.MM.dd/HH:mm",Locale.getDefault()).format(new Date(schedule.getTerminalTimestamp())))
                .setText(R.id.item_schedule_table_type, getType(schedule.getScheduleType()));

        holder.setCardViewBackgroundColor(R.id.item_schedule_table,Color.parseColor("#ACD6FF"));
    }

    /**
     * 任务类型转换器
     */
    private String getType(int i) {
        switch (i) {
            case 1:
                return Sundial.getInstance().getString(R.string.add_task_study);
            case 2:
                return Sundial.getInstance().getString(R.string.add_task_social);
            case 3:
                return Sundial.getInstance().getString(R.string.add_task_work);
            case 4:
                return Sundial.getInstance().getString(R.string.add_task_play);
            case 5:
                return Sundial.getInstance().getString(R.string.add_task_sleep);
            case 6:
                return Sundial.getInstance().getString(R.string.add_task_others);
        }

        return "类型错误";
    }
}
