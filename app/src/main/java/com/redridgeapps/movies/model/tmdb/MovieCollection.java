package com.redridgeapps.movies.model.tmdb;

import com.squareup.moshi.Json;

import java.util.List;

public class MovieCollection {

    @Json(name = "page")
    private int page;

    @Json(name = "results")
    private List<Movie> movies = null;

    @Json(name = "total_results")
    private int totalResults;

    @Json(name = "total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
