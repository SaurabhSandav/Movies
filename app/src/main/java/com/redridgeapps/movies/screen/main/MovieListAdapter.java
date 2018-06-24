package com.redridgeapps.movies.screen.main;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.util.function.Consumer;

public class MovieListAdapter extends PagedListAdapter<Movie, MovieListViewHolder> {

    private Consumer<Movie> clickListener;
    private int itemWidth;

    MovieListAdapter(int itemWidth, Consumer<Movie> clickListener) {
        super(Movie.DIFF_CALLBACK);
        this.itemWidth = itemWidth;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListViewHolder viewHolder = MovieListViewHolder.create(parent, itemWidth);

        viewHolder.itemView.setOnClickListener(view -> {
            if (viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                clickListener.accept(getItem(viewHolder.getAdapterPosition()));
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }
}
