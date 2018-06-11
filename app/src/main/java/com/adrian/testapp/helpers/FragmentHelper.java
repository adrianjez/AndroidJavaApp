package com.adrian.testapp.helpers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.adrian.testapp.R;

import java.util.HashMap;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class FragmentHelper {

    public static final String BACK_BUTTON_STATE = "BackButtonState";

    private static final String sStackName = "MAIN_STACK";

    private static HashMap<String, Bundle> mStates = new HashMap<>();

    public static void addFirstFragment(FragmentManager fragmentManager, Fragment fragment, String tag) {
        FragmentHelper.addFirstFragment(fragmentManager, fragment, tag, false);
    }

    public static void addFirstFragment(FragmentManager fragmentManager, Fragment fragment, String tag, boolean fixedBack) {
        fragment.getArguments().putBoolean(BACK_BUTTON_STATE, fixedBack);
        fragmentManager.popBackStack(sStackName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager
                .beginTransaction()
                //.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment_container, fragment, tag)
                .commit();

    }


    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, String tag) {
        fragment.getArguments().putBoolean(BACK_BUTTON_STATE, true);
        fragmentManager
                .beginTransaction()
                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.fragment_container, fragment, tag)
                .addToBackStack(sStackName)
                .commit();
    }
}
