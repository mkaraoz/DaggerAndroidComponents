package com.duvarapps.daggerandroidcomponents.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Ticker
{
    @PrimaryKey(autoGenerate = true) private int id;
    @SerializedName("last_price") @Expose private double lastPrice;
    @SerializedName("timestamp") @Expose private double timestamp;
    private String symbol; // tBTCUSD

    public Ticker(int id, double lastPrice, double timestamp, String symbol) {
        this.id = id;
        this.lastPrice = lastPrice;
        this.timestamp = timestamp;
        this.symbol = symbol;
    }

    @Ignore
    public Ticker(double lastPrice, double timestamp, String symbol) {
        this.id = id;
        this.lastPrice = lastPrice;
        this.timestamp = timestamp;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
