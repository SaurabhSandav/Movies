package com.redridgeapps.movies.screen.main;

import com.redridgeapps.movies.api.TMDbService;
import com.redridgeapps.movies.screen.base.BaseViewModel;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    private TMDbService tmDbService;

    @Inject
    MainViewModel(TMDbService tmDbService) {
        this.tmDbService = tmDbService;
    }
}
