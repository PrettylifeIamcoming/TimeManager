package com.prettylifeiamcoming.timemanager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prettylifeiamcoming.timemanager.R;
import com.prettylifeiamcoming.timemanager.Sundial;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.db.RealmHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends BaseAdapter<Task> {

    private RealmHelper mRealmHleper;

    //对应四个象限的颜色设置，红、黄、粉、绿
    private String[] mColors = {"#FF7575","#FFFFB9","#FFC1E0","#BBFFBB"};

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
                //.setProgressBar(R.id.item_task_table_process1,task.getTaskProcess());

        //填充颜色
        switch (task.getTaskLevel()){
            case 1:
                holder.setCardViewBackgroundColor(R.id.item_task_table,Color.parseColor(mColors[0]));
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
                holder.setCardViewBackgroundColor(R.id.item_task_table,Color.parseColor(mColors[1]));
                break;
            case 3:
                holder.setCardViewBackgroundColor(R.id.item_task_table,Color.parseColor(mColors[2]));
                break;
            case 4:
                holder.setCardViewBackgroundColor(R.id.item_task_table,Color.parseColor(mColors[3]));
                break;
        }
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
