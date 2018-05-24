package com.quick.savepicture.view;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.quick.savepicture.PictureApplication;

import java.io.File;

public class PictureViewPresenter {
    @BindingAdapter("android:src")
    public static void setImage(ImageView view, String path) {
        Glide.with(PictureApplication.getInstance())
                .load(new File(path))
                .thumbnail(0.1f)
                .into(view);
    }
}
