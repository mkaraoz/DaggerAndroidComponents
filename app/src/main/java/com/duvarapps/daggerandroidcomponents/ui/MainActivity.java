package com.duvarapps.daggerandroidcomponents.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.duvarapps.daggerandroidcomponents.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector
{

    @Inject DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private static String SYMBOL = "btcusd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.showFragment(savedInstanceState);
    }

    private void showFragment(Bundle savedInstanceState){
        if (savedInstanceState == null) {

            TickerFragment fragment = new TickerFragment();

            Bundle bundle = new Bundle();
            bundle.putString(TickerFragment.UID_KEY, SYMBOL);
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, null)
                    .commit();
        }
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
