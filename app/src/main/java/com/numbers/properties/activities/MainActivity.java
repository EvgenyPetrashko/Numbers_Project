package com.numbers.properties.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.numbers.properties.R;
import com.numbers.properties.Temp;
import com.numbers.properties.fragments.AlgorithmsFragment;
import com.numbers.properties.fragments.InfoFragment;
import com.numbers.properties.fragments.LotteryFragment;
import com.numbers.properties.fragments.PropertiesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Button start_button;
    private AdView mAdView;
    NavigationView navigationView;
    DrawerLayout drawer;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        loadAdsInterstitial();
    }

    private void init() {
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoFragment(drawer)).commit();
    }

    private void AddConfigure(){
        mAdView = findViewById(R.id.main_add);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.menu_info:
                Temp.set(1);
                fragment = new InfoFragment(drawer);
                break;
            case R.id.menu_properties:
                Temp.set(2);
                fragment = new PropertiesFragment(drawer);
                break;
            case R.id.menu_algorithms:
                Temp.set(4);
                fragment = new AlgorithmsFragment(drawer);
                break;
            case R.id.menu_full_version:
                Intent intent = new Intent(MainActivity.this, FullVersionActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_lottery:
                Temp.set(6);
                fragment = new LotteryFragment(drawer, mInterstitialAd);
                break;

        }
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
        drawer.close();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (Temp.page == 3){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PropertiesFragment(drawer)).commit();
                Temp.set(2);
                return;
            }else if (Temp.page == 5){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AlgorithmsFragment(drawer)).commit();
                Temp.set(4);
                return;
            }
            super.onBackPressed();
        }
    }

    private  void loadAdsInterstitial(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

    }



}