package com.adrian.testapp.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.adrian.backend.domain.FoodEntity;
import com.adrian.testapp.ui.base.BaseActivity;

/**
 * Created by adrianjez on 07.12.2017.
 */

public class FoodDetailsActivity extends BaseActivity {

    public static Intent getIntent(Context context, FoodEntity foodEntity){
        Intent c = new Intent(context, FoodDetailsActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(FoodEntity.BUNDLE_KEY, foodEntity);
        c.putExtras(args);
        return c;
    }

    @Override
    protected Fragment newFragmentInstanceForArgs(Bundle args) {
        FoodDetailsFragment f = new FoodDetailsFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public String getFragmentTag() {
        return FoodDetailsFragment.FRAGMENT_TAG;
    }

}
