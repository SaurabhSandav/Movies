package com.redridgeapps.movies.api;

import com.redridgeapps.movies.BuildConfig;
import com.redridgeapps.movies.model.tmdb.MovieCollection;
import com.redridgeapps.movies.model.tmdb.MovieDetail;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDbService {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    String TMDB_API_KEY = BuildConfig.TMDB_API_KEY;
    String DEFAULT_IMAGE_POSTER_SIZE = "w342";
    String DEFAULT_IMAGE_BACKDROP_SIZE = "w780";
    String INCLUDE_REVIEWS_AND_VIDEOS = "reviews,videos";

    static String buildPosterURL(String path) {
        return IMAGE_BASE_URL + DEFAULT_IMAGE_POSTER_SIZE + path;
    }

    static String buildBackdropURL(String path) {
        if (path == null) return null;
        else return IMAGE_BASE_URL + DEFAULT_IMAGE_BACKDROP_SIZE + path;
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

    @GET("movie/{movieId}")
    Single<MovieDetail> getMovieDetail(
            @Path("movieId") String movieId,
            @Query("api_key") String apiKey,
            @Query("append_to_response") String appendToResponse
    );
}
