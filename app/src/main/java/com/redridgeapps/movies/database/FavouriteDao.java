package com.redridgeapps.movies.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.redridgeapps.movies.model.tmdb.Movie;

import io.reactivex.Maybe;

@Dao
public interface FavouriteDao {

    @Query("SELECT * FROM favourite_movies WHERE id = :id")
    Maybe<Movie> getFavouriteMovie(String id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavourites(Movie... movies);

    @Delete
    void deleteFavourites(Movie... movies);
}
