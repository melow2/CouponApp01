package com.khs.couponapp01.repository;

import android.app.Application;

import com.first.coupon.service.CouponNumberInstance;
import com.first.coupon.service.api.CouponNumberAPI;

import retrofit2.Call;

/**
 * @auther hyeoksin
 * @since
 */
public class CouponNumberRepository {
    private CouponNumberAPI service;
    private static final String TAG = CouponDataRepository.class.getSimpleName();
    private String coupon_number;

    public CouponNumberRepository(Application application) {
        this.service = CouponNumberInstance.getInstance();
    }

    public Call<String> getCouponNumberCallback(int time, int siteIdx, int pIdIdx) {
        return service.getCouponNumber(time, siteIdx, pIdIdx);
    }
}
