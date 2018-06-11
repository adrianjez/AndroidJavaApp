package com.adrian.testapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;

import com.adrian.backend.domain.FoodEntity;
import com.adrian.testapp.ui.details.FoodDetailsActivity;
import com.adrian.testapp.ui.details.FoodDetailsFragment;
import com.adrian.testapp.ui.search.FoodSearchActivity;
import com.adrian.testapp.ui.search.FoodSearchFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by adrianjez on 07.12.2017.
 */

@RunWith(RobolectricTestRunner.class)
public class SampleTests {

    private FoodSearchActivity foodSearchActivity;
    private FoodDetailsActivity foodDetailsActivity;

    @Before
    public void setUp(){
        this.foodSearchActivity = Robolectric.setupActivity(FoodSearchActivity.class);

        FoodEntity testFoodDetailsEntity = mock(FoodEntity.class);
        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        Intent i =  FoodDetailsActivity.getIntent(application.getApplicationContext(), testFoodDetailsEntity);

        this.foodDetailsActivity = Robolectric.buildActivity(FoodDetailsActivity.class, i).create().get();

    }

    @Test
    public void checkIfCorrectSearchFragmentPushed(){
        Fragment expected = new FoodSearchFragment();
        Fragment actual = this.foodSearchActivity.getSupportFragmentManager()
                .findFragmentByTag(foodSearchActivity.getFragmentTag());

        assertNotNull(actual);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    public void checkIfCorrectDetailsFragmentPushed(){
        Fragment expected = new FoodDetailsFragment();
        Fragment actual = this.foodDetailsActivity.getSupportFragmentManager()
                .findFragmentByTag(foodDetailsActivity.getFragmentTag());

        assertNotNull(actual);
        assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    public void checkIfCorrectSearchTerm(){
        String currentSearchTerm = ((SearchView)
                foodSearchActivity.findViewById(R.id.fragment_food_search_search_view)).getQueryHint().toString();
        assertEquals(currentSearchTerm, "Search Term");
    }


}
