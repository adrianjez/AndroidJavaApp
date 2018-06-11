package com.adrian.backend.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by adrianjez on 06.12.2017.
 */

public abstract class BaseFoodEntity implements Parcelable, Serializable {

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("f2f_url")
    private String f2fUrl;

    @SerializedName("title")
    private String title;

    @SerializedName("source_url")
    private String sourceUrl;

    @SerializedName("recipe_id")
    private String recipeId;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("social_rank")
    private float socialRank;

    @SerializedName("publisher_url")
    private String publisherUrl;

    public String getPublisher() {
        return publisher;
    }

    public String getF2fUrl() {
        return f2fUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getSocialRank() {
        return socialRank;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.publisher);
        dest.writeString(this.f2fUrl);
        dest.writeString(this.title);
        dest.writeString(this.sourceUrl);
        dest.writeString(this.recipeId);
        dest.writeString(this.imageUrl);
        dest.writeFloat(this.socialRank);
        dest.writeString(this.publisherUrl);
    }

    public BaseFoodEntity() {
    }

    protected BaseFoodEntity(Parcel in) {
        this.publisher = in.readString();
        this.f2fUrl = in.readString();
        this.title = in.readString();
        this.sourceUrl = in.readString();
        this.recipeId = in.readString();
        this.imageUrl = in.readString();
        this.socialRank = in.readFloat();
        this.publisherUrl = in.readString();
    }
}
