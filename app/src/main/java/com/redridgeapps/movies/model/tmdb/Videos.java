
package com.redridgeapps.movies.model.tmdb;

import com.squareup.moshi.Json;

import java.util.List;

public class Videos {

    @Json(name = "results")
    private List<VideoItem> results = null;

    public List<VideoItem> getResults() {
        return results;
    }

    public void setResults(List<VideoItem> results) {
        this.results = results;
    }

}
