package com.prettylifeiamcoming.timemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;
    private OnItemClickListener mItemClickListener;
    private onLongItemClickListener mLongItemClickListener;

    public BaseAdapter(Context mContext, List<T> mDatas){
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    public BaseAdapter(Context mContext, List<T> mDatas, int mLayoutId) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mLayoutId = mLayoutId;
    }

    public void updateData(List<T> data) {
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void addAll(List<T> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        convert(mContext, holder, mDatas.get(position));
        if (mItemClickListener != null) {
            holder.mItemView.setOnClickListener(v -> mItemClickListener.onItemClick(v, position));
        }
        if (mLongItemClickListener != null) {
            holder.itemView.setOnLongClickListener(v -> {
                mLongItemClickListener.onLongItemClick(v, position);
                return true;
            });
        }
    }

    protected abstract void convert(Context mContext, BaseViewHolder holder, T t);


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface onLongItemClickListener {
        void onLongItemClick(View view, int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setonLongItemClickListener(onLongItemClickListener listener) {
        this.mLongItemClickListener = listener;
    }
}
