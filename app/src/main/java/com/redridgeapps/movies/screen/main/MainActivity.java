package com.redridgeapps.movies.screen.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.redridgeapps.movies.R;
import com.redridgeapps.movies.databinding.ActivityMainBinding;
import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.screen.base.BaseActivity;
import com.redridgeapps.movies.util.Constants;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    @Inject
    public SharedPreferences prefs;

    private MovieListAdapter adapter;
    private String sort;
    private int itemWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sort = prefs.getString(Constants.KEY_SORT_MAIN, Constants.DEFAULT_SORT_MAIN);

        setupRecyclerView();
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

        prefs.edit().putString(Constants.KEY_SORT_MAIN, sort).apply();

        adapter = new MovieListAdapter(itemWidth, this::handleListClick);
        getBinding().recyclerView.setAdapter(adapter);

        getViewModel().setSort(sort);
        getViewModel().getMovies().observe(this, adapter::submitList);
    }

    private void setupRecyclerView() {

        // Calculate poster size to be relative to screen size
        float presetItemWidth = getResources().getDimension(R.dimen.default_movie_poster_width);
        int fullWidth = getResources().getDisplayMetrics().widthPixels;
        int columns = (int) Math.ceil(fullWidth / presetItemWidth);
        itemWidth = fullWidth / columns;

        adapter = new MovieListAdapter(itemWidth, this::handleListClick);

        getBinding().recyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        getBinding().recyclerView.setAdapter(adapter);
        getBinding().recyclerView.setHasFixedSize(true);

        getViewModel().setSort(sort);

        getViewModel().getMovies().observe(this, adapter::submitList);
    }

    private void handleListClick(Movie movie) {
        Toast.makeText(this, movie.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
