package com.adrian.testapp.ui.search;

import android.support.v7.widget.SearchView;

import com.adrian.backend.domain.FoodEntity;
import com.adrian.backend.interactors.InteractorFood;
import com.adrian.backend.services.BaseService;
import com.adrian.backend.services.ErrorManager;
import com.adrian.testapp.ui.base.BasePresenter;

import java.util.ArrayList;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class FoodSearchPresenter extends BasePresenter<FoodSearchContract.View>
        implements FoodSearchContract.Presenter, SearchView.OnQueryTextListener {

    private final InteractorFood mInteractorFood;

    public FoodSearchPresenter(FoodSearchContract.View view, final InteractorFood interactorFood){
        super(view);
        this.mInteractorFood = interactorFood;
    }

    @Override
    public void makeSearch(String query) {
        if(getView() != null){
            getView().showWait();
            mInteractorFood.loadMealsList(query, new BaseService.IBaseServiceResponse<ArrayList<FoodEntity>>() {
                @Override
                public void onCompleted(ArrayList<FoodEntity> result) {
                    if(getView() != null) {
                        getView().removeWait();
                        getView().refreshSearchResult(result);
                    }
                }

                @Override
                public void onError(ErrorManager errorManager) {
                    if(getView() != null) {
                        getView().removeWait();
                        getView().onError(errorManager.getAppErrorMessage());
                    }
                }
            });
        }
    }

    @Override
    public void onFoodSelected(FoodEntity foodEntity) {
        if(getView() != null)
            getView().onFoodSelected(foodEntity);
    }

    //** SearchView.OnQueryTextListener
    @Override
    public boolean onQueryTextSubmit(String query) {
        makeSearch(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
