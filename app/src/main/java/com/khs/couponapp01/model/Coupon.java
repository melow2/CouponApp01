package com.khs.couponapp01.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;
import com.khs.couponapp01.BR;
import com.khs.couponapp01.R;

public class Coupon extends BaseObservable implements Parcelable {

    @SerializedName("imageNumber")
    private int imageNumber = -1;
    @SerializedName("title")
    private String title = null;
    @SerializedName("webServer")
    private String webServer = null;
    @SerializedName("point")
    private String point = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("couponNumber")
    private String couponNumber = null;
    @SerializedName("moveTo")
    private String moveTo = null;
    @SerializedName("siteIndex")
    private int siteIndex = -1;
    private boolean isOpened = false;

    @BindingAdapter(value = {"posterPath", "placeHolder"}, requireAll = false) // 속성이 하나라도 선택될 때.
    public static void loadImage(ImageView imageView, int imageNumber, Drawable placeHolder) {
        Glide.with(imageView.getContext())
                .load(findPosterImage(imageNumber))
                .placeholder(placeHolder)
                .into(imageView);
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWebServer(String webServer) {
        this.webServer = webServer;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
        notifyPropertyChanged(BR.couponNumber);
    }

    public void setMoveTo(String moveTo) {
        this.moveTo = moveTo;
    }

    public void setSiteIndex(int siteIndex) {
        this.siteIndex = siteIndex;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getWebServer() {
        return webServer;
    }

    public String getPoint() {
        return point;
    }

    public String getDescription() {
        return description;
    }

    @Bindable
    public String getCouponNumber() {
        return couponNumber;
    }

    public String getMoveTo() {
        return moveTo;
    }

    public int getSiteIndex() {
        return siteIndex;
    }

    public boolean isOpened() {
        return isOpened;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imageNumber);
        dest.writeString(this.title);
        dest.writeString(this.webServer);
        dest.writeString(this.point);
        dest.writeString(this.description);
        dest.writeString(this.couponNumber);
        dest.writeString(this.moveTo);
        dest.writeInt(this.siteIndex);
        dest.writeByte(this.isOpened ? (byte) 1 : (byte) 0);
    }

    public Coupon() {
    }

    protected Coupon(Parcel in) {
        this.imageNumber = in.readInt();
        this.title = in.readString();
        this.webServer = in.readString();
        this.point = in.readString();
        this.description = in.readString();
        this.couponNumber = in.readString();
        this.moveTo = in.readString();
        this.siteIndex = in.readInt();
        this.isOpened = in.readByte() != 0;
    }

    public static final Creator<Coupon> CREATOR = new Creator<Coupon>() {
        @Override
        public Coupon createFromParcel(Parcel source) {
            return new Coupon(source);
        }

        @Override
        public Coupon[] newArray(int size) {
            return new Coupon[size];
        }
    };

    public static int findPosterImage(int imageNumber) {
        switch (imageNumber) {
            case 0: return R.drawable.poster_00_anytoon;
            case 1: return R.drawable.poster_01_applefile;
            case 2: return R.drawable.poster_02_bigfile;
            case 3: return R.drawable.poster_03_bolleh;
            case 4: return R.drawable.poster_04_bondiskn;
            case 5: return R.drawable.poster_05_bontv;
            case 6: return R.drawable.poster_06_dodofile;
            case 7: return R.drawable.poster_07_filecast;
            case 8: return R.drawable.poster_08_filecity;
            case 9: return R.drawable.poster_09_fileguri;
            case 10: return R.drawable.poster_10_fileham;
            case 11: return R.drawable.poster_11_filehon;
            case 12: return R.drawable.poster_12_fileis;
            case 13: return R.drawable.poster_13_filejo;
            case 14: return R.drawable.poster_14_filekok;
            case 15: return R.drawable.poster_15_filekuki;
            case 16: return R.drawable.poster_16_filelon;
            case 17: return R.drawable.poster_17_filemaru;
            case 18: return R.drawable.poster_18_filenori;
            case 19: return R.drawable.poster_19_filetour;
            case 20: return R.drawable.poster_20_fulltv;
            case 21: return R.drawable.poster_21_kdisk;
            case 22: return R.drawable.poster_22_me2disk;
            case 23: return R.drawable.poster_23_megafile;
            case 24: return R.drawable.poster_24_netfile2;
            case 25: return R.drawable.poster_25_nowtv;
            case 26: return R.drawable.poster_26_ondisk;
            case 27: return R.drawable.poster_27_oradisk;
            case 28: return R.drawable.poster_28_pdpop;
            case 29: return R.drawable.poster_29_qdown;
            case 30: return R.drawable.poster_30_realcomics;
            case 31: return R.drawable.poster_31_sharebox;
            case 32: return R.drawable.poster_32_smartfile;
            case 33: return R.drawable.poster_33_todisk;
            case 34: return R.drawable.poster_34_tomics;
            case 35: return R.drawable.poster_35_tple;
            case 36: return R.drawable.poster_36_wedisk;
            case 37: return R.drawable.poster_37_yesfile;
            case 38: return R.drawable.poster_38_zoomtv;
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "imageNumber=" + imageNumber +
                ", title='" + title + '\'' +
                ", webServer='" + webServer + '\'' +
                ", point='" + point + '\'' +
                ", description='" + description + '\'' +
                ", couponNumber='" + couponNumber + '\'' +
                ", moveTo='" + moveTo + '\'' +
                ", siteIndex=" + siteIndex +
                ", isOpened=" + isOpened +
                '}';
    }
}
