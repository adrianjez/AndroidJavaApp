package com.adrian.testapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.adrian.testapp.R;
import com.adrian.testapp.helpers.FragmentHelper;

/**
 * Created by adrianjez on 07.12.2017.
 */

public abstract class BaseActivity extends FragmentActivity {

    private Fragment f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_containing);
        if(savedInstanceState == null) {
            f = newFragmentInstanceForArgs(getIntent() != null ? getIntent().getExtras() : new Bundle());
            FragmentHelper.addFirstFragment(getSupportFragmentManager(),f, getFragmentTag());
        }

    }

    //** Abstract methods
    protected abstract Fragment newFragmentInstanceForArgs(Bundle args);
    public abstract String getFragmentTag();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, getFragmentTag(), f);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            f = getSupportFragmentManager().getFragment(savedInstanceState, getFragmentTag());
        }
    }


}
