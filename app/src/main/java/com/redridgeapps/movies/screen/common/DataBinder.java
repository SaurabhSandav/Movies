package com.redridgeapps.movies.screen.common;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.redridgeapps.movies.util.GlideApp;

public final class DataBinder {

    private DataBinder() {
    }

    @BindingAdapter({"imageUrl", "placeholder", "error"})
    public static void setImageUrl(
            ImageView imageView, String url,
            Drawable placeholder,
            Drawable error
    ) {

        if (url != null) {
            GlideApp.with(imageView.getContext())
                    .load(url)
                    .fitCenter()
                    .placeholder(placeholder)
                    .error(error)
                    .into(imageView);
        } else {
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }
}