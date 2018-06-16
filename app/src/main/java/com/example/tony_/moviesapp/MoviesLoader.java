package com.example.tony_.moviesapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class MoviesLoader extends AsyncTaskLoader<List<Movie>> {

    private String mUrl;

    public MoviesLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.v("Main", "loader starting");

    }

    @Override
    public List<Movie> loadInBackground() {
        return Utility.fetchMovieData(mUrl);
    }
}
