package com.redridgeapps.movies.screen.main;

import android.os.Bundle;

import com.redridgeapps.movies.R;
import com.redridgeapps.movies.screen.base.BaseActivity;

public class MainActivity extends BaseActivity<MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected Class<MainViewModel> provideViewModelClass() {
        return MainViewModel.class;
    }
}
