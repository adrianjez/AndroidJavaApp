package com.adrian.testapp.helpers;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by adrianjez on 07.12.2017.
 */

public class PicassoHelper {

    private static void loadPhoto(ImageView imageView, String url) {
        if (imageView != null) {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }

    @BindingAdapter("srcUrl")
    public static void srcUrl(ImageView imageView, String imageURL) {
        loadPhoto(imageView, imageURL);
    }
}
