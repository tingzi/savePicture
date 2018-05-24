package com.quick.savepicture.model;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.quick.savepicture.PictureApplication;
import com.quick.savepicture.bean.Picture;
import com.quick.savepicture.contract.PictureContract;
import com.quick.savepicture.model.dao.PictureDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

public class PictureModel implements PictureContract.Model {

    private Context context;
    private PictureDao pictureDao;
    private CompositeDisposable disposable;

    public PictureModel() {
        this.context = PictureApplication.getInstance().getApplicationContext();
        pictureDao = PictureApplication.getInstance().getDb().pictureDao();
        disposable = new CompositeDisposable();
    }

    @Override
    public List<Picture> getPhotos() {
        List<Picture> pictures = new ArrayList<>();
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        CursorLoader loader = new CursorLoader(context, imageUri, null, null, null, null);
        Cursor cursor = loader.loadInBackground();
        if (null != cursor) {
            while (cursor.moveToNext()) {
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE))/1024;
                String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                long dateAdded = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
                Picture bean = new Picture();
                bean.path = path;
                bean.size = size;
                bean.displayName = displayName;
                bean.dateAdded = dateAdded;
                pictures.add(bean);
            }
            cursor.close();
        }
        return pictures;
    }

    @Override
    public void savePhotos(List<Picture> pictures) {
        pictureDao.insertPictures(pictures);
    }

    @Override
    public void uploadPhotos(List<Picture> pictures) {
        if (null != pictures) {
            for (Picture picture : pictures) {
                picture.isUploaded = true;
            }
        }
        savePhotos(pictures);
    }
}
