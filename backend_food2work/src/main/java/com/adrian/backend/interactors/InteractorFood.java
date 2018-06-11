package com.adrian.backend.interactors;

import com.adrian.backend.domain.FoodDetailsEntity;
import com.adrian.backend.domain.FoodEntity;
import com.adrian.backend.services.BaseService;

import java.util.ArrayList;

/**
 * Created by adrianjez on 06.12.2017.
 */

public interface InteractorFood {

    void loadMealsList(String query, BaseService.IBaseServiceResponse<ArrayList<FoodEntity>> callback);
    void loadMealDetails(String mealID, BaseService.IBaseServiceResponse<FoodDetailsEntity> callback);
}
