package com.example.tony_.moviesapp;


import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private Movie mMovie;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.v("Main","fragment details created");
        if (!getArguments().isEmpty() && getArguments().containsKey("Movie")) {
            mMovie = getArguments().getParcelable("Movie");
        }

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView titleTextView = view.findViewById(R.id.tv_details_name);
        TextView ratingTextView = view.findViewById(R.id.tv_details_rating);

        titleTextView.setText(mMovie.getmTitle());
        ratingTextView.setText(mMovie.getmRating());

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("Main","fragment details paused");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("Main","fragment details Destroyed");
    }
}
