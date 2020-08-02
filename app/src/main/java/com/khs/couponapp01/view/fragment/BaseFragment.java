package com.khs.couponapp01.view.fragment;

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
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

/**
 * @auther hyeoksin
 * @since
 */
public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {
    B mBinding;

    public static void printLog(@NonNull String tag, @NonNull String msg){
        Log.d(tag,msg);
    }

    public static void showSnackBar(@NonNull View v, @StringRes int stringResID){
        Snackbar.make(v,stringResID,Snackbar.LENGTH_LONG).show();
    }

    public static void showToast(@NonNull Context context, @NonNull String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    protected final void bindView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull int layout){
        mBinding = DataBindingUtil.inflate(inflater,layout,container,false);
    }
}
