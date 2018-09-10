package com.duvarapps.daggerandroidcomponents.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TickerDao
{
    @Insert(onConflict = REPLACE)
    void save(Ticker ticker);

    @Query("SELECT * FROM ticker WHERE symbol = :symbol")
    LiveData<Ticker> load(String symbol);

    @Query("SELECT * FROM ticker WHERE symbol = :symbol AND (timestamp + 10000) < :currentTime LIMIT 1")
    Ticker hasTicker(String symbol, long currentTime);
}
