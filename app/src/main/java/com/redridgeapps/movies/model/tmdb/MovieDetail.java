
package com.redridgeapps.movies.model.tmdb;

import com.squareup.moshi.Json;

import java.util.List;

public class MovieDetail {

    @Json(name = "adult")
    private Boolean adult;

    @Json(name = "backdrop_path")
    private String backdropPath;

    @Json(name = "belongs_to_collection")
    private BelongsToCollection belongsToCollection;

    @Json(name = "budget")
    private Integer budget;

    @Json(name = "genres")
    private List<Genre> genres = null;

    @Json(name = "homepage")
    private String homepage;

    @Json(name = "id")
    private Integer id;

    @Json(name = "imdb_id")
    private String imdbId;

    @Json(name = "original_language")
    private String originalLanguage;

    @Json(name = "original_title")
    private String originalTitle;

    @Json(name = "overview")
    private String overview;

    @Json(name = "popularity")
    private Double popularity;

    @Json(name = "poster_path")
    private String posterPath;

    @Json(name = "production_companies")
    private List<ProductionCompany> productionCompanies = null;

    @Json(name = "production_countries")
    private List<ProductionCountry> productionCountries = null;

    @Json(name = "release_date")
    private String releaseDate;

    @Json(name = "revenue")
    private Integer revenue;

    @Json(name = "runtime")
    private Integer runtime;

    @Json(name = "spoken_languages")
    private List<SpokenLanguage> spokenLanguages = null;

    @Json(name = "status")
    private String status;

    @Json(name = "tagline")
    private String tagline;

    @Json(name = "title")
    private String title;

    @Json(name = "video")
    private Boolean video;

    @Json(name = "vote_average")
    private Double voteAverage;

    @Json(name = "vote_count")
    private Integer voteCount;

    @Json(name = "reviews")
    private Reviews reviews;

    @Json(name = "videos")
    private Videos videos;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

}
