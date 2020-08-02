package com.khs.couponapp01.view.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.first.coupon.BuildConfig;
import com.first.coupon.R;
import com.first.coupon.databinding.ItemCouponBinding;
import com.first.coupon.model.Coupon;
import com.first.coupon.service.CouponNumberInstance;
import com.first.coupon.view.adapter.holder.CouponViewHolder;
import com.first.coupon.view.handler.CouponRecyclerViewHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @auther hyeoksin
 * @since
 */

public class CouponAdapter extends BaseAdapter<Coupon, CouponViewHolder, ItemCouponBinding, CouponRecyclerViewHandler> {

    private static String TAG = CouponAdapter.class.getSimpleName();

    private static DiffUtil.ItemCallback<Coupon> diffCallback = new DiffUtil.ItemCallback<Coupon>() {
        @Override
        public boolean areItemsTheSame(@NonNull Coupon oldItem, @NonNull Coupon newItem) {
            return oldItem.getImageNumber() == oldItem.getImageNumber();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Coupon oldItem, @NonNull Coupon newItem) {
            return oldItem.getCouponNumber().equals(newItem.getCouponNumber());
        }
    };


    public CouponAdapter(Context context, RecyclerView rv) {
        super(context,rv, diffCallback);
    }


    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        bindView(parent, R.layout.item_coupon);
        setHandler(new CouponRecyclerViewHandler(this));
        mBinding.setHandler(mHandler);
        return new CouponViewHolder(mContext, mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {
        Coupon coupon = getItem(holder.getAdapterPosition());
        mBinding.setCoupon(getItem(holder.getAdapterPosition()));
        mHandler.addButtonEventListener(new CouponRecyclerViewHandler.ButtonEvent() {
            @Override
            public void download(int time, int siteIdx, int pIdIdx) {
                CouponNumberInstance.getInstance()
                    .getCouponNumber(time, siteIdx, pIdIdx)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            // ||16433||1||CALL give_snum3('1','0','121.130.82.57', '0','P')
                            String couponNumber = response.body();
                            if (couponNumber != null) {
                                coupon.setCouponNumber(couponNumber);
                                copyOnClipBoard(couponNumber);
                            }

                            if (BuildConfig.DEBUG) {
                                Log.d(TAG, "onResponse()");
                                Log.d(TAG, "response_code: " + response.code());
                                Log.d(TAG, "response_msg: " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            if (BuildConfig.DEBUG) {
                                Log.d(TAG, "onFailure()");
                                Log.d(TAG, "response_msg: " + t.getLocalizedMessage());
                            }
                        }

                        private void copyOnClipBoard(String couponNumber) {
                            ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("couponNumber", couponNumber);
                            if(clipboard!=null) {
                                clipboard.setPrimaryClip(clip);
                                showToast(mContext,mContext.getString(R.string.msg_copy_coupon_success));
                            }else{
                                showToast(mContext,mContext.getString(R.string.msg_copy_coupon_fail));
                            }
                        }
                    });
            }

            @Override
            public void moveTo(String moveTo) {
                Uri uri = Uri.parse(moveTo);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
