package com.redridgeapps.movies.model.tmdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.text.format.DateFormat;

import com.redridgeapps.movies.api.TMDbService;
import com.squareup.moshi.Json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity(tableName = "favourite_movies")
public class Movie implements Parcelable {

    @ColumnInfo(name = "poster_path")
    @Json(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "adult")
    @Json(name = "adult")
    private boolean adult;

    @ColumnInfo(name = "overview")
    @Json(name = "overview")
    private String overview;

    @ColumnInfo(name = "release_date")
    @Json(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "genre_ids")
    @Json(name = "genre_ids")
    private List<Integer> genreIds = null;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @Json(name = "id")
    private String id = "";

    @ColumnInfo(name = "original_title")
    @Json(name = "original_title")
    private String originalTitle;

    @ColumnInfo(name = "original_language")
    @Json(name = "original_language")
    private String originalLanguage;

    @ColumnInfo(name = "title")
    @Json(name = "title")
    private String title;

    @ColumnInfo(name = "backdrop_path")
    @Json(name = "backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "popularity")
    @Json(name = "popularity")
    private double popularity;

    @ColumnInfo(name = "vote_count")
    @Json(name = "vote_count")
    private int voteCount;

    @ColumnInfo(name = "video")
    @Json(name = "video")
    private boolean video;

    @ColumnInfo(name = "vote_average")
    @Json(name = "vote_average")
    private double voteAverage;

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.posterPath = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.genreIds = new ArrayList<>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.id = in.readString();
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.popularity = in.readDouble();
        this.voteCount = in.readInt();
        this.video = in.readByte() != 0;
        this.voteAverage = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeList(this.genreIds);
        dest.writeString(this.id);
        dest.writeString(this.originalTitle);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.title);
        dest.writeString(this.backdropPath);
        dest.writeDouble(this.popularity);
        dest.writeInt(this.voteCount);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.voteAverage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterURL() {
        return TMDbService.buildPosterURL(this.posterPath);
    }

    public String getBackdropURL() {
        return TMDbService.buildBackdropURL(this.backdropPath);
    }

    public String getSimpleReleaseDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        try {
            Date date = format.parse(releaseDate);
            return DateFormat.format("MMM d, yyyy", date).toString();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {

        @Override
        public boolean areItemsTheSame(Movie oldItem, Movie newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(Movie oldItem, Movie newItem) {
            return true;
        }
    };
}
