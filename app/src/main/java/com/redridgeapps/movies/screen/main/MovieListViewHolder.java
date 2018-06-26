package com.redridgeapps.movies.screen.main;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.redridgeapps.movies.R;
import com.redridgeapps.movies.databinding.ItemMovieListBinding;
import com.redridgeapps.movies.model.tmdb.Movie;

public class MovieListViewHolder extends RecyclerView.ViewHolder {

    private final ItemMovieListBinding binding;

    private MovieListViewHolder(ItemMovieListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public static MovieListViewHolder create(ViewGroup parent, int itemWidth) {
        ItemMovieListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_movie_list,
                parent,
                false
        );

        // Set item aspect ratio to 3:2
        binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(itemWidth, (int) (itemWidth * 1.5)));

        return new MovieListViewHolder(binding);
    }

    void bindTo(Movie movie) {
        if (movie == null) {
            binding.getRoot().setClickable(false);
            binding.getRoot().setFocusable(false);
        } else {
            binding.getRoot().setClickable(true);
            binding.getRoot().setFocusable(true);
        }

        binding.setMovie(movie);
        binding.executePendingBindings();
    }
}
