package com.duvarapps.daggerandroidcomponents.dagger;

import com.duvarapps.daggerandroidcomponents.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBinderModule
{
    @ContributesAndroidInjector (modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivity();
}
