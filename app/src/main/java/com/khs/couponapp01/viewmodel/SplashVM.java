package com.khs.couponapp01.viewmodel;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.first.coupon.view.activity.SplashActivity;

public class SplashVM extends AndroidViewModel implements LifecycleObserver {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final int SPLASH_TIME_DELAY_IN_MS = 3000;
    private MutableLiveData<Boolean> splashTimeDelay = new MutableLiveData<>();
    private Handler handler;
    private Runnable splashTimeOutRunnable = () -> splashTimeDelay.postValue(true); // functional interface.

    public SplashVM(@NonNull Application application) {
        super(application);
        handler = new Handler();
    }

    @NonNull
    public MutableLiveData<Boolean> getSplashTimeDelay() {
        return splashTimeDelay;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onCreate() {
        handler.postDelayed(splashTimeOutRunnable, SPLASH_TIME_DELAY_IN_MS);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        handler.removeCallbacks(splashTimeOutRunnable);
    }
}
