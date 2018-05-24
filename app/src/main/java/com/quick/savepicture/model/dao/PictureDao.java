package com.quick.savepicture.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.quick.savepicture.bean.Picture;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PictureDao {

    @Query("SELECT * FROM picture WHERE is_uploaded = 0")  // TODO:
    Flowable<Picture> getUnUploadPictures();

    @Query("SELECT * FROM picture WHERE is_uploaded = 1")  // TODO:
    Flowable<Picture> getUploadPictures();

    @Query("SELECT * FROM picture")
    Flowable<Picture> getAllPictures();

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // TODO：替换时不包括idUpdated这项可以么？
    void insertPictures(List<Picture> pictures);

}
