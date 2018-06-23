package com.redridgeapps.movies.screen.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.redridgeapps.movies.R;
import com.redridgeapps.movies.databinding.ItemMovieListBinding;
import com.redridgeapps.movies.model.tmdb.Movie;

public class MovieListAdapter extends ListAdapter<Movie, MovieListAdapter.ViewHolder> {

    private int itemWidth;

    MovieListAdapter(int itemWidth) {
        super(Movie.DIFF_CALLBACK);
        this.itemWidth = itemWidth;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_movie_list,
                parent,
                false
        );

        // Set item aspect ratio to 16:9
        binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(itemWidth, (int) (itemWidth * 1.5)));

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemMovieListBinding binding;

        ViewHolder(ItemMovieListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(Movie movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }
    }
}
