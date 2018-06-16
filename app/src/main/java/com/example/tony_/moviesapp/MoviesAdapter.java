package com.example.tony_.moviesapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private List<Movie> mMovies;
    private MoviesAdapterOnClickListener mMoviesAdapterOnClickListener;

    public interface MoviesAdapterOnClickListener {
        public void onClick(Movie movie);
    }

    public MoviesAdapter(List<Movie> movies, MoviesAdapterOnClickListener moviesAdapterOnClickListener) {
        mMovies = movies;
        mMoviesAdapterOnClickListener = moviesAdapterOnClickListener;
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_item, parent, false);
        MoviesAdapterViewHolder holder = new MoviesAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {
        holder.Bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) {
            return  0;
        }
        return mMovies.size();
    }

    public class MoviesAdapterViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mPosterImageView;

        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            mPosterImageView = itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);
        }

        public void Bind(Movie movie) {
            Picasso.get()
                    .load(movie.getmPosterUrl())
                    .error(R.drawable.user_placeholder_error)
                    .into(mPosterImageView);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = mMovies.get(position);
            mMoviesAdapterOnClickListener.onClick(movie);
        }
    }
}
