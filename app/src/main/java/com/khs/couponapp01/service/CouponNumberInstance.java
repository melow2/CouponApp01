package com.khs.couponapp01.service;

import com.first.coupon.service.api.CouponNumberAPI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @auther hyeoksin
 * @since
 */
public class CouponNumberInstance {
    private static Retrofit retrofit = null;
    private static String TAG = CouponDataInstance.class.getSimpleName();
    private static String BASE_URL = "http://www.maincoupon.com/includes/ajax_loads/";

    public static CouponNumberAPI getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(HTMLConverter.FACTORY)
                    .build();
        }
        return retrofit.create(CouponNumberAPI.class);
    }

    static final class HTMLConverter implements Converter<ResponseBody, String> {
        static final Converter.Factory FACTORY = new Converter.Factory() {
            @Override
            public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                if (type == String.class) {
                    return new HTMLConverter();
                }
                return null;
            }
        };

        @Override
        public String convert(ResponseBody responseBody) throws IOException {
            Document document = Jsoup.parse(responseBody.string());
            Element value = document.body();
            String[] params = value.html().split("\\|\\|");
            return params[0];
        }
    }

}
