package com.example.testproject.viewmodel;

public class WeatherItem {
    //    private final String mImageUrl;
    private final String mCity;
    private final String mCond;
    private final String mTime;
    private final String mPrecip;
    private final String mTemp;
    private final String mHumdt;
    private final String mFeel;
//    private final String mSunrise;
//    private final String mSunset;

    public WeatherItem(String City, String Time, String Cond, String Precip, String Temp,
                       String Humdt, String Feel, String Sunset, String Sunrise) {

        mCity = City;
        mTime = Time;
        mCond = Cond;
        mPrecip = Precip;
        mTemp = Temp;
        mHumdt = Humdt;
        mFeel = Feel;
//        mSunset = Sunset;
//        mSunrise = Sunrise;

    }

//    public String getmImageUrl() {
//        return mImageUrl;
//    }

    public String getmCity() {
        return mCity;
    }

    public String getmCond() {
        return mCond;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmPrecip() {
        return mPrecip;
    }

    public String getmTemp() {
        return mTemp;
    }

    public String getmHumdt() {
        return mHumdt;
    }

    public String getmFeel() {
        return mFeel;
    }
//    public String getmSunset() {
//        return mSunset;
//    }

//    public String getmSunrise() {
//        return mSunrise;
//    }
}

