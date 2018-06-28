package com.redridgeapps.movies.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.model.tmdb.MovieCollection;
import com.redridgeapps.movies.util.Event;
import com.redridgeapps.movies.util.RetryableError;
import com.redridgeapps.movies.util.function.Action;
import com.redridgeapps.movies.util.function.Function;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MovieCollectionDataSource extends PageKeyedDataSource<Integer, Movie> {

    private CompositeDisposable compositeDisposable;
    private Function<Integer, Single<MovieCollection>> request;
    private MutableLiveData<Event<RetryableError>> errorMutableLiveData;

    MovieCollectionDataSource(CompositeDisposable compositeDisposable, Function<Integer, Single<MovieCollection>> request) {
        this.compositeDisposable = compositeDisposable;
        this.request = request;
        this.errorMutableLiveData = new MutableLiveData<>();
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
                throwable -> {
                    Action retry = () -> loadInitial(params, callback);
                    errorMutableLiveData.postValue(new Event<>(new RetryableError(throwable, retry)));
                }
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
                throwable -> {
                    Action retry = () -> loadAfter(params, callback);
                    errorMutableLiveData.postValue(new Event<>(new RetryableError(throwable, retry)));
                }
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

    public MutableLiveData<Event<RetryableError>> getErrors() {
        return errorMutableLiveData;
    }
}
