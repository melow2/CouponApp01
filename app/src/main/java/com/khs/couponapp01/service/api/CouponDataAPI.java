package com.khs.couponapp01.service.api;

import com.first.coupon.model.Coupon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CouponDataAPI {
    @GET("coupon.json")
    Call<List<Coupon>> getCouponData();
}
