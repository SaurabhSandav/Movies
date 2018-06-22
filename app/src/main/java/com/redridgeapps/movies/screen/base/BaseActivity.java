package com.redridgeapps.movies.screen.base;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<VM extends BaseViewModel, VDB extends ViewDataBinding> extends DaggerAppCompatActivity {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private VM viewModel;
    private VDB binding;

    @LayoutRes
    protected abstract int provideLayout();

    protected abstract Class<VM> provideViewModelClass();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, provideLayout());

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(provideViewModelClass());
    }

    protected VM getViewModel() {
        return viewModel;
    }

    protected VDB getBinding() {
        return binding;
    }
}
