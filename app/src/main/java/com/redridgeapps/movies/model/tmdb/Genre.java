
package com.redridgeapps.movies.model.tmdb;

import com.squareup.moshi.Json;

public class Genre {

    @Json(name = "id")
    private Integer id;

    @Json(name = "name")
    private String name;

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

}
