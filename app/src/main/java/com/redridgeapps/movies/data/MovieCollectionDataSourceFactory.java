package com.redridgeapps.movies.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.model.tmdb.MovieCollection;
import com.redridgeapps.movies.util.function.Function;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class MovieCollectionDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private MutableLiveData<MovieCollectionDataSource> dataSourceMutableLiveData;
    private CompositeDisposable compositeDisposable;
    private Function<Integer, Single<MovieCollection>> request;

    public MovieCollectionDataSourceFactory(CompositeDisposable compositeDisposable, Function<Integer, Single<MovieCollection>> request) {
        this.compositeDisposable = compositeDisposable;
        this.request = request;
        this.dataSourceMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MovieCollectionDataSource dataSource = new MovieCollectionDataSource(compositeDisposable, request);
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public LiveData<MovieCollectionDataSource> getDataSourceLiveData() {
        return dataSourceMutableLiveData;
    }
}
