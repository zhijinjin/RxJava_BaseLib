package com.zjj.baselibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;

/**
 * Created by zhijinjin (951507056@qq.com)
 * on 2018/3/29.
 */

public abstract class BaseRecyLerViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{

    protected AdapterView.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;
    }

    protected void onItemHolderClick(RecyclerView.ViewHolder itemHolder) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        } else {
            throw new IllegalStateException("Please call setOnItemClickListener method set the click event listeners");
        }
    }

}
