package com.redridgeapps.movies.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
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

        observeErrors();

        getViewModel().setMovieId(movie);
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

    private void observeErrors() {
        getViewModel().getErrorsLiveData().observe(this, event -> {
            if (event == null || event.hasBeenHandled()) return;

            Snackbar.make(getBinding().getRoot(), event.getPayload().getMessage(), Snackbar.LENGTH_LONG)
                    .show();
        });
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

        setupFavouriteButton();
        getBinding().setMovie(movie);
    }

    private void setupFavouriteButton() {

        getViewModel().getFavouriteState().observe(this, favourite -> {
            if (favourite == null) return;
            setFavouriteButtonState(favourite);
            getBinding().mainDetail.btFavourite.setVisibility(View.VISIBLE);
        });

        getBinding().mainDetail.btFavourite.setOnClickListener(view -> {
            boolean newState = !((boolean) view.getTag());

            if (newState) getViewModel().addToFavourites();
            else getViewModel().removeFromFavourites();

            setFavouriteButtonState(newState);
        });
    }

    private void setFavouriteButtonState(boolean favourite) {
        int stringRes = favourite ? R.string.text_remove_from_favourites : R.string.text_add_to_favourites;

        getBinding().mainDetail.btFavourite.setText(stringRes);
        getBinding().mainDetail.btFavourite.setTag(favourite);
    }
}
