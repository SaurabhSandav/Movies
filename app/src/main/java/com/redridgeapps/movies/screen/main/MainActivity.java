package com.redridgeapps.movies.screen.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.redridgeapps.movies.R;
import com.redridgeapps.movies.screen.base.BaseActivity;
import com.redridgeapps.movies.util.Constants;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel> {

    @Inject
    public SharedPreferences prefs;

    private String sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sort = prefs.getString(Constants.KEY_SORT_MAIN, Constants.DEFAULT_SORT_MAIN);

        getViewModel().setSort(sort);
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

        getViewModel().setSort(sort);
    }
}
