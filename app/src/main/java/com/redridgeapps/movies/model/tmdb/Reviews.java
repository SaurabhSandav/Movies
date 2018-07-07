
package com.redridgeapps.movies.model.tmdb;

import com.squareup.moshi.Json;

import java.util.List;

public class Reviews {

    @Json(name = "page")
    private Integer page;

    @Json(name = "results")
    private List<ReviewItem> results = null;

    @Json(name = "total_pages")
    private Integer totalPages;

    @Json(name = "total_results")
    private Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<ReviewItem> getResults() {
        return results;
    }

    public void setResults(List<ReviewItem> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

}
