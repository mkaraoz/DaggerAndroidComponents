package com.duvarapps.daggerandroidcomponents.ui;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.duvarapps.daggerandroidcomponents.api.TickerWebService;
import com.duvarapps.daggerandroidcomponents.db.Ticker;
import com.duvarapps.daggerandroidcomponents.db.TickerDao;

import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class TickerRepository
{
    private final TickerWebService webservice;
    private final TickerDao tickerDao;
    private final Executor executor;

    @Inject
    public TickerRepository(TickerWebService webservice, TickerDao tickerDao, Executor executor) {
        this.webservice = webservice;
        this.tickerDao = tickerDao;
        this.executor = executor;
    }

    public LiveData<Ticker> getTicker(String symbol) {
        refreshTicker(symbol); // try to refresh data if possible from Bitfinex Api
        return tickerDao.load(symbol); // return a LiveData directly from the database.
    }

    private void refreshTicker(final String symbol) {
        executor.execute(new Runnable()
        {
            @Override
            public void run() {
                // Check if ticker was fetched recently
                boolean tickerExists = (tickerDao.hasTicker(symbol,
                        (new Date()).getTime()) != null);
                // If ticker have to be updated
                if (!tickerExists) {
                    webservice.getTicker(symbol).enqueue(new Callback<Ticker>()
                    {
                        @Override
                        public void onResponse(Call<Ticker> call, final Response<Ticker> response) {
                            executor.execute(new Runnable()
                            {
                                @Override
                                public void run() {
                                    Ticker ticker = response.body();
                                    ticker.setTimestamp((new Date()).getTime());
                                    ticker.setSymbol(symbol);
                                    tickerDao.save(ticker);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<Ticker> call, Throwable t) {
                            Log.e("_MK", t.getMessage(), t);
                        }
                    });
                }
            }
        });
    }
}
