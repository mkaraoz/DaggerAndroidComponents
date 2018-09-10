package com.duvarapps.daggerandroidcomponents.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duvarapps.daggerandroidcomponents.R;
import com.duvarapps.daggerandroidcomponents.db.Ticker;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class TickerFragment extends Fragment
{
    public static final String UID_KEY = "uid";

    @Inject ViewModelProvider.Factory viewModelFactory;

    private TickerViewModel viewModel;

    private TextView textView;

    public TickerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        textView = view.findViewById(R.id.textView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        String symbol = getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TickerViewModel.class);
        viewModel.init(symbol);
        viewModel.getTicker().observe(this, new Observer<Ticker>()
        {
            @Override
            public void onChanged(@Nullable Ticker ticker) {
                updateUI(ticker);
            }
        });
    }

    private void updateUI(@Nullable Ticker t) {
        if (t != null) {
            this.textView.setText("btcusd: " + t.getLastPrice());
        }
    }
}
