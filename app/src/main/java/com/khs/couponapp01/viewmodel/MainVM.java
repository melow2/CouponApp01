package com.khs.couponapp01.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.first.coupon.model.Coupon;
import com.first.coupon.repository.CouponDataRepository;

import java.util.List;

public class MainVM extends AndroidViewModel implements LifecycleObserver {

    private CouponDataRepository couponDataRepository;
    private MutableLiveData<List<Coupon>> mCoupons;

    public MainVM(@NonNull Application application) {
        super(application);
        this.couponDataRepository = new CouponDataRepository(application);
    }

    public LiveData<List<Coupon>> getAllCoupons(){
        mCoupons = couponDataRepository.getCouponList();
        return mCoupons;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {

    }
}
