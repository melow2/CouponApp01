package com.khs.couponapp01.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.first.coupon.BuildConfig;
import com.first.coupon.R;
import com.first.coupon.databinding.ActivitySplashBinding;
import com.first.coupon.viewmodel.SplashVM;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private Observer<Boolean> splashTimeDelayObserver = aBoolean -> {
      if(aBoolean!=null && aBoolean){
          startActivityMain();
      }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        bindView(R.layout.activity_splash);
        SplashVM splashVM = new ViewModelProvider(this).get(SplashVM.class);
        this.getLifecycle().addObserver(splashVM);
        splashVM.getSplashTimeDelay().observe(this,splashTimeDelayObserver);
        mBinding.tvVersion.setText(String.format(getString(R.string.version), BuildConfig.VERSION_NAME));
    }

    private void startActivityMain(){
        MainActivity.start(SplashActivity.this);
        finish();
    }

}