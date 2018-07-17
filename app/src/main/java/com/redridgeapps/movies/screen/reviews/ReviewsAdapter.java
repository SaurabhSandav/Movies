package com.redridgeapps.movies.screen.reviews;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.redridgeapps.movies.R;
import com.redridgeapps.movies.databinding.ItemReviewListBinding;
import com.redridgeapps.movies.model.tmdb.ReviewItem;

public class ReviewsAdapter extends ListAdapter<ReviewItem, ReviewsAdapter.ViewHolder> {

    ReviewsAdapter() {
        super(ReviewItem.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemReviewListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_review_list,
                parent,
                false
        );

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemReviewListBinding binding;

        ViewHolder(ItemReviewListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(ReviewItem item) {
            binding.setReviewItem(item);
            binding.executePendingBindings();
        }
    }
}
