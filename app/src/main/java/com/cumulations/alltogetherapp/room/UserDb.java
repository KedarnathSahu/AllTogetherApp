package com.cumulations.alltogetherapp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.cumulations.alltogetherapp.model.Record;

@Database(entities = {Record.class}, version = 1, exportSchema = false)
public abstract class UserDb extends RoomDatabase {

    public abstract UserDao userDao();

}
