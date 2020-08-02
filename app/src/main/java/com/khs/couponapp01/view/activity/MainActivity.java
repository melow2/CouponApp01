package com.khs.couponapp01.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.awesome.admanager.AdManager;
import com.awesome.admanager.OnInterstitialAdLoadListener;
import com.awesome.admanager.data.Ad;
import com.awesome.admanager.data.AdName;
import com.awesome.admanager.data.AdType;
import com.first.coupon.BuildConfig;
import com.first.coupon.R;
import com.first.coupon.databinding.ActivityMainBinding;
import com.first.coupon.view.dialog.CloseDialog;
import com.first.coupon.view.fragment.CouponFragment;

import java.util.Locale;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static final String TAG = MainActivity.class.getSimpleName();
    private AdManager interstitial, banner;
    private CloseDialog closeDialog;

    @Override
    protected void onStart() {
        super.onStart();
        showInterstitialAd();
        showBottomBannerAd();
        setPopupAd();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        if (savedInstanceState == null) {
            callCouponFragment();
        }
    }

    private void callCouponFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_coupon, new CouponFragment())
                .commitNow();
    }

    private void init() {
        bindView(R.layout.activity_main);
        setToolbar((Toolbar) mBinding.toolbar, false, getString(getApplicationInfo().labelRes), findViewById(R.id.tv_title));
    }

    private void setPopupAd() {
        closeDialog = new CloseDialog(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            closeDialog.create();
        }
        closeDialog.addButtonListener(new CloseDialog.ButtonEvent() {
            @Override
            public void onPositiveBtn() {
                closeDialog.dismiss();
                finish();
            }

            @Override
            public void onNegativeBtn() {
                closeDialog.dismiss();
            }
        });
    }

    private void showInterstitialAd() {
        interstitial = new AdManager.Builder(this)
                .setAdmangerTest(true)
                .setBaseUrl(getString(R.string.interstitial_order_url))
                .setAd(new Ad(AdName.ADMOB, AdType.INTERSTITIAL, getString(R.string.admob_interstitial_id)))
                .setAd(new Ad(AdName.CAULY, AdType.INTERSTITIAL, getString(R.string.cauly_media_code)))
                .setAd(new Ad(AdName.MANPLUS, AdType.INTERSTITIAL, getString(R.string.mezzo_media_publisher_code) + ',' + getString(R.string.mezzo_media_media_code) + ',' + getString(R.string.mezzo_media_interstitial_section_code)))
                .setAd(new Ad(AdName.FACEBOOK, AdType.INTERSTITIAL, getString(R.string.facebook_interstitial_id)))
                .setOnInterstitialAdLoadListener(new OnInterstitialAdLoadListener() {
                    @Override
                    public void onAdLoaded() {
                        interstitial.showInterstitial();
                    }

                    @Override
                    public void onAdFailedToLoad() {
                    }
                })
                .build();
        interstitial.load();
    }

    private void showBottomBannerAd() {
        banner = new AdManager.Builder(this)
                .setAdmangerTest(true)
                .setBaseUrl(getString(R.string.bottom_banner_order_url))
                .setContainer(mBinding.lytBanner)
                .setAd(new Ad(AdName.ADMOB, AdType.BANNER, getString(R.string.admob_bottom_banner_id)))
                .setAd(new Ad(AdName.CAULY, AdType.BANNER, getString(R.string.cauly_media_code)))
                .setAd(new Ad(AdName.MANPLUS, AdType.BANNER, getString(R.string.mezzo_media_publisher_code) + ',' + getString(R.string.mezzo_media_media_code) + ',' + getString(R.string.mezzo_media_interstitial_section_code)))
                .setAd(new Ad(AdName.FACEBOOK, AdType.BANNER, getString(R.string.facebook_bottom_banner_id)))
                .build();
        banner.load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                String title = String.format(Locale.getDefault(), getString(R.string.msg_share_message), getString(getApplicationInfo().labelRes)); // app_name
                String content = String.format(Locale.getDefault(), getString(R.string.share_store_url), getPackageName());                         // com.flower

                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onOptionsItemSelected()");
                    Log.d(TAG, "title: " + title);
                    Log.d(TAG, "content: " + content);
                }
                Intent intent = new Intent(Intent.ACTION_SEND) // 공유하기.
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_SUBJECT, getString(getApplicationInfo().labelRes))
                        .putExtra(Intent.EXTRA_TEXT, title + "\n" + content);
                startActivity(Intent.createChooser(intent, getString(R.string.share_app)));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(@NonNull Context context) {
        if (context instanceof AppCompatActivity) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            startActivityAnimation(context);
        }
    }

    @Override
    public void onBackPressed() {
        closeDialog.show();
    }
}