package com.adrian.testapp.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adrian.backend.domain.FoodEntity;
import com.adrian.backend.interactors.InteractorFood;
import com.adrian.testapp.databinding.FragmentFoodSearchBinding;
import com.adrian.testapp.ui.base.BaseFragment;
import com.adrian.testapp.ui.details.FoodDetailsActivity;
import com.adrian.testapp.ui.search.adapter.FoodSearchAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class FoodSearchFragment extends BaseFragment implements FoodSearchContract.View {

    public static final String FRAGMENT_TAG = "FoodSearch_FRAGMENT_TAG";

    @Inject
    InteractorFood mInteractorFood;

    private FragmentFoodSearchBinding mMainBinding;
    private FoodSearchPresenter mPresenter;
    private ArrayList<FoodEntity> mData;
    private FoodSearchAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        mPresenter = new FoodSearchPresenter(this, mInteractorFood);
    }

    @Override
    public View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMainBinding = FragmentFoodSearchBinding.inflate(inflater, container, false);
        mMainBinding.fragmentFoodSearchSearchView.setOnQueryTextListener(mPresenter);
        mMainBinding.fragmentFoodSearchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMainBinding.fragmentFoodSearchRecyclerView.setAdapter(mAdapter = new FoodSearchAdapter(getContext(), mPresenter));
        if(savedInstanceState != null){
            mMainBinding.fragmentFoodSearchRecyclerView.getLayoutManager()
                    .onRestoreInstanceState(savedInstanceState.getParcelable("RV_STATE"));
            postInit();
        }
        return mMainBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("RV_STATE", mMainBinding.fragmentFoodSearchRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    //** FoodSearchContract.View
    @Override
    public void refreshSearchResult(ArrayList<FoodEntity> foodEntities) {
        this.mData = foodEntities;
        postInit();
    }

    @Override
    public void onFoodSelected(FoodEntity foodEntity) {
        Intent i = FoodDetailsActivity.getIntent(getContext(), foodEntity);
        startActivity(i);
    }

    //** Private methods
    private void postInit(){
        mAdapter.setOrReplaceData(this.mData);
    }
}
