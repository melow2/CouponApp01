package com.khs.couponapp01.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.awesome.admanager.AdManager;
import com.awesome.admanager.data.Ad;
import com.awesome.admanager.data.AdName;
import com.awesome.admanager.data.AdType;
import com.first.coupon.R;
import com.first.coupon.databinding.DialogAlertBinding;

import java.util.Locale;

public class CloseDialog extends Dialog {

    public static final String TAG = CloseDialog.class.getSimpleName();

    public ButtonEvent listener;

    public interface ButtonEvent{
        void onPositiveBtn();
        void onNegativeBtn();
    }

    public void addButtonListener(ButtonEvent listener){
        this.listener = listener;
    }

    public CloseDialog(@NonNull Context context) {
        super(context,android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogAlertBinding mBinding = DialogAlertBinding.inflate(LayoutInflater.from(getContext()));
        setContentView(mBinding.getRoot());

        mBinding.tvDialogCommonTitle.setText(
                String.format(Locale.getDefault(),
                        getContext().getString(R.string.msg_exit),
                        getContext().getString(R.string.app_name)));

        AdManager adManager = new AdManager.Builder(getContext())
                .setAdmangerTest(true)
                .setContainer(mBinding.dialogCommonContent)
                .setBaseUrl(getContext().getString(R.string.popup_banner_order_url))
                .setAd(new Ad(AdName.ADMOB, AdType.HALF_BANNER, getContext().getString(R.string.admob_popup_banner_id)))
                .setAd(new Ad(AdName.FACEBOOK, AdType.HALF_BANNER, getContext().getString(R.string.facebook_popup_banner_id)))
                .build();
        adManager.load();

        mBinding.btnFinish.setOnClickListener(v->{ listener.onPositiveBtn();});
        mBinding.btnCancel.setOnClickListener(v->{ listener.onNegativeBtn();});
    }

}
