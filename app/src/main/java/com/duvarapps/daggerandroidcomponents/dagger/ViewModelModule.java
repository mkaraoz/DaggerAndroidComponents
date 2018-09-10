package com.duvarapps.daggerandroidcomponents.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.duvarapps.daggerandroidcomponents.ui.TickerViewModelFactory;
import com.duvarapps.daggerandroidcomponents.ui.TickerViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule
{
    @Binds
    @IntoMap
    @ViewModelKey(TickerViewModel.class)
    abstract ViewModel bindTickerViewModel(TickerViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(TickerViewModelFactory factory);
}
