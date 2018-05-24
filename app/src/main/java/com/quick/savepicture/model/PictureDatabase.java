package com.quick.savepicture.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.quick.savepicture.bean.Picture;
import com.quick.savepicture.model.dao.PictureDao;

@Database(entities = {Picture.class}, version = 1)
public abstract class PictureDatabase extends RoomDatabase {
    public abstract PictureDao pictureDao();
}
