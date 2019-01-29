package com.cumulations.alltogetherapp.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cumulations.alltogetherapp.model.Record;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll4theFirstTime(Record... records);

    @Update
    void updateRecord(Record... records);

    @Query("select * from users")
    LiveData<List<Record>> getAll();
}
