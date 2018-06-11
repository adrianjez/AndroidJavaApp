package com.adrian.testapp.ui.details.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.adrian.backend.domain.FoodDetailsEntity;
import com.adrian.testapp.databinding.AdapterFoodDetailsFooterViewHolderBinding;
import com.adrian.testapp.databinding.AdapterFoodDetailsHeaderViewHolderBinding;
import com.adrian.testapp.databinding.AdapterFoodDetailsIngredientViewHolderBinding;
import com.adrian.testapp.ui.details.FoodDetailsContract;

/**
 * Created by adrianjez on 07.12.2017.
 */

public class FoodDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TOP_HEADER_CELL_VIEW_TYPE = 1;
    private static final int FOOD_INGREDIENT_CELL_VIEW_TYPE = 2;
    private static final int BOTTOM_FOOTER_CELL_VIEW_TYPE = 3;

    private FoodDetailsContract.Presenter mPresenter;
    private LayoutInflater mInflater;
    private FoodDetailsEntity mFoodDetailsEntity;

    public FoodDetailsAdapter(Context context, FoodDetailsContract.Presenter presenter) {
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mPresenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TOP_HEADER_CELL_VIEW_TYPE:
                return new HeaderViewHolder(AdapterFoodDetailsHeaderViewHolderBinding.inflate(mInflater, parent, false));
            case FOOD_INGREDIENT_CELL_VIEW_TYPE:
                return new IngredientViewHolder(AdapterFoodDetailsIngredientViewHolderBinding.inflate(mInflater, parent, false));
            case BOTTOM_FOOTER_CELL_VIEW_TYPE:
                return new FooterViewHolder(mPresenter,
                        AdapterFoodDetailsFooterViewHolderBinding.inflate(mInflater, parent, false));
        }
        throw new RuntimeException("Unsupported view type found in FoodDetailsAdapter");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TOP_HEADER_CELL_VIEW_TYPE:
                ((HeaderViewHolder) holder).fillCellView(mFoodDetailsEntity);
                break;
            case FOOD_INGREDIENT_CELL_VIEW_TYPE:
                ((IngredientViewHolder) holder).fillCellView(mFoodDetailsEntity.getIngredients().get(position - 1));
                break;
            case BOTTOM_FOOTER_CELL_VIEW_TYPE:
                ((FooterViewHolder) holder).fillCellView(mFoodDetailsEntity);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mFoodDetailsEntity == null ? 0
                : getIngredientsCount()
                + 2; //FOOTER + HEADER
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TOP_HEADER_CELL_VIEW_TYPE;
        if (position <= getIngredientsCount())
            return FOOD_INGREDIENT_CELL_VIEW_TYPE;
        return BOTTOM_FOOTER_CELL_VIEW_TYPE;

    }

    //** Public methods
    public void setData(FoodDetailsEntity foodDetailsEntity) {
        mFoodDetailsEntity = foodDetailsEntity;
        notifyDataSetChanged();
    }

    //** Private methods
    private int getIngredientsCount() {
        return mFoodDetailsEntity != null && mFoodDetailsEntity.getIngredients() != null
                ? mFoodDetailsEntity.getIngredients().size() : 0;
    }

    //** CellView Types
    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private AdapterFoodDetailsHeaderViewHolderBinding mCellBinding;

        private HeaderViewHolder(AdapterFoodDetailsHeaderViewHolderBinding cellBinding) {
            super(cellBinding.getRoot());
            this.mCellBinding = cellBinding;
        }

        private void fillCellView(FoodDetailsEntity foodDetailsEntity){
            this.mCellBinding.setFoodEntity(foodDetailsEntity);
            this.mCellBinding.executePendingBindings();
        }
    }


    private class IngredientViewHolder extends RecyclerView.ViewHolder {

        private AdapterFoodDetailsIngredientViewHolderBinding mCellBinding;

        private IngredientViewHolder(AdapterFoodDetailsIngredientViewHolderBinding cellBinding) {
            super(cellBinding.getRoot());
            this.mCellBinding = cellBinding;
        }

        private void fillCellView(String ingredient){
            this.mCellBinding.setIngredient(ingredient);
            this.mCellBinding.executePendingBindings();
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        private AdapterFoodDetailsFooterViewHolderBinding mCellBinding;

        private FooterViewHolder(FoodDetailsContract.Presenter presenter,
                                 AdapterFoodDetailsFooterViewHolderBinding cellBinding) {
            super(cellBinding.getRoot());
            this.mCellBinding = cellBinding;
            this.mCellBinding.setFoodDetailsPresenter(presenter);
        }

        private void fillCellView(FoodDetailsEntity foodDetailsEntity){
            this.mCellBinding.setFoodEntity(foodDetailsEntity);
            this.mCellBinding.executePendingBindings();
        }
    }
}
