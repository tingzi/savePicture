package com.quick.savepicture.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Picture {
    @PrimaryKey
    public long id;

    @ColumnInfo(name = "path")
    public String path;

    @ColumnInfo(name = "size")
    public long size;

    @ColumnInfo(name = "display_name")
    public String displayName;

    @ColumnInfo(name = "date_added")
    public long dateAdded;

    @ColumnInfo(name = "is_uploaded")
    public boolean isUploaded;

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", displayName='" + displayName + '\'' +
                ", dateAdded=" + dateAdded +
                ", isUploaded=" + isUploaded +
                '}';
    }
}
