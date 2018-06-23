package com.redridgeapps.movies.api;

import com.redridgeapps.movies.BuildConfig;
import com.redridgeapps.movies.model.tmdb.MovieCollection;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbService {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    String TMDB_API_KEY = BuildConfig.TMDB_API_KEY;
    String DEFAULT_IMAGE_POSTER_SIZE = "w342";

    static String buildPosterURL(String path) {
        return IMAGE_BASE_URL + DEFAULT_IMAGE_POSTER_SIZE + path;
    }

    @GET("movie/top_rated")
    Single<MovieCollection> getTopRated(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/popular")
    Single<MovieCollection> getPopular(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );
}
