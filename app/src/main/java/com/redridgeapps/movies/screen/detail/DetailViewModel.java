package com.redridgeapps.movies.screen.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.redridgeapps.movies.api.TMDbService;
import com.redridgeapps.movies.database.AppDatabase;
import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.model.tmdb.MovieDetail;
import com.redridgeapps.movies.screen.base.BaseViewModel;
import com.redridgeapps.movies.util.Event;
import com.redridgeapps.movies.util.RetryableError;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends BaseViewModel {

    private MutableLiveData<MovieDetail> movieDetailLiveData;
    private MutableLiveData<Event<Throwable>> errorsLiveData;
    private TMDbService tmDbService;
    private AppDatabase database;
    private Movie movie;

    @Inject
    DetailViewModel(TMDbService tmDbService, AppDatabase database) {
        this.tmDbService = tmDbService;
        this.database = database;

        this.movieDetailLiveData = new MutableLiveData<>();
        this.errorsLiveData = new MutableLiveData<>();
    }

    public void setMovieId(Movie movie) {
        this.movie = movie;

        fetchMovieDetail();
    }

    public LiveData<MovieDetail> getMovieDetailLiveData() {
        return movieDetailLiveData;
    }

    public LiveData<Event<Throwable>> getErrorsLiveData() {
        return errorsLiveData;
    }

    public LiveData<Boolean> getFavouriteState() {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        Disposable disposable = database.favouriteDao().getFavouriteMovie(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favouriteMovie -> result.postValue(Boolean.TRUE),
                        throwable -> errorsLiveData.postValue(new Event<>(throwable)),
                        () -> result.postValue(Boolean.FALSE)
                );

        getCompositeDisposable().add(disposable);

        return result;
    }

    public void addToFavourites() {

        Disposable disposable = Completable
                .fromAction(() -> database.favouriteDao().insertFavourites(movie))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }, throwable -> errorsLiveData.postValue(new Event<>(throwable)));

        getCompositeDisposable().add(disposable);
    }

    public void removeFromFavourites() {

        Disposable disposable = Completable
                .fromAction(() -> database.favouriteDao().deleteFavourites(movie))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }, throwable -> errorsLiveData.postValue(new Event<>(throwable)));

        getCompositeDisposable().add(disposable);
    }

    private void fetchMovieDetail() {
        Disposable disposable = tmDbService.getMovieDetail(
                movie.getId(),
                TMDbService.TMDB_API_KEY,
                TMDbService.INCLUDE_REVIEWS_AND_VIDEOS
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movie -> movieDetailLiveData.postValue(movie),
                        throwable -> {
                            RetryableError retryableError = new RetryableError(throwable, this::fetchMovieDetail);
                            errorsLiveData.postValue(new Event<>(retryableError));
                        }
                );

        getCompositeDisposable().add(disposable);
    }
}
