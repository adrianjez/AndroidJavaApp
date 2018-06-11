package com.adrian.testapp.ui.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.adrian.testapp.ui.base.BaseActivity;

/**
 * Created by adrianjez on 07.12.2017.
 */

public class FoodSearchActivity extends BaseActivity {


    @Override
    protected Fragment newFragmentInstanceForArgs(Bundle args) {
        return new FoodSearchFragment();
    }

    @Override
    public String getFragmentTag() {
        return FoodSearchFragment.FRAGMENT_TAG;
    }
}
