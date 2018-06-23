package com.redridgeapps.movies.screen.common;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public final class DataBinder {

    private DataBinder() {
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        // TODO handle placeholder, error, null
        if (url != null) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        } else {
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }
}