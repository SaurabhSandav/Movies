package com.redridgeapps.movies.screen.main;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.redridgeapps.movies.model.tmdb.Movie;

public class MovieListAdapter extends PagedListAdapter<Movie, MovieListViewHolder> {

    private int itemWidth;

    MovieListAdapter(int itemWidth) {
        super(Movie.DIFF_CALLBACK);
        this.itemWidth = itemWidth;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MovieListViewHolder.create(parent, itemWidth);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }
}
