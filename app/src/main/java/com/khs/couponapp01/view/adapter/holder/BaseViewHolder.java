package com.khs.couponapp01.view.adapter.holder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

abstract class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private Context context;
    B mBinding;
    public BaseViewHolder(@NonNull Context context, @NonNull B itemView){
        super(itemView.getRoot());
        this.context = context;
        this.mBinding = itemView;
    }
}
