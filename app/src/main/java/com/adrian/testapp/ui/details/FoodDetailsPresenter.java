package com.adrian.testapp.ui.details;

import com.adrian.backend.domain.FoodDetailsEntity;
import com.adrian.backend.interactors.InteractorFood;
import com.adrian.backend.services.BaseService;
import com.adrian.backend.services.ErrorManager;
import com.adrian.testapp.ui.base.BasePresenter;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class FoodDetailsPresenter extends BasePresenter<FoodDetailsContract.View>
        implements FoodDetailsContract.Presenter {

    private final InteractorFood mInteractorFood;

    public FoodDetailsPresenter(FoodDetailsContract.View view, final InteractorFood interactorFood) {
        super(view);
        this.mInteractorFood = interactorFood;
    }

    @Override
    public void loadFoodDetails(String foodID) {
        if (getView() != null) {
            getView().showWait();
            mInteractorFood.loadMealDetails(foodID, new BaseService.IBaseServiceResponse<FoodDetailsEntity>() {
                @Override
                public void onCompleted(FoodDetailsEntity result) {
                    if (getView() != null) {
                        getView().removeWait();
                        getView().foodDetailsLoaded(result);
                    }
                }

                @Override
                public void onError(ErrorManager errorManager) {
                    if (getView() != null) {
                        getView().removeWait();
                        getView().onError(errorManager.getAppErrorMessage());
                    }
                }
            });
        }
    }

    @Override
    public void viewInstructionsClicked(FoodDetailsEntity foodDetailsEntity) {
        if(getView() != null){
            getView().showURL(foodDetailsEntity.getF2fUrl());
        }
    }

    @Override
    public void viewOriginalClicked(FoodDetailsEntity foodDetailsEntity) {
        if(getView() != null){
            getView().showURL(foodDetailsEntity.getSourceUrl());
        }
    }
}
