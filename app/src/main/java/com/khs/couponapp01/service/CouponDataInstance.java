package com.khs.couponapp01.service;

import com.first.coupon.service.api.CouponDataAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CouponDataInstance {
    private static Retrofit retrofit = null;
    private static String TAG = CouponDataInstance.class.getSimpleName();
    private static String BASE_URL="http://trot.mobilelink.xyz/Trot/";
    public static CouponDataAPI getInstance(){
        if(retrofit==null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CouponDataAPI.class);
    }
}
