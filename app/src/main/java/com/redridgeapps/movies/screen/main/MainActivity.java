package com.redridgeapps.movies.screen.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.redridgeapps.movies.R;
import com.redridgeapps.movies.databinding.ActivityMainBinding;
import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.screen.base.BaseActivity;
import com.redridgeapps.movies.screen.detail.DetailActivity;
import com.redridgeapps.movies.util.Constants;
import com.redridgeapps.movies.util.RetryableError;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    @Inject
    public SharedPreferences prefs;

    private String sort;
    private int itemWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sort = prefs.getString(Constants.KEY_SORT_MAIN, Constants.DEFAULT_SORT_MAIN);

        setupRecyclerView();
        fetchMoviesIfConnected();
    }

    @Override
    protected int provideLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> provideViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_options, menu);

        int sortId;

        switch (sort) {
            case Constants.SORT_POPULAR:
                sortId = R.id.sort_popular;
                break;
            case Constants.SORT_TOP_RATED:
                sortId = R.id.sort_top_rated;
                break;
            default:
                throw new IllegalArgumentException("Invalid sort argument!");
        }

        menu.findItem(sortId).setChecked(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sort_popular:
                changeSort(item, Constants.SORT_POPULAR);
                return true;
            case R.id.sort_top_rated:
                changeSort(item, Constants.SORT_TOP_RATED);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeSort(MenuItem item, String sort) {
        item.setChecked(true);
        this.sort = sort;
        prefs.edit().putString(Constants.KEY_SORT_MAIN, sort).apply();

        fetchMoviesIfConnected();
    }

    private void setupRecyclerView() {

        // Calculate poster size to be relative to screen size
        float presetItemWidth = getResources().getDimension(R.dimen.default_movie_poster_width);
        int fullWidth = getResources().getDisplayMetrics().widthPixels;
        int columns = (int) Math.ceil(fullWidth / presetItemWidth);
        itemWidth = fullWidth / columns;

        getBinding().recyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        getBinding().recyclerView.setHasFixedSize(true);
    }

    private boolean isConnectedToNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void fetchMoviesIfConnected() {
        if (isConnectedToNetwork()) {
            fetchMovies();
        } else {
            showNotConnected();
            getBinding().recheck.setOnClickListener(view -> fetchMoviesIfConnected());
        }
    }

    private void fetchMovies() {
        showLoading();

        MovieListAdapter adapter = new MovieListAdapter(itemWidth, this::handleListClick);
        getBinding().recyclerView.setAdapter(adapter);

        getViewModel().setSort(sort);
        getViewModel().getInitialLoadUpdates().observe(this, o -> showList());

        adapter.submitList(getViewModel().getMoviesPagedList());

        getViewModel().getMovieListErrors().observe(this, event -> {
            if (event == null || event.hasBeenHandled()) return;

            RetryableError error = event.getPayloadIfNotHandled();
            String errorString = error.getThrowable().getMessage();
            errorString = (errorString != null) ? errorString : getString(R.string.error_network_request_failure);

            Snackbar.make(getBinding().getRoot(), errorString, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.text_retry, view -> error.retry())
                    .show();
        });
    }

    private void handleListClick(Movie movie) {
        startActivity(DetailActivity.createIntent(MainActivity.this, movie));
    }

    private void showLoading() {
        getBinding().recyclerView.setVisibility(View.INVISIBLE);
        getBinding().loading.setVisibility(View.VISIBLE);
        getBinding().notConnected.setVisibility(View.GONE);
    }

    private void showNotConnected() {
        getBinding().recyclerView.setVisibility(View.INVISIBLE);
        getBinding().loading.setVisibility(View.GONE);
        getBinding().notConnected.setVisibility(View.VISIBLE);
    }

    private void showList() {
        getBinding().recyclerView.setVisibility(View.VISIBLE);
        getBinding().loading.setVisibility(View.GONE);
        getBinding().notConnected.setVisibility(View.GONE);
    }
}
