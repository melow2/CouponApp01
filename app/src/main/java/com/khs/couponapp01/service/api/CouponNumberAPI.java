package com.khs.couponapp01.service.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @auther hyeoksin
 * @since
 */
public interface CouponNumberAPI {
    @FormUrlEncoded         // @Field parameters can only be used with form encoding.
    @POST("get_snum.php")
    Call<String> getCouponNumber(@Field("time") int time, @Field("site_idx") int siteIdx, @Field("p_id_idx") int pIdIdx);
}
