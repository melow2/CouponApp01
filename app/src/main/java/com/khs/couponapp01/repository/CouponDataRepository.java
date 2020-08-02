package com.khs.couponapp01.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.first.coupon.BuildConfig;
import com.first.coupon.R;
import com.first.coupon.model.Coupon;
import com.first.coupon.service.CouponDataInstance;
import com.first.coupon.service.api.CouponDataAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CouponDataRepository {

    private String coupon_title;
    private Application application;
    private CouponDataAPI service;
    private MutableLiveData<List<Coupon>> couponList = new MutableLiveData<>();
    private static final String TAG = CouponDataRepository.class.getSimpleName();

    public CouponDataRepository(Application application) {
        this.application = application;
        this.coupon_title = application.getString(R.string.coupon_title);
        this.service = CouponDataInstance.getInstance();
    }

    public MutableLiveData<List<Coupon>> getCouponList() {
        Call<List<Coupon>> callback = service.getCouponData();
        callback.enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {

                if (response.body() != null) {
                    List<Coupon> coupons = new ArrayList<>();
                    for (Coupon coupon : response.body()) {
                        if (coupon.getTitle().equals(coupon_title)) {
                            coupons.add(0, coupon);
                        } else {
                            coupons.add(coupon);
                        }
                    }
                    couponList.setValue(coupons);
                }

                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onResponse()");
                    Log.d(TAG, "response_code: " + response.code());
                    Log.d(TAG, "response_msg: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onFailure()");
                    Log.d(TAG, "response_msg: " + t.getLocalizedMessage());
                }
            }
        });
        return couponList;
    }
}
