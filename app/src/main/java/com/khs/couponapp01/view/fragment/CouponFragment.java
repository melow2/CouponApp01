package com.khs.couponapp01.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.first.coupon.R;
import com.first.coupon.databinding.FragmentCouponBinding;
import com.first.coupon.model.Coupon;
import com.first.coupon.view.adapter.CouponAdapter;
import com.first.coupon.viewmodel.MainVM;

import java.util.List;

/**
 * @auther hyeoksin
 * @since
 */
public class CouponFragment extends BaseFragment<FragmentCouponBinding> {

    private static String TAG = CouponFragment.class.getSimpleName();
    private MainVM mainVM;
    private CouponAdapter adapter;

    private Observer<List<Coupon>> getCouponsObserver = coupons -> {
        if(coupons!=null){
            adapter.submitList(coupons);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater, container, R.layout.fragment_coupon);
        mainVM = new ViewModelProvider(this).get(MainVM.class);
        adapter = new CouponAdapter(getContext(),mBinding.rcvCoupon); // ?
        mBinding.rcvCoupon.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mBinding.rcvCoupon.setAdapter(adapter);
        this.getLifecycle().addObserver(mainVM);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainVM.getAllCoupons().observe(getViewLifecycleOwner(),getCouponsObserver);
    }
}
