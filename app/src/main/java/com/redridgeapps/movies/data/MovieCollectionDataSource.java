package com.redridgeapps.movies.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.model.tmdb.MovieCollection;
import com.redridgeapps.movies.util.function.Function;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MovieCollectionDataSource extends PageKeyedDataSource<Integer, Movie> {

    private CompositeDisposable compositeDisposable;
    private Function<Integer, Single<MovieCollection>> request;

    MovieCollectionDataSource(CompositeDisposable compositeDisposable, Function<Integer, Single<MovieCollection>> request) {
        this.compositeDisposable = compositeDisposable;
        this.request = request;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        getMovies(
                1,
                movieCollection -> callback.onResult(
                        movieCollection.getMovies(),
                        0,
                        movieCollection.getTotalResults(),
                        null,
                        getAdjacentPageKey(movieCollection)
                ),
                throwable -> {/*TODO handle failure*/}
        );
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        getMovies(
                params.key,
                movieCollection -> callback.onResult(
                        movieCollection.getMovies(),
                        getAdjacentPageKey(movieCollection)
                ),
                throwable -> {/*TODO handle failure*/}
        );
    }

    private void getMovies(int page, Consumer<MovieCollection> onSuccess, Consumer<Throwable> onError) {

        Single<MovieCollection> collection = request.apply(page);

        Disposable disposable = collection.subscribe(onSuccess, onError);

        compositeDisposable.add(disposable);
    }

    private Integer getAdjacentPageKey(MovieCollection collection) {
        if (collection.getPage() >= collection.getTotalPages()) return null;
        else return collection.getPage() + 1;
    }
}
