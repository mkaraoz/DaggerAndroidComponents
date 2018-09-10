package com.duvarapps.daggerandroidcomponents.dagger;

import com.duvarapps.daggerandroidcomponents.ui.TickerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule
{
    @ContributesAndroidInjector
    abstract TickerFragment contributeTickerFragment();
}
