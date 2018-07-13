package com.redridgeapps.movies.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.redridgeapps.movies.model.tmdb.Movie;
import com.redridgeapps.movies.util.IntegerListStringConverter;

@Database(
        entities = {
                Movie.class
        },
        version = 1,
        exportSchema = false
)
@TypeConverters({IntegerListStringConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String NAME = "MOVIE_TRACKER";

    public abstract FavouriteDao favouriteDao();
}
