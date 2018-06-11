package com.adrian.backend.domain;

import android.os.Parcel;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class FoodEntity extends BaseFoodEntity {

    public static final String BUNDLE_KEY = "FoodEntity_BUNDLE_KEY";

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public FoodEntity() {
    }

    protected FoodEntity(Parcel in) {
        super(in);
    }

    public static final Creator<FoodEntity> CREATOR = new Creator<FoodEntity>() {
        @Override
        public FoodEntity createFromParcel(Parcel source) {
            return new FoodEntity(source);
        }

        @Override
        public FoodEntity[] newArray(int size) {
            return new FoodEntity[size];
        }
    };
}
