package com.prettylifeiamcoming.timemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private Context mContext;
    private List<T> mData;
    private int mLayoutId;
    private OnItemClickListener mItemClickListener;
    private onLongItemClickListener mLongItemClickListener;

    public BaseAdapter(Context mContext, List<T> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public BaseAdapter(Context mContext, List<T> mData, int mLayoutId) {
        this.mContext = mContext;
        this.mData = mData;
        this.mLayoutId = mLayoutId;
    }

    public void updateData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addAll(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        convert(mContext, holder, mData.get(position));
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
        void onLongItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setonLongItemClickListener(onLongItemClickListener listener) {
        this.mLongItemClickListener = listener;
    }
}
