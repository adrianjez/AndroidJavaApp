package com.adrian.testapp.ui.details;

import com.adrian.backend.domain.FoodDetailsEntity;
import com.adrian.testapp.ui.base.BaseView;

/**
 * Created by adrianjez on 06.12.2017.
 */

public interface FoodDetailsContract {

    interface View extends BaseView {
        void foodDetailsLoaded(FoodDetailsEntity foodDetailsEntity);
        void showURL(String url);
    }

    interface Presenter {
        void loadFoodDetails(String foodID);
        void viewInstructionsClicked(FoodDetailsEntity foodEntity);
        void viewOriginalClicked(FoodDetailsEntity foodEntity);
    }
}
