
package com.redridgeapps.movies.model.tmdb;

import com.squareup.moshi.Json;

public class BelongsToCollection {

    @Json(name = "id")
    private Integer id;

    @Json(name = "name")
    private String name;

    @Json(name = "poster_path")
    private String posterPath;

    @Json(name = "backdrop_path")
    private String backdropPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

}
