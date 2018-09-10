package com.duvarapps.daggerandroidcomponents.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Ticker.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase
{
    private static volatile MyDatabase INSTANCE;

    public abstract TickerDao tickerDao();
}
