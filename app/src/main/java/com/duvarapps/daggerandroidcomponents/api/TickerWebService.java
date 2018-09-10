package com.duvarapps.daggerandroidcomponents.api;

import com.duvarapps.daggerandroidcomponents.db.Ticker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TickerWebService
{
    @GET("pubticker/{symbol}")
    Call<Ticker> getTicker(@Path("symbol") String symbol);
}
