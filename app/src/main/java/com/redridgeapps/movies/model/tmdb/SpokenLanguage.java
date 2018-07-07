
package com.redridgeapps.movies.model.tmdb;

import com.squareup.moshi.Json;

public class SpokenLanguage {

    @Json(name = "iso_639_1")
    private String iso6391;

    @Json(name = "name")
    private String name;

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
