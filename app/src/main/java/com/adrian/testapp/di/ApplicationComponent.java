package com.adrian.testapp.di;

import com.adrian.backend.BackendFoodToForkModule;
import com.adrian.testapp.ui.details.FoodDetailsFragment;
import com.adrian.testapp.ui.search.FoodSearchFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by adrianjez on 06.12.2017.
 */

@Singleton
@Component(modules = {BackendFoodToForkModule.class})
public interface ApplicationComponent {

    void inject(FoodDetailsFragment f);
    void inject(FoodSearchFragment f);

}
