package com.redridgeapps.movies.screen.main;

import android.support.annotation.NonNull;

import com.redridgeapps.movies.api.TMDbService;
import com.redridgeapps.movies.screen.base.BaseViewModel;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    private TMDbService tmDbService;
    private String sort;

    @Inject
    MainViewModel(TMDbService tmDbService) {
        this.tmDbService = tmDbService;
    }

    public void setSort(@NonNull String sort) {
        if (sort.equals(this.sort)) return;
        this.sort = sort;
    }
}
