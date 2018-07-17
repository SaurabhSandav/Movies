
package com.redridgeapps.movies.model.tmdb;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.util.DiffUtil;

import com.squareup.moshi.Json;

public class ReviewItem implements Parcelable {

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

    public static final DiffUtil.ItemCallback<ReviewItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<ReviewItem>() {

        @Override
        public boolean areItemsTheSame(ReviewItem oldItem, ReviewItem newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(ReviewItem oldItem, ReviewItem newItem) {
            return true;
        }
    };
    public static final Parcelable.Creator<ReviewItem> CREATOR = new Parcelable.Creator<ReviewItem>() {
        @Override
        public ReviewItem createFromParcel(Parcel source) {
            return new ReviewItem(source);
        }

        @Override
        public ReviewItem[] newArray(int size) {
            return new ReviewItem[size];
        }
    };

    public ReviewItem() {
    }

    private ReviewItem(Parcel in) {
        this.author = in.readString();
        this.content = in.readString();
        this.id = in.readString();
        this.url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.content);
        dest.writeString(this.id);
        dest.writeString(this.url);
    }
}
