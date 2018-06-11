package com.adrian.testapp.ui.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.adrian.backend.domain.FoodEntity;
import com.adrian.testapp.databinding.AdapterFoodSearchMainViewHolderBinding;
import com.adrian.testapp.ui.search.FoodSearchPresenter;

import java.util.ArrayList;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class FoodSearchAdapter extends RecyclerView.Adapter<FoodSearchAdapter.FoodViewHolder> {

    private LayoutInflater mInflater;
    private FoodSearchPresenter mPresenter;
    private ArrayList<FoodEntity> mData;

    public FoodSearchAdapter(Context context, FoodSearchPresenter presenter) {
        this.mPresenter = presenter;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FoodViewHolder(AdapterFoodSearchMainViewHolderBinding.inflate(mInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        holder.fillCellView(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    //** Public methods
    public void setOrReplaceData(ArrayList<FoodEntity> data){
        this.mData = data;
        notifyDataSetChanged();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private AdapterFoodSearchMainViewHolderBinding mCellBinding;

        private FoodViewHolder(AdapterFoodSearchMainViewHolderBinding cellBinding) {
            super(cellBinding.getRoot());
            this.mCellBinding = cellBinding;
            this.mCellBinding.setPresenter(mPresenter);
        }

        private void fillCellView(FoodEntity foodEntity){
            mCellBinding.setFoodEntity(foodEntity);
            mCellBinding.executePendingBindings();
        }

    }
}
