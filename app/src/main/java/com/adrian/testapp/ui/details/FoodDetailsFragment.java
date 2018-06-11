package com.adrian.testapp.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adrian.backend.domain.FoodDetailsEntity;
import com.adrian.backend.domain.FoodEntity;
import com.adrian.backend.interactors.InteractorFood;
import com.adrian.testapp.databinding.FragmentFoodDetailsBinding;
import com.adrian.testapp.ui.base.BaseFragment;
import com.adrian.testapp.ui.details.adapter.FoodDetailsAdapter;
import com.adrian.testapp.ui.web_explorer.WebExplorerActivity;

import javax.inject.Inject;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class FoodDetailsFragment extends BaseFragment implements FoodDetailsContract.View {

    public static final String FRAGMENT_TAG = "FoodDetails_FRAGMENT_TAG";

    @Inject
    InteractorFood mInteractorFood;

    private FoodEntity mFoodEntity;
    private FoodDetailsAdapter mAdapter;
    private FoodDetailsPresenter mPresenter;
    private FoodDetailsEntity mFoodDetailsEntity;
    private FragmentFoodDetailsBinding mMainBinding;


    @Override
    protected String getTitle() {
        if(mFoodEntity != null){
            return mFoodEntity.getTitle();
        }
        return super.getTitle();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        this.mPresenter = new FoodDetailsPresenter(this, mInteractorFood);
        if(getArguments().containsKey(FoodEntity.BUNDLE_KEY))
            mFoodEntity = getArguments().getParcelable(FoodEntity.BUNDLE_KEY);

    }

    @Override
    public View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mMainBinding = FragmentFoodDetailsBinding.inflate(inflater, container, false);
        this.mMainBinding.fragmentFoodDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mMainBinding.fragmentFoodDetailsRecyclerView.setAdapter(mAdapter = new FoodDetailsAdapter(getContext(), mPresenter));
        if(savedInstanceState != null){
            this.mMainBinding.fragmentFoodDetailsRecyclerView.getLayoutManager()
                    .onRestoreInstanceState(savedInstanceState.getParcelable("RV_STATE"));
            postInit();
        }
        return mMainBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState == null)
            mPresenter.loadFoodDetails(mFoodEntity.getRecipeId());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("RV_STATE", mMainBinding.fragmentFoodDetailsRecyclerView.getLayoutManager()
                .onSaveInstanceState());
    }

    //** FoodDetailsContract.View
    @Override
    public void foodDetailsLoaded(FoodDetailsEntity foodDetailsEntity) {
        this.mFoodDetailsEntity = foodDetailsEntity;
        postInit();
    }

    @Override
    public void showURL(String url) {
        Intent i = WebExplorerActivity.getIntent(getContext(), url);
        startActivity(i);
    }

    //** Private methods
    private void postInit(){
        mAdapter.setData(this.mFoodDetailsEntity);
    }
}
