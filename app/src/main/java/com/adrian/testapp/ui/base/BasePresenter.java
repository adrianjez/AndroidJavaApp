package com.adrian.testapp.ui.base;

import java.lang.ref.WeakReference;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class BasePresenter<T extends BaseView> {

    protected WeakReference<T> mView;

    public BasePresenter(T view){
        mView = new WeakReference<>(view);
    }

    protected T getView(){
        if(mView != null)
            return mView.get();
        return null;
    }

}
