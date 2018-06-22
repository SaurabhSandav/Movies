package com.redridgeapps.movies.di.modules;

import com.redridgeapps.movies.di.scopes.PerActivity;
import com.redridgeapps.movies.screen.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector()
    public abstract MainActivity bindMainActivity();
}
