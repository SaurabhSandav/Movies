package com.redridgeapps.movies.di.modules.viewmodel;

import android.arch.lifecycle.ViewModelProvider;

import com.redridgeapps.movies.screen.base.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
