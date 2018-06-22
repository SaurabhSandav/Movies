package com.redridgeapps.movies.screen.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.redridgeapps.movies.api.TMDbService;
import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.model.tmdb.MovieCollection;
import com.redridgeapps.movies.screen.base.BaseViewModel;
import com.redridgeapps.movies.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    public MutableLiveData<List<Movie>> movies;
    private TMDbService tmDbService;
    private String sort;
    private int page = 0;

    @Inject
    MainViewModel(TMDbService tmDbService) {
        this.tmDbService = tmDbService;
        this.movies = new MutableLiveData<>();
    }

    public void setSort(@NonNull String sort) {
        if (sort.equals(this.sort)) return;
        this.sort = sort;
        movies.setValue(new ArrayList<>());
        page = 0;
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void refreshMovies() {
        Single<MovieCollection> collection;

        switch (sort) {
            case Constants.SORT_TOP_RATED:
                collection = tmDbService.getTopRated(TMDbService.TMDB_API_KEY, ++page);
                break;
            case Constants.SORT_POPULAR:
                collection = tmDbService.getPopular(TMDbService.TMDB_API_KEY, ++page);
                break;
            default:
                throw new IllegalArgumentException("Invalid Sort Argument!");
        }

        Disposable disposable = collection.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieCollection -> {
                            List<Movie> newMovies = movieCollection.getMovies();
                            if (movies.getValue() != null) newMovies.addAll(movies.getValue());
                            movies.postValue(newMovies);
                        },
                        throwable -> {
                            // TODO Handle exception without crashing
                            throw new RuntimeException(throwable);
                        }
                );

        getCompositeDisposable().add(disposable);
    }
}
