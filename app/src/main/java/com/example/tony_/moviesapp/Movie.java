package com.example.tony_.moviesapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Movie implements Parcelable {
    private String mTitle;
    private String mPosterUrl;
    private String mPlot;
    private String mRating;
    private String mReleaseDate;

    public Movie(String mTitle, String mPosterUrl, String mPlot, String mRating, String mReleaseDate) {
        this.mTitle = mTitle;
        this.mPosterUrl = mPosterUrl;
        this.mPlot = mPlot;
        this.mRating = mRating;
        this.mReleaseDate = mReleaseDate;
    }

    protected Movie(Parcel in) {
        mTitle = in.readString();
        mPosterUrl = in.readString();
        mPlot = in.readString();
        mRating = in.readString();
        mReleaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPosterUrl() {
        return mPosterUrl;
    }

    public void setmPosterUrl(String mPosterUrl) {
        this.mPosterUrl = mPosterUrl;
    }

    public String getmPlot() {
        return mPlot;
    }

    public void setmPlot(String mPlot) {
        this.mPlot = mPlot;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mPosterUrl);
        dest.writeString(mPlot);
        dest.writeString(mRating);
        dest.writeString(mReleaseDate);
    }
}
