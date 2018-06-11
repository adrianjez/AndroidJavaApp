package com.adrian.testapp.ui.search;

import com.adrian.backend.domain.FoodEntity;
import com.adrian.testapp.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by adrianjez on 06.12.2017.
 */

public interface FoodSearchContract {

    interface View extends BaseView {
        void refreshSearchResult(ArrayList<FoodEntity> foodEntities);
        void onFoodSelected(FoodEntity foodEntity);
    }

    interface Presenter {
        void makeSearch(String query);
        void onFoodSelected(FoodEntity foodEntity);
    }
}
