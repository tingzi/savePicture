package com.quick.savepicture.contract;

import android.content.Context;

import com.quick.savepicture.bean.Picture;

import java.util.List;

public interface PictureContract {
    interface Model {
        List<Picture> getPhotos();
        void savePhotos(List<Picture> pictures);
        void uploadPhotos(List<Picture> pictures);
    }

    @FunctionalInterface
    interface View {
        void show(List<Picture> pictures);
    }

    interface Presenter {
        void load(View view);
        void onDestroy();
    }
}
