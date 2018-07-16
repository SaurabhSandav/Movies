package com.redridgeapps.movies.screen.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.redridgeapps.movies.api.TMDbService;
import com.redridgeapps.movies.data.MoviesDataSource;
import com.redridgeapps.movies.database.AppDatabase;
import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.model.tmdb.MovieCollection;
import com.redridgeapps.movies.screen.base.BaseViewModel;
import com.redridgeapps.movies.util.Constants;
import com.redridgeapps.movies.util.Event;
import com.redridgeapps.movies.util.MainThreadExecutor;
import com.redridgeapps.movies.util.function.Function;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private static final int PAGE_SIZE = 20;

    private PagedList<Movie> moviesPagedList;
    private MediatorLiveData<Event<Throwable>> errorsMediatorLiveData;
    private TMDbService tmDbService;
    private final PagedList.Config config;
    private AppDatabase database;
    private String sort;

    @Inject
    MainViewModel(TMDbService tmDbService, AppDatabase database) {
        this.tmDbService = tmDbService;
        this.database = database;
        this.errorsMediatorLiveData = new MediatorLiveData<>();

        this.config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE * 2)
                .build();
    }

    public void setSort(@NonNull String sort) {
        if (sort.equals(this.sort)) return;
        this.sort = sort;
        setupMovies(page -> getMovieRequest(sort, page));
    }

    public PagedList<Movie> getMoviesPagedList() {
        return moviesPagedList;
    }

    public LiveData<Event<Throwable>> getErrorsLiveData() {
        return errorsMediatorLiveData;
    }

    private void setupMovies(Function<Integer, Single<MovieCollection>> request) {

        MoviesDataSource dataSource = new MoviesDataSource(getCompositeDisposable(), request);

        moviesPagedList = new PagedList.Builder<>(dataSource, config)
                .setFetchExecutor(MainThreadExecutor.getExecutor())
                .setNotifyExecutor(MainThreadExecutor.getExecutor())
                .build();

        errorsMediatorLiveData.addSource(dataSource.getErrors(), errorsMediatorLiveData::setValue);
    }

    private Single<MovieCollection> getMovieRequest(String sort, int page) {
        switch (sort) {
            case Constants.SORT_TOP_RATED:
                return tmDbService.getTopRated(TMDbService.TMDB_API_KEY, page);
            case Constants.SORT_POPULAR:
                return tmDbService.getPopular(TMDbService.TMDB_API_KEY, page);
            default:
                throw new IllegalArgumentException("Invalid sort argument!");
        }
    }

    public LiveData<List<Movie>> getFavouriteMovies() {
        MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();

        Disposable disposable = database.favouriteDao()
                .getFavouriteMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesLiveData::postValue,
                        throwable -> errorsMediatorLiveData.postValue(new Event<>(throwable)));

        getCompositeDisposable().add(disposable);

        return moviesLiveData;
    }
}
