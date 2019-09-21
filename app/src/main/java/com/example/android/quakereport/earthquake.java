package com.example.android.quakereport;

public class earthquake {
    public String mMag;
    public String mLocation;
    public long mDate;

    public earthquake(String mMag, String mLocation, long mDate) {
        this.mMag = mMag;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    public String getmMag() {
        return mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmDate() {
        return mDate;
    }
}
