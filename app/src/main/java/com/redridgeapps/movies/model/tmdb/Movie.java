package com.redridgeapps.movies.model.tmdb;

import com.squareup.moshi.Json;

import java.util.List;

public class Movie {

    @Json(name = "poster_path")
    private String posterPath;

    @Json(name = "adult")
    private boolean adult;

    @Json(name = "overview")
    private String overview;

    @Json(name = "release_date")
    private String releaseDate;

    @Json(name = "genre_ids")
    private List<Integer> genreIds = null;

    @Json(name = "id")
    private String id;

    @Json(name = "original_title")
    private String originalTitle;

    @Json(name = "original_language")
    private String originalLanguage;

    @Json(name = "title")
    private String title;

    @Json(name = "backdrop_path")
    private String backdropPath;

    @Json(name = "popularity")
    private double popularity;

    @Json(name = "vote_count")
    private int voteCount;

    @Json(name = "video")
    private boolean video;

    @Json(name = "vote_average")
    private double voteAverage;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
