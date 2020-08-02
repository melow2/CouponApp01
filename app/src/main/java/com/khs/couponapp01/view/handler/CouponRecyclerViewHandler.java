package com.khs.couponapp01.view.handler;

import android.view.View;

import com.first.coupon.model.Coupon;
import com.first.coupon.view.adapter.CouponAdapter;

/**
 * @auther hyeoksin
 * @since
 */

public class CouponRecyclerViewHandler extends ItemTouchHelperCallback {
    private static String TAG = CouponRecyclerViewHandler.class.getSimpleName();
    private static int TIME = 0;
    private static int P_ID_IDX = 735;
    private ButtonEvent mBtnListener;
    private CouponAdapter adapter;

    public CouponRecyclerViewHandler(CouponAdapter couponAdapter) {
        this.adapter = couponAdapter;
    }

    public interface ButtonEvent {
        void download(int time, int siteIdx, int pIdIdx);
        void moveTo(String moveTo);
    }

    public void addButtonEventListener(ButtonEvent listener) {
        this.mBtnListener = listener;
    }

    public void onDownloadBtnClicked(View v, Coupon coupon) {
        mBtnListener.download(TIME,coupon.getSiteIndex(),P_ID_IDX);
    }

    public void onMoveToBtnClicked(View v, Coupon coupon) {
        mBtnListener.moveTo(coupon.getMoveTo());
    }


}
