
package com.redridgeapps.movies.model.tmdb;

import com.squareup.moshi.Json;

public class ReviewItem {

    @Json(name = "author")
    private String author;

    @Json(name = "content")
    private String content;

    @Json(name = "id")
    private String id;

    @Json(name = "url")
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
