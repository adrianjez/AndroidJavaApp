package com.adrian.backend.services;

import com.adrian.backend.domain.FoodDetailsEntity;
import com.adrian.backend.domain.FoodEntity;
import com.adrian.backend.interactors.InteractorFood;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class ServiceFood extends BaseService implements InteractorFood {

    private interface APIServiceFood {
        @GET("api/search")
        Call<SearchFoodResponse> searchFood(@Query("key") String apiKey, @Query("q") String query);

        @GET("api/get")
        Call<DetailsFoodResponse> getFoodDetails(@Query("key") String apiKey, @Query("rId") String rId);
    }

    private APIServiceFood mAPIServiceFood;

    public ServiceFood(Retrofit retrofit, String apiKey) {
        super(apiKey);
        mAPIServiceFood = retrofit.create(APIServiceFood.class);
    }

    //** InteractorFood
    @Override
    public void loadMealsList(final String query, final IBaseServiceResponse<ArrayList<FoodEntity>> callback) {
        mAPIServiceFood.searchFood(mAPIKey, query)
                .enqueue(new Callback<SearchFoodResponse>() {
                    @Override
                    public void onResponse(Call<SearchFoodResponse> call, Response<SearchFoodResponse> response) {
                        if (response.code() == 200) {
                            callback.onCompleted(response.body().recipes);
                        } else callback.onError(new ErrorManager(response));
                    }

                    @Override
                    public void onFailure(Call<SearchFoodResponse> call, Throwable t) {
                        callback.onError(new ErrorManager(t));
                    }
                });
    }

    @Override
    public void loadMealDetails(String mealID, final IBaseServiceResponse<FoodDetailsEntity> callback) {
        mAPIServiceFood.getFoodDetails(mAPIKey, mealID)
                .enqueue(new Callback<DetailsFoodResponse>() {
                    @Override
                    public void onResponse(Call<DetailsFoodResponse> call, Response<DetailsFoodResponse> response) {
                        if (response.code() == 200) {
                            callback.onCompleted(response.body().foodDetailsEntity);
                        } else callback.onError(new ErrorManager(response));
                    }

                    @Override
                    public void onFailure(Call<DetailsFoodResponse> call, Throwable t) {
                        callback.onError(new ErrorManager(t));
                    }
                });
    }

    private class SearchFoodResponse {

        @SerializedName("count")
        private int count;

        @SerializedName("recipes")
        private ArrayList<FoodEntity> recipes;
    }

    private class DetailsFoodResponse {

        @SerializedName("recipe")
        private FoodDetailsEntity foodDetailsEntity;
    }
}
