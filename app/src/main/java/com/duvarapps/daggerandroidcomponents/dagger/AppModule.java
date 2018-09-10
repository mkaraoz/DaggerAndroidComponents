package com.duvarapps.daggerandroidcomponents.dagger;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.duvarapps.daggerandroidcomponents.api.TickerWebService;
import com.duvarapps.daggerandroidcomponents.db.MyDatabase;
import com.duvarapps.daggerandroidcomponents.db.TickerDao;
import com.duvarapps.daggerandroidcomponents.ui.TickerRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule
{
    private final static String BASE_URL = "https://api.bitfinex.com/v1/";

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    MyDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, MyDatabase.class, "ticker.db").build();
    }

    @Provides
    @Singleton
    TickerDao provideTickerDao(MyDatabase database) {
        return database.tickerDao();
    }

    @Provides
    @Singleton
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    TickerRepository provideTickerRepository(TickerWebService webservice, TickerDao tickerDao, Executor executor) {
        return new TickerRepository(webservice, tickerDao, executor);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(
                GsonConverterFactory.create(gson)).baseUrl(BASE_URL).build();
        return retrofit;
    }

    @Provides
    @Singleton
    TickerWebService provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(TickerWebService.class);
    }
}
