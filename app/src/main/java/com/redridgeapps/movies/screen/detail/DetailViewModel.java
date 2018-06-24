package com.redridgeapps.movies.screen.detail;

import com.redridgeapps.movies.api.TMDbService;
import com.redridgeapps.movies.screen.base.BaseViewModel;

import javax.inject.Inject;

public class DetailViewModel extends BaseViewModel {

    private TMDbService tmDbService;

    @Inject
    DetailViewModel(TMDbService tmDbService) {
        this.tmDbService = tmDbService;
    }
}
