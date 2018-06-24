package com.redridgeapps.movies.data;

import android.arch.paging.DataSource;

import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.model.tmdb.MovieCollection;
import com.redridgeapps.movies.util.function.Function;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class MovieCollectionDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private CompositeDisposable compositeDisposable;
    private Function<Integer, Single<MovieCollection>> request;

    public MovieCollectionDataSourceFactory(CompositeDisposable compositeDisposable, Function<Integer, Single<MovieCollection>> request) {
        this.compositeDisposable = compositeDisposable;
        this.request = request;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        return new MovieCollectionDataSource(compositeDisposable, request);
    }
}
