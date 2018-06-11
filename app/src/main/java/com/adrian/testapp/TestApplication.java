package com.adrian.testapp;

import android.app.Application;

import com.adrian.backend.BackendFoodToForkModule;
import com.adrian.testapp.di.ApplicationComponent;
import com.adrian.testapp.di.DaggerApplicationComponent;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class TestApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    //** Public methods
    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    //** Private methods
    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .backendFoodToForkModule(new BackendFoodToForkModule(BuildConfig.wsFood2ForkURL, BuildConfig.food2forkAPIKey))
                .build();
    }
}
