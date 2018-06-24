package com.redridgeapps.movies.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
    }

    @Override
    protected int provideLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected Class<DetailViewModel> provideViewModelClass() {
        return DetailViewModel.class;
    }
}
