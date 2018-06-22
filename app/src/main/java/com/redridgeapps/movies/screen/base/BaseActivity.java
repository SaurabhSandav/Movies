package com.redridgeapps.movies.screen.base;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<VM extends BaseViewModel> extends DaggerAppCompatActivity {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private VM viewModel;

    protected abstract Class<VM> provideViewModelClass();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(provideViewModelClass());
    }

    protected VM getViewModel() {
        return viewModel;
    }
}
