package com.prettylifeiamcoming.timemanager.adapter;

import android.content.Context;
import android.graphics.Color;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.util.TaskOrScheduleTypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CompletedTaskAdapter extends BaseAdapter<Task> {
    //对应四个象限的颜色设置，红、黄、粉、绿
    private String[] mColors = {"#FF7575", "#FFFFB9", "#FFC1E0", "#BBFFBB"};

    public CompletedTaskAdapter(Context mContext, List<Task> mData, int mLayoutId) {
        super(mContext, mData, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, final Task task) {
        holder.setText(R.id.item_completed_task_name, task.getTaskName())
                .setText(R.id.item_completed_task_terminal, new SimpleDateFormat("yyyy.MM.dd/HH:mm", Locale.getDefault()).format(new Date(task.getTerminalTimestamp())))
                .setText(R.id.item_completed_task_type, TaskOrScheduleTypeConverter.getType(task.getTaskType()))
                .setText(R.id.item_completed_task_process, String.valueOf(task.getTaskProgress() + "%"))
                .setText(R.id.item_completed_task_custom_level, String.valueOf(task.getTaskCustomLevel()));
        //.setProgressBar(R.id.item_task_table_process1,task.getTaskProcess());

        //填充颜色
        switch (task.getTaskLevel()) {
            case 1:
                holder.setCardViewBackgroundColor(R.id.item_completed_task, Color.parseColor(mColors[0]));
//                holder.setTextColor(R.id.item_task_table_name,Color.parseColor("#FFFFFF"));
//                holder.setTextColor(R.id.item_task_table_deadline,Color.parseColor("#FFFFFF"));
//                holder.setTextColor(R.id.item_task_table_type,Color.parseColor("#FFFFFF"));
//                holder.setTextColor(R.id.item_task_table_process,Color.parseColor("#FFFFFF"));
//                holder.setTextColor(R.id.item_task_table_custom_level,Color.parseColor("#FFFFFF"));
//                holder.setTextColor(R.id.textView003,Color.parseColor("#FFFFFF"));
//                holder.setTextColor(R.id.textView008,Color.parseColor("#FFFFFF"));
//                holder.setTextColor(R.id.textView0010,Color.parseColor("#FFFFFF"));
//                holder.setTextColor(R.id.textView0011,Color.parseColor("#FFFFFF"));
                break;
            case 2:
                holder.setCardViewBackgroundColor(R.id.item_completed_task, Color.parseColor(mColors[1]));
                break;
            case 3:
                holder.setCardViewBackgroundColor(R.id.item_completed_task, Color.parseColor(mColors[2]));
                break;
            case 4:
                holder.setCardViewBackgroundColor(R.id.item_completed_task, Color.parseColor(mColors[3]));
                break;
        }
    }
}
