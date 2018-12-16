package com.prettylifeiamcoming.timemanager.adapter;

import android.content.Context;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.Sundial;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends BaseAdapter<Task> {

    private RealmHelper mRealmHleper;

    public TaskAdapter(Context mContext, List<Task> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, final Task task) {
        holder.setText(R.id.item_task_table_name, task.getTaskName())
                .setText(R.id.item_task_table_deadline, new SimpleDateFormat("yyyy.MM.dd/HH:mm",Locale.getDefault()).format(new Date(task.getDeadline())))
                .setText(R.id.item_task_table_type, getType(task.getTaskType()))
                .setText(R.id.item_task_table_process, String.valueOf(task.getTaskProcess() + "%"))
                .setText(R.id.item_task_table_custom_level, String.valueOf(task.getTaskCustomLevel()));

        //决定背景颜色
//        switch (task.getTaskLevel()){
//            case 1:
//                holder.setBackgroundColor(R.layout.item_task_table,0xFFFF0000);
//                break;
//            case 2:
//                holder.setBackgroundColor(R.layout.item_task_table,0xFFFFFF00);
//                break;
//            case 3:
//                holder.setBackgroundColor(R.layout.item_task_table,0xFFFFF000);
//                break;
//            case 4:
//                holder.setBackgroundColor(R.layout.item_task_table,0xFFFFFFF0);
//                break;
//        }
    }

    /**
     * 任务类型转换器
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
