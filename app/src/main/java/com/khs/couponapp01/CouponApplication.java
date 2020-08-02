package com.khs.couponapp01;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

/**
 * @auther hyeoksin
 * @since
 */

public class CouponApplication extends MultiDexApplication {
    static final String TAG = CouponApplication.class.getSimpleName();
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate()");
    }
}
