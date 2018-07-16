package com.redridgeapps.movies.screen.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.redridgeapps.movies.R;
import com.redridgeapps.movies.databinding.ItemVideoListBinding;
import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.model.tmdb.VideoItem;
import com.redridgeapps.movies.util.GlideApp;
import com.redridgeapps.movies.util.function.Consumer;

public class VideoAdapter extends ListAdapter<VideoItem, VideoAdapter.ViewHolder> {

    private Consumer<VideoItem> clickListener;

    VideoAdapter(Consumer<VideoItem> clickListener) {
        super(VideoItem.DIFF_CALLBACK);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_video_list,
                parent,
                false
        );

        ViewHolder viewHolder = new ViewHolder(binding);

        binding.getRoot().setOnClickListener(view -> {
            if (viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                clickListener.accept(getItem(viewHolder.getAdapterPosition()));
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemVideoListBinding binding;

        ViewHolder(ItemVideoListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(VideoItem videoItem) {
            GlideApp.with(binding.ivMovieVideo)
                    .load(videoItem.getYoutubeThumbnail())
                    .into(binding.ivMovieVideo);
        }
    }
}
