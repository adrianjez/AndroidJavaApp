package com.adrian.testapp.ui.base;

/**
 * Created by adrianjez on 06.12.2017.
 */

public interface BaseView {

    void showWait();
    void removeWait();
    void onError(String error);
}
