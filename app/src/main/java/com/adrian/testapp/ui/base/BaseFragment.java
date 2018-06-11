package com.adrian.testapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adrian.testapp.R;
import com.adrian.testapp.TestApplication;
import com.adrian.testapp.di.ApplicationComponent;

/**
 * Created by adrianjez on 06.12.2017.
 */

public abstract class BaseFragment extends Fragment implements BaseView, View.OnClickListener {

    private View mProgressView;

    public BaseFragment(){
        super();
        setRetainInstance(true);
        if (getArguments() == null)
            setArguments(new Bundle());

    }


    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = createContentView(inflater, container, savedInstanceState);
        prepareToolbar(fragmentView);
        View contentView = inflater.inflate(R.layout.fragment_base, container, false);
        mProgressView = contentView.findViewById(R.id.progress_container);
        ((FrameLayout) contentView.findViewById(R.id.fragment_content))
                .addView(fragmentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return contentView;
    }

    //** Abstract methods
    public abstract View createContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    //** Protected methods
    protected FragmentManager getFM() {
        if (getActivity() != null)
            return getActivity().getSupportFragmentManager();
        return null;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((TestApplication) getActivity().getApplication()).getApplicationComponent();
    }

    protected String getTitle() {
        return "";
    }

    private void prepareToolbar(View view) {
        if (view.findViewById(R.id.toolbar_back_button) != null) {
            view.findViewById(R.id.toolbar_back_button).setOnClickListener(this);
        }
        if (view.findViewById(R.id.toolbar_title) != null) {
            ((TextView) view.findViewById(R.id.toolbar_title)).setText(getTitle());
        }
    }

    //** View.OnClickListener
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_button:
                getActivity().onBackPressed();
                break;
        }
    }

    //** Base View
    @Override
    public void showWait() {
        if(mProgressView != null)
            mProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        if(mProgressView != null)
            mProgressView.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }


}
