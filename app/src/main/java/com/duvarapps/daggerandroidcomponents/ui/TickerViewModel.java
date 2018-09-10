package com.duvarapps.daggerandroidcomponents.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.duvarapps.daggerandroidcomponents.db.Ticker;

import javax.inject.Inject;

public class TickerViewModel extends ViewModel
{
    private LiveData<Ticker> ticker;
    private TickerRepository tickerRepo;

    @Inject
    public TickerViewModel(TickerRepository tickerRepo) {
        this.tickerRepo = tickerRepo;
    }

    public void init(String symbol) {
        if (this.ticker != null) {
            return;
        }
        ticker = tickerRepo.getTicker(symbol);
    }

    public LiveData<Ticker> getTicker() {
        return this.ticker;
    }
}
