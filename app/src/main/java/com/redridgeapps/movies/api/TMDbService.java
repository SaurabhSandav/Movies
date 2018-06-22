package com.redridgeapps.movies.api;

import com.redridgeapps.movies.model.tmdb.MovieCollection;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbService {

    String BASE_URL = "https://api.themoviedb.org/3/";

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
