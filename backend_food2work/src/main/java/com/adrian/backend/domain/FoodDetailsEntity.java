package com.adrian.backend.domain;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class FoodDetailsEntity extends BaseFoodEntity implements Serializable {

    @SerializedName("ingredients")
    ArrayList<String> ingredients;

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringList(this.ingredients);
    }

    public FoodDetailsEntity() {
    }

    protected FoodDetailsEntity(Parcel in) {
        super(in);
        this.ingredients = in.createStringArrayList();
    }

    public static final Creator<FoodDetailsEntity> CREATOR = new Creator<FoodDetailsEntity>() {
        @Override
        public FoodDetailsEntity createFromParcel(Parcel source) {
            return new FoodDetailsEntity(source);
        }

        @Override
        public FoodDetailsEntity[] newArray(int size) {
            return new FoodDetailsEntity[size];
        }
    };

}
