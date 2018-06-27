package com.redridgeapps.movies.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import com.redridgeapps.movies.R;
import com.redridgeapps.movies.databinding.ActivityDetailBinding;
import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.screen.base.BaseActivity;

public class DetailActivity extends BaseActivity<DetailViewModel, ActivityDetailBinding> {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    public static Intent createIntent(Context context, Movie movie) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        setupLayout(movie);
    }

    @Override
    protected int provideLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected Class<DetailViewModel> provideViewModelClass() {
        return DetailViewModel.class;
    }

    private void setupLayout(Movie movie) {

        setSupportActionBar(getBinding().toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getBinding().collapsingToolbar.setTitle(movie.getTitle());
        getBinding().collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        // Set backdrop aspect ratio to 16:9
        int fullWidth = getResources().getDisplayMetrics().widthPixels;
        ViewGroup.LayoutParams layoutParams = getBinding().movieBackdrop.getLayoutParams();
        layoutParams.width = fullWidth;
        layoutParams.height = (int) (fullWidth * 0.56);

        if (getBinding().mainDetail != null) {

            // Calculate poster size to be relative to screen size with 3:2 aspect ratio.
            float presetItemWidth = getResources().getDimension(R.dimen.default_movie_poster_width);
            int columns = (int) Math.ceil(fullWidth / presetItemWidth);
            int itemWidth = fullWidth / columns;

            getBinding().mainDetail.moviePoster.getLayoutParams().width = itemWidth;
            getBinding().mainDetail.moviePoster.getLayoutParams().height = (int) (itemWidth * 1.5);
        }

        getBinding().setMovie(movie);
    }
}
