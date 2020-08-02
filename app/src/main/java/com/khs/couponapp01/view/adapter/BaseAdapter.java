package com.khs.couponapp01.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.first.coupon.view.handler.CouponRecyclerViewHandler;
import com.google.android.material.snackbar.Snackbar;

/**
 * @auther hyeoksin
 * @since
 */
abstract class BaseAdapter<M, H extends RecyclerView.ViewHolder,B extends ViewDataBinding,D> extends ListAdapter<M,H> {

    Context mContext;
    RecyclerView mRv;
    B mBinding;
    D mHandler;

    protected BaseAdapter(Context context, RecyclerView rv,@NonNull DiffUtil.ItemCallback<M> diffCallback) {
        super(diffCallback);
        this.mRv = rv;
        this.mContext =context;
    }

    public static void printLog(@NonNull String tag, @NonNull String msg){
        Log.d(tag,msg);
    }

    public static void showSnackBar(@NonNull View v, @StringRes int stringResID){
        Snackbar.make(v,stringResID,Snackbar.LENGTH_LONG).show();
    }

    public static void showToast(@NonNull Context context, @NonNull String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    protected final void bindView(ViewGroup parent,int layout){
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                layout, parent,false);
    }

    protected final void setHandler(CouponRecyclerViewHandler handler){
        this.mHandler = (D) handler;
    }
}
