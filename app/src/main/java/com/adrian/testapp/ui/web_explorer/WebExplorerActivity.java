package com.adrian.testapp.ui.web_explorer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.adrian.testapp.ui.base.BaseActivity;

/**
 * Created by adrianjez on 07.12.2017.
 */

public class WebExplorerActivity extends BaseActivity {

    public static Intent getIntent(Context context, String targetURL){
        Intent i = new Intent(context, WebExplorerActivity.class);
        i.putExtras(WebExplorerFragment.generateArgs(targetURL));
        return i;
    }

    @Override
    protected Fragment newFragmentInstanceForArgs(Bundle args) {
        WebExplorerFragment f = new WebExplorerFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public String getFragmentTag() {
        return WebExplorerFragment.FRAGMENT_TAG;
    }
}
