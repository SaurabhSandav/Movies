package com.redridgeapps.movies.screen.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.redridgeapps.movies.api.TMDbService;
import com.redridgeapps.movies.data.MovieCollectionDataSource;
import com.redridgeapps.movies.data.MovieCollectionDataSourceFactory;
import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.model.tmdb.MovieCollection;
import com.redridgeapps.movies.screen.base.BaseViewModel;
import com.redridgeapps.movies.util.Constants;
import com.redridgeapps.movies.util.Event;
import com.redridgeapps.movies.util.RetryableError;
import com.redridgeapps.movies.util.function.Function;

import javax.inject.Inject;

import io.reactivex.Single;

public class MainViewModel extends BaseViewModel {

    private static final int PAGE_SIZE = 20;

    public LiveData<PagedList<Movie>> movies;
    private LiveData<Event<RetryableError>> movieListErrors;

    private TMDbService tmDbService;
    private String sort;

    @Inject
    MainViewModel(TMDbService tmDbService) {
        this.tmDbService = tmDbService;
    }

    public void setSort(@NonNull String sort) {
        if (sort.equals(this.sort)) return;
        this.sort = sort;
        setupMovies(page -> getMovieRequest(sort, page));
    }

    public LiveData<PagedList<Movie>> getMovies() {
        return movies;
    }

    public LiveData<Event<RetryableError>> getMovieListErrors() {
        return movieListErrors;
    }

    private void setupMovies(Function<Integer, Single<MovieCollection>> request) {
        MovieCollectionDataSourceFactory dataSourceFactory =
                new MovieCollectionDataSourceFactory(getCompositeDisposable(), request);

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE * 2)
                .build();

        movies = new LivePagedListBuilder<>(dataSourceFactory, config).build();

        movieListErrors = Transformations.switchMap(dataSourceFactory.getDataSourceLiveData(), MovieCollectionDataSource::getErrors);
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
}
