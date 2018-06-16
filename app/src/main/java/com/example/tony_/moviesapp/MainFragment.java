package com.example.tony_.moviesapp;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements MoviesAdapter.MoviesAdapterOnClickListener, LoaderManager.LoaderCallbacks<List<Movie>> {

    private RecyclerView mRecyclerView;
    private ArrayList<Movie> mMovies;

    private String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private String PATH_POPULAR = "popular";
    private String API_KEY = "093a106eeee012b0db5cc9b16e64950f";
    private boolean mHasPopularChecked = true;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("Main", "fragment create");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());

        mRecyclerView = view.findViewById(R.id.rv_flavor);
        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),
                mNoOfColumns);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        int spacing = 0; // 50px
        boolean includeEdge = false;
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(mNoOfColumns, spacing, includeEdge));


        //check for screen rotate
        if (savedInstanceState != null && savedInstanceState.containsKey("movies")) {
            Log.v("Main", "fragment get movies from save state");
            mMovies = savedInstanceState.getParcelableArrayList("movies");
        }

        //check mMovies between fragments
        if (mMovies == null) {
            Log.v("Main", "fragment movies null query first time");
            getLoaderManager().initLoader(0,null, this).forceLoad();

        } else {
            Log.v("Main", "fragment using movies data");
            MoviesAdapter adapter = new MoviesAdapter(mMovies, this);
            mRecyclerView.setAdapter(adapter);
        }

        return view;
    }


    @Override
    public void onClick(Movie movie) {
        DetailsFragment fragment = new DetailsFragment();

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putParcelable("Movie", movie);
        fragment.setArguments(bundle);
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v("Main", "fragment state saved");
        if (mRecyclerView != null) {
            outState.putParcelable("Data", mRecyclerView.getLayoutManager().onSaveInstanceState());
        }
        if (mMovies != null) {
            outState.putParcelableArrayList("movies", mMovies);
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.v("Main", "fragment finish state restored");
        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable("Data");
            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("Main","Main Fragment Activty has been created");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("Main", "fragment paused");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("Main","fragment main Destroyed");
    }

    public String buildURI() {
        if (mHasPopularChecked) {
            Uri baseUri = Uri.parse(BASE_URL);
            Uri.Builder builder = baseUri.buildUpon();
            builder.appendPath(PATH_POPULAR);
            builder.appendQueryParameter("api_key", API_KEY);

            String completedUri = builder.toString();
            return completedUri;
        }
        return "";
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        Log.v("Main", "loader created");
        return new MoviesLoader(getActivity(), buildURI());
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        Log.v("Main", "loader finished");
        mMovies = new ArrayList<>(data);
        MoviesAdapter adapter = new MoviesAdapter(mMovies, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        Log.v("Main", "loader reset");
        //mMovies.clear();
    }
}
