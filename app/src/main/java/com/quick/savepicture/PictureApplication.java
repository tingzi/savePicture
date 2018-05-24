package com.quick.savepicture;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.quick.savepicture.model.PictureDatabase;

public class PictureApplication extends Application {

    private static PictureApplication instance;
    private PictureDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    public static PictureApplication getInstance() {
        return instance;
    }

    private void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        db = Room.databaseBuilder(getApplicationContext(), PictureDatabase.class, "picture").build();
    }

    public PictureDatabase getDb() {
        return db;
    }
}
