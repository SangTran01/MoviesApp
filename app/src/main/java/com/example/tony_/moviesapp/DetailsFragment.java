package com.example.tony_.moviesapp;


import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


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

        TextView titleTextView = view.findViewById(R.id.tv_title);
        TextView dateTextView = view.findViewById(R.id.tv_details_date);
        TextView ratingTextView = view.findViewById(R.id.tv_details_rating);
        TextView plotTextView = view.findViewById(R.id.tv_details_plot);
        ImageView posterImageView = view.findViewById(R.id.iv_details_poster);

        titleTextView.setText(mMovie.getmTitle());
        ratingTextView.setText(mMovie.getmRating());
        dateTextView.setText(mMovie.getmReleaseDate());
        plotTextView.setText(mMovie.getmPlot());

        Picasso.get()
                .load(mMovie.getmPosterUrl())
                .error(R.drawable.user_placeholder_error)
                .into(posterImageView);

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
